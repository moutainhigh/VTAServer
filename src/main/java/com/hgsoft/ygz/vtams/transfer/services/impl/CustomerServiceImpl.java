package com.hgsoft.ygz.vtams.transfer.services.impl;

import java.sql.Timestamp;

import com.hgsoft.ygz.vtams.transfer.common.enums.operationEnum;
import com.hgsoft.ygz.vtams.transfer.common.validation.CustomGroup;
import com.hgsoft.ygz.vtams.transfer.model.Customer;
import com.hgsoft.ygz.vtams.transfer.model.business.MsgResult;
import com.hgsoft.ygz.vtams.transfer.model.business.log.SyncLog;
import com.hgsoft.ygz.vtams.transfer.model.map.CustomerMapping;
import com.hgsoft.ygz.vtams.transfer.model.map.CustomerSeq;
import com.hgsoft.ygz.vtams.transfer.services.*;
import com.hgsoft.ygz.vtams.transfer.util.ValidationUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hgsoft.ygz.vtams.transfer.constant.VTAMsgContant;
import com.hgsoft.ygz.vtams.transfer.exception.SyncException;
import com.hgsoft.ygz.vtams.transfer.model.business.BusinessReq;
import com.hgsoft.ygz.vtams.transfer.model.business.CustomerMiddle;
import com.hgsoft.ygz.vtams.transfer.util.DateUtil;
import org.springframework.transaction.annotation.Transactional;

/**
 * 客户信息上传及变更
 *
 * @author 赖少涯
 * @date 2017-11-02
 */
@Service("customerService")
public class CustomerServiceImpl implements ICustomerService {

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

        final String mappingFailedMsgPrefix = "客户信息映射失败:";

        //将消息内容转成对应的实体类
        CustomerMiddle customerMiddle = jsonService.getObject(msg.getBusinessContent(), CustomerMiddle.class);
        if (null == customerMiddle) {
            syncException.setMappingEndTime(DateUtil.getCurrentSqlTimestamp());
            syncException.setStatusDesc(mappingFailedMsgPrefix + "json字符串无法转成对应的实体类");
            throw syncException;
        }

        //校验中间类，判断值是否符合要求
        String validateResult = ValidationUtil.validate(customerMiddle, CustomGroup.Primary.class);
        if (validateResult.length() > 0) {
            syncException.setMappingEndTime(DateUtil.getCurrentSqlTimestamp());
            syncException.setStatusDesc(mappingFailedMsgPrefix + validateResult);
            throw syncException;
        }

        //如果是单位用户，则需要补充校验
        if (customerMiddle.getUserType().equals(2)) {
            String validateSecondaryResult = ValidationUtil.validate(customerMiddle, CustomGroup.Secondary.class);
            if (validateSecondaryResult.length() > 0) {
                syncException.setMappingEndTime(DateUtil.getCurrentSqlTimestamp());
                syncException.setStatusDesc(mappingFailedMsgPrefix + validateSecondaryResult);
                throw syncException;
            }
        }

        //最终映射对象
        final Customer customer = new Customer();

        //客户类型
        customer.setUserType(customerMiddle.getUserType());
        //开户人名称
        customer.setUserName(StringUtils.trimToEmpty(customerMiddle.getUserName()));
        //开户人证件类型
        customer.setUserIdType(customerMiddle.getUserIdType());
        //开户人证件号码
        customer.setUserIdNum(StringUtils.trimToEmpty(customerMiddle.getUserIdNum()));
        //开户人/指定经办人电号码
        customer.setTel(customerMiddle.getTel());
        //地址
        customer.setAddress(StringUtils.trimToEmpty(customerMiddle.getAddress()));
        //开户方式
        customer.setRegisteredType(customerMiddle.getRegisteredType());
        //开户渠道编号
        customer.setChannelId(customerMiddle.getChannelId());
        //开户时间
        customer.setRegisteredTime(DateUtil.format(customerMiddle.getRegisteredTime()));

        //部门或单位、指定经办人姓名、指定经办人证件类型、指定经办人证件号
        customer.setDepartment(StringUtils.trimToEmpty(customerMiddle.getDepartment()));
        customer.setAgentName(StringUtils.trimToEmpty(customerMiddle.getAgentName()));
        customer.setAgentIdType(customerMiddle.getAgentIdType());
        customer.setAgentldNum(customerMiddle.getAgentldNum());

        //状态和状态变更时间、操作
        customer.setStatus(customerMiddle.getStatus());
        customer.setStatusChangeTime(DateUtil.format(customerMiddle.getStatusChangeTime()));
        customer.setOperation(msg.getOperation());

        //如果操作类型为新增，则获取顺序码构造新的用户编号；否则根据旧编号查找新的编号
        final String oldUserNo = customerMiddle.getId();

        CustomerMapping customerMapping;
        if (customer.getOperation().equals(operationEnum.add.getValue())) {
            //根据发行方编号、开户年月日、顺序码构建新的编号
            CustomerSeq customerSeq = new CustomerSeq();
            customerSeq.setIssuerId(VTAMsgContant.ISSUER_ID);
            customerSeq.setYearMonthDay(oldUserNo.substring(2, 8));
            final String seqStr = sequenceService.getCustomerSeq(customerSeq);
            if (null == seqStr) {
                syncException.setMappingEndTime(DateUtil.getCurrentSqlTimestamp());
                syncException.setStatusDesc(mappingFailedMsgPrefix + "顺序码获取失败，无法生成新的客户编号");
                throw syncException;
            }

            //生成新的客户编号
            final String newUserNo = customerSeq.getIssuerId() + customerSeq.getYearMonthDay() + seqStr;

            //构造新的映射关系并保存
            customerMapping = new CustomerMapping(oldUserNo, newUserNo);
            mappingService.saveCustomerMapping(customerMapping);
        } else {
            customerMapping = mappingService.getCustomerMapping(oldUserNo);
            if (null == customerMapping) {
                syncException.setMappingEndTime(DateUtil.getCurrentSqlTimestamp());
                syncException.setStatusDesc(mappingFailedMsgPrefix + "旧客户编号[" + oldUserNo + "]没有对应的映射记录");
                throw syncException;
            }
        }

        customer.setId(customerMapping.getNewUserNo());

        //发送最终映射customer 到部中心
        final Timestamp mappingEndTime = DateUtil.getCurrentSqlTimestamp();
        syncException.setMappingEndTime(mappingEndTime);
        syncException.setRequestTime(mappingEndTime);

        final String jsonStr = jsonService.getString(customer);
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
