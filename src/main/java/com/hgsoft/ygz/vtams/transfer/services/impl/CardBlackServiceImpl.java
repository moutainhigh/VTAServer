package com.hgsoft.ygz.vtams.transfer.services.impl;

import com.hgsoft.ygz.vtams.transfer.constant.VTAMsgContant;
import com.hgsoft.ygz.vtams.transfer.dao.InputStockMapper;
import com.hgsoft.ygz.vtams.transfer.exception.AsyncException;
import com.hgsoft.ygz.vtams.transfer.model.CardBlack;
import com.hgsoft.ygz.vtams.transfer.model.business.BusinessReq;
import com.hgsoft.ygz.vtams.transfer.model.business.CardBlackMiddle;
import com.hgsoft.ygz.vtams.transfer.model.business.log.AsyncLog;
import com.hgsoft.ygz.vtams.transfer.services.ICardBlackService;
import com.hgsoft.ygz.vtams.transfer.services.IJsonService;
import com.hgsoft.ygz.vtams.transfer.util.DateUtil;
import com.hgsoft.ygz.vtams.transfer.util.ExceptionUtil;
import com.hgsoft.ygz.vtams.transfer.util.ValidationUtil;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;

/**
 * 用户卡黑名单业务处理：非实时
 *
 * @author 赖少涯
 * @date 2017-10-15
 */
@Service("cardBlackService")
public class CardBlackServiceImpl implements ICardBlackService {

    private static final Logger log = LoggerFactory.getLogger(CardBlackServiceImpl.class);

    @Autowired
    private InputStockMapper inputStockMapper;

    @Autowired
    private IJsonService jsonService;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public AsyncLog inputStockAfterMapping(BusinessReq msg) {

        //获取业务对象、定义映射对象、日志、错误消息
        AsyncException asyncException = new AsyncException();
        asyncException.setMappingStartTime(DateUtil.getCurrentSqlTimestamp());

        final String mappingFailedMsgPrefix = "用户卡黑名单信息映射失败:";
        final String inputStockFailedMsg = "用户卡黑名单入库失败:";
        final String inputStockSuccessMsg = "用户卡黑名单入库成功";

        //业务操作
        CardBlackMiddle cardBlackMiddle = jsonService.getObject(msg.getBusinessContent(), CardBlackMiddle.class);
        if (null == cardBlackMiddle) {
            asyncException.setMappingEndTime(DateUtil.getCurrentSqlTimestamp());
            asyncException.setStatusDesc(mappingFailedMsgPrefix + "json字符串无法转成对应的实体类");
            throw asyncException;
        }

        //校验中间类，判断值是否符合要求
        String validateResult = ValidationUtil.validate(cardBlackMiddle);
        if (validateResult.length() > 0) {
            asyncException.setMappingEndTime(DateUtil.getCurrentSqlTimestamp());
            asyncException.setStatusDesc(mappingFailedMsgPrefix + validateResult);
            throw asyncException;
        }

        //最终对象
        CardBlack cardBlack = new CardBlack();

        //设置发行方编号
        cardBlack.setIssuerId(VTAMsgContant.ISSUSER_ID);

        //设置用户卡黑名单生成时间
        cardBlack.setCreationTime(DateUtil.format(cardBlackMiddle.getCreationTime()));

        //设置黑名单类型
        cardBlack.setType(cardBlackMiddle.getType());

        //设置用户卡编号
        cardBlack.setCardId(VTAMsgContant.AREA_CODE + cardBlackMiddle.getCardId());

        //设置状态
        cardBlack.setStatus(cardBlackMiddle.getStatus());

        //入库:捕获异常，并且判断受影响记录数是否为1
        final Timestamp mappingEndTime = DateUtil.getCurrentSqlTimestamp();
        asyncException.setMappingEndTime(mappingEndTime);
        asyncException.setInputStockStartTime(mappingEndTime);
        try {
            final int effectiveCount = inputStockMapper.saveCardBlack(cardBlack);
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
