package com.hgsoft.ygz.vtams.transfer.services.impl;

import java.sql.Timestamp;
import java.util.Date;

import com.hgsoft.ygz.vtams.transfer.common.enums.ChannelTypeEnum;
import com.hgsoft.ygz.vtams.transfer.common.enums.operationEnum;
import com.hgsoft.ygz.vtams.transfer.constant.VTAMsgContant;
import com.hgsoft.ygz.vtams.transfer.model.business.log.AsyncLog;
import com.hgsoft.ygz.vtams.transfer.model.map.PointMapping;
import com.hgsoft.ygz.vtams.transfer.util.ValidationUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hgsoft.ygz.vtams.transfer.dao.InputStockMapper;
import com.hgsoft.ygz.vtams.transfer.exception.AsyncException;
import com.hgsoft.ygz.vtams.transfer.model.Hall;
import com.hgsoft.ygz.vtams.transfer.model.business.BusinessReq;
import com.hgsoft.ygz.vtams.transfer.model.business.HallMiddle;
import com.hgsoft.ygz.vtams.transfer.model.map.HallSeq;
import com.hgsoft.ygz.vtams.transfer.services.IHallService;
import com.hgsoft.ygz.vtams.transfer.services.IJsonService;
import com.hgsoft.ygz.vtams.transfer.services.IMappingService;
import com.hgsoft.ygz.vtams.transfer.services.ISequenceService;
import com.hgsoft.ygz.vtams.transfer.util.DateUtil;
import com.hgsoft.ygz.vtams.transfer.util.ExceptionUtil;
import org.springframework.transaction.annotation.Transactional;

/**
 * 服务网点信息服务类
 *
 * @author 赖少涯
 * @date 2017-11-01
 */
@Service("hallService")
public class HallServiceImpl implements IHallService {

    @Autowired
    private IJsonService jsonService;

    @Autowired
    private InputStockMapper inputStockMapper;

    @Autowired
    private ISequenceService sequenceService;

    @Autowired
    private IMappingService mappingService;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public AsyncLog inputStockAfterMapping(BusinessReq msg) {
        AsyncException asyncException = new AsyncException();
        asyncException.setMappingStartTime(DateUtil.getCurrentSqlTimestamp());

        final String mappingFailedMsgPrefix = "服务网点信息映射失败:";
        final String inputStockFailedMsg = "服务网点信息入库失败:";
        final String inputStockSuccessMsg = "服务网点信息入库成功";

        //将消息内容转成对应的实体类
        HallMiddle hallMiddle = jsonService.getObject(msg.getBusinessContent(), HallMiddle.class);
        if (null == hallMiddle) {
            asyncException.setMappingEndTime(DateUtil.getCurrentSqlTimestamp());
            asyncException.setStatusDesc(mappingFailedMsgPrefix + "json字符串无法转成对应的实体类");
            throw asyncException;
        }

        //校验中间类，判断值是否符合要求
        String validateResult = ValidationUtil.validate(hallMiddle);
        if (validateResult.length() > 0) {
            asyncException.setMappingEndTime(DateUtil.getCurrentSqlTimestamp());
            asyncException.setStatusDesc(mappingFailedMsgPrefix + validateResult);
            throw asyncException;
        }

        //映射对象
        final Hall hall = new Hall();

        //设置服务网点名称、联系人名称、联系电话、地址、服务项目
        hall.setName(hallMiddle.getName());
        hall.setContact(hallMiddle.getContact());
        hall.setTel(hallMiddle.getTel());
        hall.setAddress(hallMiddle.getAddress());
        hall.setServiceItems(hallMiddle.getServiceItems());

        //设置营业时间:如果数据为空，默认为周一至周五 8:30-17:30
        final String businessHours = StringUtils.trimToEmpty(hallMiddle.getBusinessHours());
        if ("".equals(businessHours)) {
            hall.setBusinessHours(VTAMsgContant.DEFAULT_BUSINESSHOURS);
        } else {
            hall.setBusinessHours(businessHours);
        }

        //判断起始日期
        final Date startTime = hallMiddle.getStartTime();
        hall.setStartTime(DateUtil.format(startTime, VTAMsgContant.DATE_PATTERN_YMD));

        //设置终止日期
        final Date endTime = hallMiddle.getEndTime();
        if (null == endTime) {
            hall.setEndTime(VTAMsgContant.DEFAULT_END_DATE);
        } else {
            if (startTime.after(endTime)) {
                asyncException.setMappingEndTime(DateUtil.getCurrentSqlTimestamp());
                asyncException.setStatusDesc(mappingFailedMsgPrefix + "起始日期不能大于终止日期");
                throw asyncException;
            }
            hall.setEndTime(DateUtil.format(endTime, VTAMsgContant.DATE_PATTERN_YMD));
        }

        //设置服务网点编号 = 客服合作机构编号（11位）+地市编号（2位）+区县编号（2位）+网点顺序码（4位)
        //合作机构编号
        final String agencyId = hallMiddle.getAgencyId();

        //地市区县编号
        final String cityId = hallMiddle.getCityId();
        final String countyId = hallMiddle.getCountyId();

        //获取旧网点编号
        final String oldPointCode = hallMiddle.getOriginalCode();

        //设置操作类型
        final Integer operation = msg.getOperation();
        hall.setOperation(operation);

        //如果操作类型为新增，则获取顺序码构造新的网点编号；否则根据旧网点编号查找新的网点编号
        PointMapping pointMapping;
        if (operation.equals(operationEnum.add.getValue())) {
            //根据合作机构编号、地市、区县获取顺序码
            HallSeq hallSeq = new HallSeq();
            hallSeq.setAgencyId(agencyId);
            hallSeq.setCityCode(cityId);
            hallSeq.setDistrictCode(countyId);
            final String seqStr = sequenceService.getHallSeq(hallSeq);
            if (null == seqStr) {
                asyncException.setMappingEndTime(DateUtil.getCurrentSqlTimestamp());
                asyncException.setStatusDesc(mappingFailedMsgPrefix + "顺序码获取失败，无法生成新的网点编号");
                throw asyncException;
            }

            //生成信息网点编号
            final String newPointCode = agencyId + cityId + countyId + seqStr;

            //构造新的映射关系并保存
            pointMapping = new PointMapping(oldPointCode, newPointCode, agencyId, ChannelTypeEnum.hall.getValue());
            mappingService.savePointMapping(pointMapping);
        } else {
            pointMapping = mappingService.getPointMapping(oldPointCode);
            if (null == pointMapping) {
                asyncException.setMappingEndTime(DateUtil.getCurrentSqlTimestamp());
                asyncException.setStatusDesc(mappingFailedMsgPrefix + "旧网点编号[" + oldPointCode + "]没有对应的映射记录");
                throw asyncException;
            }
        }

        hall.setId(pointMapping.getNewPointCode());

        //入库:捕获异常，并且判断受影响记录数是否为1
        final Timestamp mappingEndTime = DateUtil.getCurrentSqlTimestamp();
        asyncException.setMappingEndTime(mappingEndTime);
        asyncException.setInputStockStartTime(mappingEndTime);
        try {
            final int effectiveCount = inputStockMapper.saveHall(hall);
            if (1 != effectiveCount) {
                asyncException.setInputStockEndTime(DateUtil.getCurrentSqlTimestamp());
                asyncException.setStatusDesc(inputStockFailedMsg + "存入数据库时受影响记录数为[" + effectiveCount + "]");
                throw asyncException;
            }
        } catch (Exception e) {
            asyncException.setInputStockEndTime(DateUtil.getCurrentSqlTimestamp());
            asyncException.setStatusDesc(inputStockFailedMsg + "无法存入数据库," + ExceptionUtil.getSimpleMsg(e));
            throw asyncException;
        }

        asyncException.setInputStockEndTime(DateUtil.getCurrentSqlTimestamp());
        asyncException.setStatusDesc(inputStockSuccessMsg);

        //构造日志对象返回
        AsyncLog asyncLog = new AsyncLog();
        BeanUtils.copyProperties(asyncException, asyncLog);
        return asyncLog;
    }
}
