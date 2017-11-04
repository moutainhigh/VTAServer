package com.hgsoft.ygz.vtams.transfer.services.impl;

import java.sql.Timestamp;
import java.util.Date;

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
import com.hgsoft.ygz.vtams.transfer.model.Issuer;
import com.hgsoft.ygz.vtams.transfer.model.business.BusinessReq;
import com.hgsoft.ygz.vtams.transfer.model.business.IssuerMiddle;
import com.hgsoft.ygz.vtams.transfer.services.IIssuerService;
import com.hgsoft.ygz.vtams.transfer.services.IJsonService;
import com.hgsoft.ygz.vtams.transfer.util.DateUtil;
import com.hgsoft.ygz.vtams.transfer.util.ExceptionUtil;
import org.springframework.transaction.annotation.Transactional;

/**
 * 发行方信息上传及变更
 *
 * @author 赖少涯
 * @date 2017-11-03
 */
@Service("issuerService")
public class IssuerServiceImpl implements IIssuerService {

    @Autowired
    private IJsonService jsonService;

    @Autowired
    private InputStockMapper inputStockMapper;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public AsyncLog inputStockAfterMapping(BusinessReq msg) {
        AsyncException asyncException = new AsyncException();
        asyncException.setMappingStartTime(DateUtil.getCurrentSqlTimestamp());

        final String mappingFailedMsgPrefix = "发行方信息映射失败:";
        final String inputStockFailedMsg = "发行方信息入库失败:";
        final String inputStockSuccessMsg = "发行方信息入库成功";

        //将消息内容转成对应的实体类
        IssuerMiddle issuerMiddle = jsonService.getObject(msg.getBusinessContent(), IssuerMiddle.class);
        if (null == issuerMiddle) {
            asyncException.setMappingEndTime(DateUtil.getCurrentSqlTimestamp());
            asyncException.setStatusDesc(mappingFailedMsgPrefix + "json字符串无法转成对应的实体类");
            throw asyncException;
        }

        //校验中间类，判断值是否符合要求
        String validateResult = ValidationUtil.validate(issuerMiddle);
        if (validateResult.length() > 0) {
            asyncException.setMappingEndTime(DateUtil.getCurrentSqlTimestamp());
            asyncException.setStatusDesc(mappingFailedMsgPrefix + validateResult);
            throw asyncException;
        }

        //映射对象
        final Issuer issuer = new Issuer();


        //判断起始日期
        final Date startTime = issuerMiddle.getStartTime();
        issuer.setStartTime(DateUtil.format(startTime, VTAMsgContant.DATE_PATTERN_YMD));

        //设置终止日期
        final Date endTime = issuerMiddle.getEndTime();
        if (null == endTime) {
            issuer.setEndTime(VTAMsgContant.DEFAULT_END_DATE);
        } else {
            if (startTime.after(endTime)) {
                asyncException.setMappingEndTime(DateUtil.getCurrentSqlTimestamp());
                asyncException.setStatusDesc(mappingFailedMsgPrefix + "起始日期不能大于终止日期");
                throw asyncException;
            }
            issuer.setEndTime(DateUtil.format(endTime, VTAMsgContant.DATE_PATTERN_YMD));
        }

        issuer.setId(issuerMiddle.getId());
        issuer.setName(issuerMiddle.getName());
        issuer.setTel(issuerMiddle.getTel());
        issuer.setContact(issuerMiddle.getContact());
        issuer.setAddress(issuerMiddle.getAddress());
        issuer.setOperation(msg.getOperation());

        //入库:捕获异常，并且判断受影响记录数是否为1
        final Timestamp mappingEndTime = DateUtil.getCurrentSqlTimestamp();
        asyncException.setMappingEndTime(mappingEndTime);
        asyncException.setInputStockStartTime(mappingEndTime);
        try {
            final int effectiveCount = inputStockMapper.saveIssuer(issuer);
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
