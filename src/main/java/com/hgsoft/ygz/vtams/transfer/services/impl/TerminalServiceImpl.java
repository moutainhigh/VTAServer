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
import com.hgsoft.ygz.vtams.transfer.model.Terminal;
import com.hgsoft.ygz.vtams.transfer.model.business.BusinessReq;
import com.hgsoft.ygz.vtams.transfer.model.business.TerminalMiddle;
import com.hgsoft.ygz.vtams.transfer.model.map.TerminalSeq;
import com.hgsoft.ygz.vtams.transfer.services.IJsonService;
import com.hgsoft.ygz.vtams.transfer.services.IMappingService;
import com.hgsoft.ygz.vtams.transfer.services.ISequenceService;
import com.hgsoft.ygz.vtams.transfer.services.ITerminalService;
import com.hgsoft.ygz.vtams.transfer.util.DateUtil;
import com.hgsoft.ygz.vtams.transfer.util.ExceptionUtil;
import org.springframework.transaction.annotation.Transactional;

/**
 * 自助服务终端信息服务类
 *
 * @author 赖少涯
 * @date 2017-11-01
 */
@Service("terminalService")
public class TerminalServiceImpl implements ITerminalService {

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

        final String mappingFailedMsgPrefix = "自助服务终端信息映射失败:";
        final String inputStockFailedMsg = "自助服务终端信息入库失败:";
        final String inputStockSuccessMsg = "自助服务终端信息入库成功";

        //将消息内容转成对应的实体类
        TerminalMiddle terminalMiddle = jsonService.getObject(msg.getBusinessContent(), TerminalMiddle.class);
        if (null == terminalMiddle) {
            asyncException.setMappingEndTime(DateUtil.getCurrentSqlTimestamp());
            asyncException.setStatusDesc(mappingFailedMsgPrefix + "json字符串无法转成对应的实体类");
            throw asyncException;
        }

        //校验中间类，判断值是否符合要求
        String validateResult = ValidationUtil.validate(terminalMiddle);
        if (validateResult.length() > 0) {
            asyncException.setMappingEndTime(DateUtil.getCurrentSqlTimestamp());
            asyncException.setStatusDesc(mappingFailedMsgPrefix + validateResult);
            throw asyncException;
        }

        //映射对象
        final Terminal terminal = new Terminal();

        //设置地址
        terminal.setAddress(terminal.getAddress());

        //设置服务项目
        terminal.setServiceItems(terminal.getServiceItems());

        //设置营业时间:如果数据为空，默认为周一至周五 8:30-17:30
        final String businessHours = StringUtils.trimToEmpty(terminalMiddle.getBusinessHours());
        if ("".equals(businessHours)) {
            terminal.setBusinessHours(VTAMsgContant.DEFAULT_BUSINESSHOURS);
        } else {
            terminal.setBusinessHours(businessHours);
        }

        //判断起始日期
        final Date startTime = terminalMiddle.getStartTime();
        terminal.setStartTime(DateUtil.format(startTime, VTAMsgContant.DATE_PATTERN_YMD));

        //设置终止日期
        final Date endTime = terminalMiddle.getEndTime();
        if (null == endTime) {
            terminal.setEndTime(VTAMsgContant.DEFAULT_END_DATE);
        } else {
            if (startTime.after(endTime)) {
                asyncException.setMappingEndTime(DateUtil.getCurrentSqlTimestamp());
                asyncException.setStatusDesc(mappingFailedMsgPrefix + "起始日期不能大于终止日期");
                throw asyncException;
            }
            terminal.setEndTime(DateUtil.format(endTime, VTAMsgContant.DATE_PATTERN_YMD));
        }

        //设置服务网点编号 = 客服合作机构编号（11位）+地市编号（2位）+区县编号（2位）+网点顺序码（4位)
        //合作机构编号
        final String agencyId = terminalMiddle.getAgencyId();

        //地市区县编号
        final String cityId = terminalMiddle.getCityId();
        final String countyId = terminalMiddle.getCountyId();

        //获取旧网点编号
        final String oldPointCode = terminalMiddle.getOriginalCode();

        //设置操作类型
        final Integer operation = msg.getOperation();
        terminal.setOperation(operation);

        //如果操作类型为新增，则获取顺序码构造新的网点编号；否则根据旧网点编号查找新的网点编号
        PointMapping pointMapping;
        if (operation.equals(operationEnum.add.getValue())) {
            //根据合作机构编号、地市、区县获取顺序码
            TerminalSeq terminalSeq = new TerminalSeq();
            terminalSeq.setAgencyId(agencyId);
            terminalSeq.setCityCode(cityId);
            terminalSeq.setDistrictCode(countyId);
            final String seqStr = sequenceService.getTerminalSeq(terminalSeq);
            if (null == seqStr) {
                asyncException.setMappingEndTime(DateUtil.getCurrentSqlTimestamp());
                asyncException.setStatusDesc(mappingFailedMsgPrefix + "顺序码获取失败，无法生成新的网点编号");
                throw asyncException;
            }

            //生成信息网点编号
            final String newPointCode = agencyId + cityId + countyId + seqStr;

            //构造新的映射关系并保存
            pointMapping = new PointMapping(oldPointCode, newPointCode, agencyId, ChannelTypeEnum.terminal.getValue());
            mappingService.savePointMapping(pointMapping);
        } else {
            pointMapping = mappingService.getPointMapping(oldPointCode);
            if (null == pointMapping) {
                asyncException.setMappingEndTime(DateUtil.getCurrentSqlTimestamp());
                asyncException.setStatusDesc(mappingFailedMsgPrefix + "旧网点编号[" + oldPointCode + "]没有对应的映射记录");
                throw asyncException;
            }
        }

        terminal.setId(pointMapping.getNewPointCode());

        //入库:捕获异常，并且判断受影响记录数是否为1
        final Timestamp mappingEndTime = DateUtil.getCurrentSqlTimestamp();
        asyncException.setMappingEndTime(mappingEndTime);
        asyncException.setInputStockStartTime(mappingEndTime);
        try {
            final int effectiveCount = inputStockMapper.saveTerminal(terminal);
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
