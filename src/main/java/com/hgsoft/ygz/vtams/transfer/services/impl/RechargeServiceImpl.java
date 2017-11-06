package com.hgsoft.ygz.vtams.transfer.services.impl;

import java.sql.Timestamp;

import com.hgsoft.ygz.vtams.transfer.model.Vehicle;
import com.hgsoft.ygz.vtams.transfer.model.business.MsgResult;
import com.hgsoft.ygz.vtams.transfer.model.business.VehicleMiddle;
import com.hgsoft.ygz.vtams.transfer.model.business.log.SyncLog;
import com.hgsoft.ygz.vtams.transfer.model.map.*;
import com.hgsoft.ygz.vtams.transfer.services.*;
import com.hgsoft.ygz.vtams.transfer.util.ValidationUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hgsoft.ygz.vtams.transfer.constant.VTAMsgContant;
import com.hgsoft.ygz.vtams.transfer.exception.SyncException;
import com.hgsoft.ygz.vtams.transfer.model.Recharge;
import com.hgsoft.ygz.vtams.transfer.model.business.BusinessReq;
import com.hgsoft.ygz.vtams.transfer.model.business.RechargeMiddle;
import com.hgsoft.ygz.vtams.transfer.util.DateUtil;
import org.springframework.transaction.annotation.Transactional;

/**
 * 充值交易上传
 *
 * @author 赖少涯
 * @date 2017-11-04
 */
@Service("rechargeService")
public class RechargeServiceImpl implements IRechargeService {

    @Autowired
    private IJsonService jsonService;

    @Autowired
    private ICommunicationService communicationService;

    @Autowired
    private IMappingService mappingService;

    @Autowired
    private ISequenceService sequenceService;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public SyncLog transportAfterMapping(BusinessReq msg) {
        SyncException syncException = new SyncException();
        syncException.setMappingStartTime(DateUtil.getCurrentSqlTimestamp());

        final String mappingFailedMsgPrefix = "充值交易信息映射失败:";

        //将消息内容转成对应的实体类
        RechargeMiddle rechargeMiddle = jsonService.getObject(msg.getBusinessContent(), RechargeMiddle.class);
        if (null == rechargeMiddle) {
            syncException.setMappingEndTime(DateUtil.getCurrentSqlTimestamp());
            syncException.setStatusDesc(mappingFailedMsgPrefix + "json字符串无法转成对应的实体类");
            throw syncException;
        }

        //校验中间类，判断值是否符合要求
        String validateResult = ValidationUtil.validate(rechargeMiddle);
        if (validateResult.length() > 0) {
            syncException.setMappingEndTime(DateUtil.getCurrentSqlTimestamp());
            syncException.setStatusDesc(mappingFailedMsgPrefix + validateResult);
            throw syncException;
        }

        //最终映射对象
        final Recharge recharge = new Recharge();

        //设置渠道编号
        PointMapping pointMapping = mappingService.getPointMapping(rechargeMiddle.getOldPointCode());
        if (null == pointMapping) {
            syncException.setMappingEndTime(DateUtil.getCurrentSqlTimestamp());
            syncException.setStatusDesc(mappingFailedMsgPrefix + "无法根据旧网点编号[" + rechargeMiddle.getOldPointCode() + "]找到对应的映射记录");
            throw syncException;
        }

        //获取顺序码
        RechargeSeq rechargeSeq = new RechargeSeq();
        final String channelType = StringUtils.trimToEmpty(pointMapping.getChannelType());
        rechargeSeq.setChannelType(channelType);
        rechargeSeq.setPointCode(StringUtils.trimToEmpty(pointMapping.getNewPointCode()));
        rechargeSeq.setDateStr(DateUtil.format(rechargeMiddle.getTradeTime(), "yyyyMMddHHmmss"));
        final String seqStr = sequenceService.getRechargeSeq(rechargeSeq);
        if (null == seqStr) {
            syncException.setMappingEndTime(DateUtil.getCurrentSqlTimestamp());
            syncException.setStatusDesc(mappingFailedMsgPrefix + "顺序码获取失败，无法生成充值交易编号");
            throw syncException;
        }

        //生成新的充值交易编号
        final String newRechargeId = String.valueOf(channelType.charAt(channelType.length() - 1)) +
                rechargeSeq.getPointCode() + rechargeSeq.getDateStr() + seqStr;
        recharge.setId(newRechargeId);

        //旧充值交易标识
        final String oldRechargeId = StringUtils.trimToEmpty(rechargeMiddle.getOldRechargeId());

        //保存映射关系
        RechargeMapping rechargeMapping = new RechargeMapping(oldRechargeId, newRechargeId);
        mappingService.saveRechargeMapping(rechargeMapping);

        //设置卡号、实收金额、赠送金额、充值金额
        recharge.setCardId(VTAMsgContant.AREA_CODE + rechargeMiddle.getCardId());
        recharge.setPaidAmount(rechargeMiddle.getPaidAmount());
        recharge.setGiftAmount(rechargeMiddle.getGiftAmount());
        recharge.setRechargeAmount(rechargeMiddle.getRechargeAmount());

        //设置充值交易编号
        recharge.setId(rechargeMapping.getNewRechargeId());

        //发送最终映射customer 到部中心
        final Timestamp mappingEndTime = DateUtil.getCurrentSqlTimestamp();
        syncException.setMappingEndTime(mappingEndTime);
        syncException.setRequestTime(mappingEndTime);

        final String jsonStr = jsonService.getString(recharge);
        MsgResult msgResult = communicationService.sendMsg(jsonStr, msg.getBusinessType());

        syncException.setReqFileName(msgResult.getReqFileName());
        syncException.setReqFileMd5(msgResult.getReqFileMd5());
        syncException.setResponseTime(msgResult.getResponseTime());
        syncException.setResponseContent(msgResult.getResponseContent());
        syncException.setResponseCode(msgResult.getResponseCode());
        syncException.setStatusDesc(msgResult.getStatusDesc());

        //判断响应码,200-300则正常返回，否则抛出异常
        if (syncException.getResponseCode() >= 200 && syncException.getResponseCode() < 300) {
            //构造日志对象返回
            SyncLog syncLog = new SyncLog();
            BeanUtils.copyProperties(syncException, syncLog);
            return syncLog;
        }

        throw syncException;
    }
}
