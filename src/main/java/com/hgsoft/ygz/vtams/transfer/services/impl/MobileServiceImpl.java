package com.hgsoft.ygz.vtams.transfer.services.impl;

import java.sql.Timestamp;
import java.util.Date;

import com.hgsoft.ygz.vtams.transfer.common.enums.ChannelTypeEnum;
import com.hgsoft.ygz.vtams.transfer.common.enums.operationEnum;
import com.hgsoft.ygz.vtams.transfer.constant.VTAMsgContant;
import com.hgsoft.ygz.vtams.transfer.model.MobileNet;
import com.hgsoft.ygz.vtams.transfer.model.business.MobileServiceMiddle;
import com.hgsoft.ygz.vtams.transfer.model.business.log.AsyncLog;
import com.hgsoft.ygz.vtams.transfer.model.map.MobileServiceSeq;
import com.hgsoft.ygz.vtams.transfer.model.map.PointMapping;
import com.hgsoft.ygz.vtams.transfer.util.DateUtil;
import com.hgsoft.ygz.vtams.transfer.util.ExceptionUtil;
import com.hgsoft.ygz.vtams.transfer.util.ValidationUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hgsoft.ygz.vtams.transfer.dao.InputStockMapper;
import com.hgsoft.ygz.vtams.transfer.exception.AsyncException;
import com.hgsoft.ygz.vtams.transfer.model.business.BusinessReq;
import com.hgsoft.ygz.vtams.transfer.services.IJsonService;
import com.hgsoft.ygz.vtams.transfer.services.IMappingService;
import com.hgsoft.ygz.vtams.transfer.services.IMobileService;
import com.hgsoft.ygz.vtams.transfer.services.ISequenceService;
import org.springframework.transaction.annotation.Transactional;

/**
 * 流动服务网点信息服务类
 *
 * @author 张梁，赖少涯
 * @date 2017/10/25
 */
@Service("mobileService")
public class MobileServiceImpl implements IMobileService {

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

        final String mappingFailedMsgPrefix = "流动网点信息映射失败:";
        final String inputStockFailedMsg = "流动网点信息入库失败:";
        final String inputStockSuccessMsg = "流动网点终端信息入库成功";


        //将消息内容转成对应的实体类
        MobileServiceMiddle mobileServiceMiddle = jsonService.getObject(msg.getBusinessContent(), MobileServiceMiddle.class);
        if (null == mobileServiceMiddle) {
            asyncException.setMappingEndTime(DateUtil.getCurrentSqlTimestamp());
            asyncException.setStatusDesc(mappingFailedMsgPrefix + "json字符串无法转成对应的实体类");
            throw asyncException;
        }

        //校验中间类，判断值是否符合要求
        String validateResult = ValidationUtil.validate(mobileServiceMiddle);
        if (validateResult.length() > 0) {
            asyncException.setMappingEndTime(DateUtil.getCurrentSqlTimestamp());
            asyncException.setStatusDesc(mappingFailedMsgPrefix + validateResult);
            throw asyncException;
        }

        //映射对象
        final MobileNet mobileNet = new MobileNet();

        //判断起始日期
        final Date startTime = mobileServiceMiddle.getStartTime();
        mobileNet.setStartTime(DateUtil.format(startTime, VTAMsgContant.DATE_PATTERN_YMD));

        //设置终止日期
        final Date endTime = mobileServiceMiddle.getEndTime();
        if (null == endTime) {
            mobileNet.setEndTime(VTAMsgContant.DEFAULT_END_DATE);
        } else {
            if (startTime.after(endTime)) {
                asyncException.setMappingEndTime(DateUtil.getCurrentSqlTimestamp());
                asyncException.setStatusDesc(mappingFailedMsgPrefix + "起始日期不能大于终止日期");
                throw asyncException;
            }
            mobileNet.setEndTime(DateUtil.format(endTime, VTAMsgContant.DATE_PATTERN_YMD));
        }

        //设置流动服务网点编号 = 客服合作机构编号（11位）+网点顺序码（4位)
        //合作机构编号
        final String agencyId = mobileServiceMiddle.getAgencyId();

        //获取旧网点编号
        final String oldPointCode = mobileServiceMiddle.getOriginalCode();

        //设置操作类型
        final Integer operation = msg.getOperation();
        mobileNet.setOperation(operation);

        //如果操作类型为新增，则获取顺序码构造新的网点编号；否则根据旧网点编号查找新的网点编号
        PointMapping pointMapping;
        if (operation.equals(operationEnum.add.getValue())) {
            //根据合作机构编号获取顺序码
            MobileServiceSeq mobileServiceSeq = new MobileServiceSeq();
            mobileServiceSeq.setAgencyId(agencyId);
            final String seqStr = sequenceService.getMobileServiceSeq(mobileServiceSeq);
            if (null == seqStr) {
                asyncException.setMappingEndTime(DateUtil.getCurrentSqlTimestamp());
                asyncException.setStatusDesc(mappingFailedMsgPrefix + "顺序码获取失败，无法生成新的网点编号");
                throw asyncException;
            }

            //生成信息网点编号
            final String newPointCode = agencyId + seqStr;

            //构造新的映射关系并保存
            pointMapping = new PointMapping(oldPointCode, newPointCode, agencyId, ChannelTypeEnum.mobileService.getValue());
            mappingService.savePointMapping(pointMapping);
        } else {
            pointMapping = mappingService.getPointMapping(oldPointCode);
            if (null == pointMapping) {
                asyncException.setMappingEndTime(DateUtil.getCurrentSqlTimestamp());
                asyncException.setStatusDesc(mappingFailedMsgPrefix + "旧网点编号[" + oldPointCode + "]没有对应的映射记录");
                throw asyncException;
            }
        }
        mobileNet.setId(pointMapping.getNewPointCode());

        //入库:捕获异常，并且判断受影响记录数是否为1
        final Timestamp mappingEndTime = DateUtil.getCurrentSqlTimestamp();
        asyncException.setMappingEndTime(mappingEndTime);
        asyncException.setInputStockStartTime(mappingEndTime);
        try {
            final int effectiveCount = inputStockMapper.saveMobileNet(mobileNet);
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
