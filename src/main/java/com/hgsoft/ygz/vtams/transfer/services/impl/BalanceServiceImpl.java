package com.hgsoft.ygz.vtams.transfer.services.impl;

import java.sql.Timestamp;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import com.hgsoft.ygz.vtams.transfer.common.enums.ChannelTypeEnum;
import com.hgsoft.ygz.vtams.transfer.common.enums.operationEnum;
import com.hgsoft.ygz.vtams.transfer.constant.VTAMsgContant;
import com.hgsoft.ygz.vtams.transfer.model.Hall;
import com.hgsoft.ygz.vtams.transfer.model.business.HallMiddle;
import com.hgsoft.ygz.vtams.transfer.model.business.log.AsyncLog;
import com.hgsoft.ygz.vtams.transfer.model.map.HallSeq;
import com.hgsoft.ygz.vtams.transfer.model.map.PointMapping;
import com.hgsoft.ygz.vtams.transfer.util.ValidationUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hgsoft.ygz.vtams.transfer.dao.InputStockMapper;
import com.hgsoft.ygz.vtams.transfer.exception.AsyncException;
import com.hgsoft.ygz.vtams.transfer.exception.SyncException;
import com.hgsoft.ygz.vtams.transfer.model.Balance;
import com.hgsoft.ygz.vtams.transfer.model.business.BalanceMiddle;
import com.hgsoft.ygz.vtams.transfer.model.business.BusinessReq;
import com.hgsoft.ygz.vtams.transfer.model.map.CustomerMapping;
import com.hgsoft.ygz.vtams.transfer.services.IBalanceService;
import com.hgsoft.ygz.vtams.transfer.services.IJsonService;
import com.hgsoft.ygz.vtams.transfer.services.IMappingService;
import com.hgsoft.ygz.vtams.transfer.util.DateUtil;
import com.hgsoft.ygz.vtams.transfer.util.ExceptionUtil;
import org.springframework.transaction.annotation.Transactional;

/**
 * 用户卡帐余额服务类
 *
 * @author 赖少涯
 * @date 2017-11-01
 */
@Service("balanceService")
public class BalanceServiceImpl implements IBalanceService {

    @Autowired
    private IJsonService jsonService;

    @Autowired
    private InputStockMapper inputStockMapper;

    @Autowired
    private IMappingService mappingService;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public AsyncLog inputStockAfterMapping(BusinessReq msg) {
        AsyncException asyncException = new AsyncException();
        asyncException.setMappingStartTime(DateUtil.getCurrentSqlTimestamp());

        final String mappingFailedMsgPrefix = "卡账余额信息映射失败:";
        final String inputStockFailedMsg = "卡账余额信息入库失败:";
        final String inputStockSuccessMsg = "卡账余额信息入库成功";


        //将消息内容转成对应的实体类
        BalanceMiddle balanceMiddle = jsonService.getObject(msg.getBusinessContent(), BalanceMiddle.class);
        if (null == balanceMiddle) {
            asyncException.setMappingEndTime(DateUtil.getCurrentSqlTimestamp());
            asyncException.setStatusDesc(mappingFailedMsgPrefix + "json字符串无法转成对应的实体类");
            throw asyncException;
        }

        //映射对象
        final Balance balance = new Balance();

        //校验中间类，判断值是否符合要求
        String validateResult = ValidationUtil.validate(balanceMiddle);
        if (validateResult.length() > 0) {
            asyncException.setMappingEndTime(DateUtil.getCurrentSqlTimestamp());
            asyncException.setStatusDesc(mappingFailedMsgPrefix + validateResult);
            throw asyncException;
        }

        //根据旧用户编号，查询新的用户编号
        CustomerMapping customerMapping = mappingService.getCustomerMapping(balanceMiddle.getUserId());
        if (null == customerMapping) {
            asyncException.setMappingEndTime(DateUtil.getCurrentSqlTimestamp());
            asyncException.setStatusDesc(mappingFailedMsgPrefix + "旧的客户编号[" + balanceMiddle.getUserId() + "]没有对应的映射记录");
            throw asyncException;
        }

        //设置卡号、卡账余额
        balance.setUserId(customerMapping.getNewUserNo());
        balance.setCardId(VTAMsgContant.AREA_CODE + balanceMiddle.getCardId());
        balance.setFee(balanceMiddle.getFee());

        //入库:捕获异常，并且判断受影响记录数是否为1
        final Timestamp mappingEndTime = DateUtil.getCurrentSqlTimestamp();
        asyncException.setMappingEndTime(mappingEndTime);
        asyncException.setInputStockStartTime(mappingEndTime);
        try {
            final int effectiveCount = inputStockMapper.saveBalance(balance);
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
