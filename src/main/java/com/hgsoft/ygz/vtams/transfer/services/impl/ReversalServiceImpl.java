package com.hgsoft.ygz.vtams.transfer.services.impl;

import java.sql.Timestamp;

import com.hgsoft.ygz.vtams.transfer.model.Recharge;
import com.hgsoft.ygz.vtams.transfer.model.business.MsgResult;
import com.hgsoft.ygz.vtams.transfer.model.business.RechargeMiddle;
import com.hgsoft.ygz.vtams.transfer.model.business.log.SyncLog;
import com.hgsoft.ygz.vtams.transfer.model.map.PointMapping;
import com.hgsoft.ygz.vtams.transfer.model.map.RechargeSeq;
import com.hgsoft.ygz.vtams.transfer.util.ValidationUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hgsoft.ygz.vtams.transfer.constant.VTAMsgContant;
import com.hgsoft.ygz.vtams.transfer.exception.SyncException;
import com.hgsoft.ygz.vtams.transfer.model.Reversal;
import com.hgsoft.ygz.vtams.transfer.model.business.BusinessReq;
import com.hgsoft.ygz.vtams.transfer.model.business.ReversalMiddle;
import com.hgsoft.ygz.vtams.transfer.model.map.RechargeMapping;
import com.hgsoft.ygz.vtams.transfer.services.ICommunicationService;
import com.hgsoft.ygz.vtams.transfer.services.IJsonService;
import com.hgsoft.ygz.vtams.transfer.services.IMappingService;
import com.hgsoft.ygz.vtams.transfer.services.IReversalService;
import com.hgsoft.ygz.vtams.transfer.util.DateUtil;
import org.springframework.transaction.annotation.Transactional;

/**
 * 冲正交易服务类
 *
 * @author 赖少涯
 * @date 2017/10/21
 */
@Service("reversalService")
public class ReversalServiceImpl implements IReversalService {

    @Autowired
    private IJsonService jsonService;

    @Autowired
    private ICommunicationService communicationService;

    @Autowired
    private IMappingService mappingService;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public SyncLog transportAfterMapping(BusinessReq msg) {
        SyncException syncException = new SyncException();
        syncException.setMappingStartTime(DateUtil.getCurrentSqlTimestamp());

        final String mappingFailedMsgPrefix = "冲正交易信息映射失败:";

        //将消息内容转成对应的实体类
        ReversalMiddle reversalMiddle = jsonService.getObject(msg.getBusinessContent(), ReversalMiddle.class);
        if (null == reversalMiddle) {
            syncException.setMappingEndTime(DateUtil.getCurrentSqlTimestamp());
            syncException.setStatusDesc(mappingFailedMsgPrefix + "json字符串无法转成对应的实体类");
            throw syncException;
        }

        //校验中间类，判断值是否符合要求
        String validateResult = ValidationUtil.validate(reversalMiddle);
        if (validateResult.length() > 0) {
            syncException.setMappingEndTime(DateUtil.getCurrentSqlTimestamp());
            syncException.setStatusDesc(mappingFailedMsgPrefix + validateResult);
            throw syncException;
        }

        //最终映射对象
        final Reversal reversal = new Reversal();


        //根据旧充值交易标识获取对应的映射记录
        RechargeMapping rechargeMapping = mappingService.getRechargeMapping(reversalMiddle.getOldRechargeId());
        if (null == rechargeMapping) {
            syncException.setMappingEndTime(DateUtil.getCurrentSqlTimestamp());
            syncException.setStatusDesc(mappingFailedMsgPrefix + "旧充值交易标识[" + reversalMiddle.getOldRechargeId() + "]没有对应的映射记录");
            throw syncException;
        }

        //设置冲正交易流水编号
        reversal.setId(StringUtils.trimToEmpty(rechargeMapping.getNewRechargeId()) + "1");
        //设置冲正交易产生时间
        reversal.setEffectiveTime(DateUtil.format(reversalMiddle.getEffectiveTime()));
        //设置用户卡编号
        reversal.setCardId(VTAMsgContant.AREA_CODE + reversalMiddle.getCardId());

        //发送最终映射customer 到部中心
        final Timestamp mappingEndTime = DateUtil.getCurrentSqlTimestamp();
        syncException.setMappingEndTime(mappingEndTime);
        syncException.setRequestTime(mappingEndTime);

        final String jsonStr = jsonService.getString(reversal);
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
