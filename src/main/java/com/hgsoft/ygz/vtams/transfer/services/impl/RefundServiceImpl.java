package com.hgsoft.ygz.vtams.transfer.services.impl;

import java.sql.Timestamp;
import java.util.Date;

import com.hgsoft.ygz.vtams.transfer.common.enums.ChannelTypeEnum;
import com.hgsoft.ygz.vtams.transfer.common.enums.operationEnum;
import com.hgsoft.ygz.vtams.transfer.model.Hall;
import com.hgsoft.ygz.vtams.transfer.model.business.HallMiddle;
import com.hgsoft.ygz.vtams.transfer.model.business.log.AsyncLog;
import com.hgsoft.ygz.vtams.transfer.model.map.HallSeq;
import com.hgsoft.ygz.vtams.transfer.model.map.PointMapping;
import com.hgsoft.ygz.vtams.transfer.model.map.RefundSeq;
import com.hgsoft.ygz.vtams.transfer.services.ISequenceService;
import com.hgsoft.ygz.vtams.transfer.util.ValidationUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hgsoft.ygz.vtams.transfer.constant.VTAMsgContant;
import com.hgsoft.ygz.vtams.transfer.dao.InputStockMapper;
import com.hgsoft.ygz.vtams.transfer.exception.AsyncException;
import com.hgsoft.ygz.vtams.transfer.model.Refund;
import com.hgsoft.ygz.vtams.transfer.model.business.BusinessReq;
import com.hgsoft.ygz.vtams.transfer.model.business.RefundMiddle;
import com.hgsoft.ygz.vtams.transfer.model.map.CustomerMapping;
import com.hgsoft.ygz.vtams.transfer.services.IJsonService;
import com.hgsoft.ygz.vtams.transfer.services.IMappingService;
import com.hgsoft.ygz.vtams.transfer.services.IRefundService;
import com.hgsoft.ygz.vtams.transfer.util.DateUtil;
import com.hgsoft.ygz.vtams.transfer.util.ExceptionUtil;
import org.springframework.transaction.annotation.Transactional;

/**
 * 退款交易业务处理：非实时
 *
 * @author zhangliang
 * @date 2017-10-25
 */
@Service("refundService")
public class RefundServiceImpl implements IRefundService {

    @Autowired
    private InputStockMapper inputStockMapper;

    @Autowired
    private IJsonService jsonService;

    @Autowired
    private IMappingService mappingService;

    @Autowired
    private ISequenceService sequenceService;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public AsyncLog inputStockAfterMapping(BusinessReq msg) {
        AsyncException asyncException = new AsyncException();
        asyncException.setMappingStartTime(DateUtil.getCurrentSqlTimestamp());

        final String mappingFailedMsgPrefix = "退款交易信息映射失败:";
        final String inputStockFailedMsg = "退款交易信息入库失败:";
        final String inputStockSuccessMsg = "退款交易信息入库成功";

        //将消息内容转成对应的实体类
        RefundMiddle refundMiddle = jsonService.getObject(msg.getBusinessContent(), RefundMiddle.class);
        if (null == refundMiddle) {
            asyncException.setMappingEndTime(DateUtil.getCurrentSqlTimestamp());
            asyncException.setStatusDesc(mappingFailedMsgPrefix + "json字符串无法转成对应的实体类");
            throw asyncException;
        }

        //校验中间类，判断值是否符合要求
        String validateResult = ValidationUtil.validate(refundMiddle);
        if (validateResult.length() > 0) {
            asyncException.setMappingEndTime(DateUtil.getCurrentSqlTimestamp());
            asyncException.setStatusDesc(mappingFailedMsgPrefix + validateResult);
            throw asyncException;
        }

        //映射对象
        final Refund refund = new Refund();

        //根据旧的用户编号查询新用户编号
        CustomerMapping customerMapping = mappingService.getCustomerMapping(refundMiddle.getOldUserNo());
        if (null == customerMapping) {
            asyncException.setMappingEndTime(DateUtil.getCurrentSqlTimestamp());
            asyncException.setStatusDesc(mappingFailedMsgPrefix + "无法根据旧用户编号[" + refundMiddle.getOldUserNo() + "]找到对应的映射记录");
            throw asyncException;
        }

        //根据旧网点编号查找新网点编号
        PointMapping pointMapping = mappingService.getPointMapping(refundMiddle.getOldPointCode());
        if (null == pointMapping) {
            asyncException.setMappingEndTime(DateUtil.getCurrentSqlTimestamp());
            asyncException.setStatusDesc(mappingFailedMsgPrefix + "无法根据旧网点编号[" + refundMiddle.getOldPointCode() + "]找到对应的映射记录");
            throw asyncException;
        }

        //获取顺序码
        RefundSeq refundSeq = new RefundSeq();
        final String channelType = StringUtils.trimToEmpty(pointMapping.getChannelType());
        refundSeq.setChannelType(channelType);
        refundSeq.setPointCode(StringUtils.trimToEmpty(pointMapping.getNewPointCode()));
        refundSeq.setDateStr(DateUtil.format(refundMiddle.getRefundTime(), "yyyyMMddHHmmss"));
        final String seqStr = sequenceService.getRefundSeq(refundSeq);
        if (null == seqStr) {
            asyncException.setMappingEndTime(DateUtil.getCurrentSqlTimestamp());
            asyncException.setStatusDesc(mappingFailedMsgPrefix + "顺序码获取失败，无法生成新的退款ID");
            throw asyncException;
        }

        //生成新的退款ID
        final String newRefundId = String.valueOf(channelType.charAt(channelType.length() - 1)) +
                refundSeq.getPointCode() + refundSeq.getDateStr() + seqStr;
        refund.setId(newRefundId);
        //设置用户编号
        refund.setUserId(StringUtils.trimToEmpty(customerMapping.getNewUserNo()));
        //设置卡号
        refund.setCardId(VTAMsgContant.AREA_CODE + refundMiddle.getCardId());
        //设置退款金额
        refund.setFee(refundMiddle.getFee());

        //入库:捕获异常，并且判断受影响记录数是否为1
        final Timestamp mappingEndTime = DateUtil.getCurrentSqlTimestamp();
        asyncException.setMappingEndTime(mappingEndTime);
        asyncException.setInputStockStartTime(mappingEndTime);
        try {
            final int effectiveCount = inputStockMapper.saveRefund(refund);
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
