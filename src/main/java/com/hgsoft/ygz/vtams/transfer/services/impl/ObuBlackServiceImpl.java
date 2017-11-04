package com.hgsoft.ygz.vtams.transfer.services.impl;

import java.sql.Timestamp;

import com.hgsoft.ygz.vtams.transfer.model.business.log.AsyncLog;
import com.hgsoft.ygz.vtams.transfer.util.ValidationUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hgsoft.ygz.vtams.transfer.constant.VTAMsgContant;
import com.hgsoft.ygz.vtams.transfer.dao.InputStockMapper;
import com.hgsoft.ygz.vtams.transfer.exception.AsyncException;
import com.hgsoft.ygz.vtams.transfer.model.ObuBlack;
import com.hgsoft.ygz.vtams.transfer.model.business.BusinessReq;
import com.hgsoft.ygz.vtams.transfer.model.business.ObuBlackMiddle;
import com.hgsoft.ygz.vtams.transfer.services.IJsonService;
import com.hgsoft.ygz.vtams.transfer.services.IObuBlackService;
import com.hgsoft.ygz.vtams.transfer.util.DateUtil;
import com.hgsoft.ygz.vtams.transfer.util.ExceptionUtil;
import org.springframework.transaction.annotation.Transactional;

/**
 * OBU黑名单业务处理
 *
 * @author 赖少涯
 * @date 2017-10-25
 */
@Service("obuBlackService")
public class ObuBlackServiceImpl implements IObuBlackService {

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

        final String mappingFailedMsgPrefix = "obu黑名单信息映射失败:";
        final String inputStockFailedMsg = "obu黑名单入库失败:";
        final String inputStockSuccessMsg = "obu黑名单入库成功";

        //业务操作
        ObuBlackMiddle obuBlackMiddle = jsonService.getObject(msg.getBusinessContent(), ObuBlackMiddle.class);
        if (null == obuBlackMiddle) {
            asyncException.setMappingEndTime(DateUtil.getCurrentSqlTimestamp());
            asyncException.setStatusDesc(mappingFailedMsgPrefix + "json字符串无法转成对应的实体类");
            throw asyncException;
        }

        //校验中间类，判断值是否符合要求
        String validateResult = ValidationUtil.validate(obuBlackMiddle);
        if (validateResult.length() > 0) {
            asyncException.setMappingEndTime(DateUtil.getCurrentSqlTimestamp());
            asyncException.setStatusDesc(mappingFailedMsgPrefix + validateResult);
            throw asyncException;
        }

        //最终对象
        ObuBlack obuBlack = new ObuBlack();

        //设置发行方编号
        obuBlack.setIssuerId(VTAMsgContant.ISSUSER_ID);

        //设置黑名单生成时间
        obuBlack.setCreationTime(DateUtil.format(obuBlackMiddle.getCreationTime()));

        //设置黑名单类型
        obuBlack.setType(obuBlackMiddle.getType());

        //设置obuId
        obuBlack.setObuId(obuBlackMiddle.getObuId());

        //设置状态
        obuBlack.setStatus(obuBlackMiddle.getStatus());

        //入库:捕获异常，并且判断受影响记录数是否为1
        final Timestamp mappingEndTime = DateUtil.getCurrentSqlTimestamp();
        asyncException.setMappingEndTime(mappingEndTime);
        asyncException.setInputStockStartTime(mappingEndTime);
        try {
            final int effectiveCount = inputStockMapper.saveObuBlack(obuBlack);
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
