package com.hgsoft.ygz.vtams.transfer.services.impl;

import java.sql.Timestamp;

import com.hgsoft.ygz.vtams.transfer.constant.VTAMsgContant;
import com.hgsoft.ygz.vtams.transfer.model.business.log.SyncLog;
import com.hgsoft.ygz.vtams.transfer.model.map.PointMapping;
import com.hgsoft.ygz.vtams.transfer.util.ValidationUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hgsoft.ygz.vtams.transfer.exception.SyncException;
import com.hgsoft.ygz.vtams.transfer.model.Obu;
import com.hgsoft.ygz.vtams.transfer.model.business.BusinessReq;
import com.hgsoft.ygz.vtams.transfer.model.business.ObuMiddle;
import com.hgsoft.ygz.vtams.transfer.model.map.CustomerMapping;
import com.hgsoft.ygz.vtams.transfer.services.ICommunicationService;
import com.hgsoft.ygz.vtams.transfer.services.IJsonService;
import com.hgsoft.ygz.vtams.transfer.services.IMappingService;
import com.hgsoft.ygz.vtams.transfer.services.IObuService;
import com.hgsoft.ygz.vtams.transfer.util.DateUtil;
import org.springframework.transaction.annotation.Transactional;

/**
 * 客户OBU信息服务类
 *
 * @author 赖少涯
 * @date 2017-11-03
 */
@Service("obuService")
public class ObuServiceImpl implements IObuService {

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

        final String mappingFailedMsgPrefix = "obu信息映射失败:";

        //将消息内容转成对应的实体类
        ObuMiddle obuMiddle = jsonService.getObject(msg.getBusinessContent(), ObuMiddle.class);
        if (null == obuMiddle) {
            syncException.setMappingEndTime(DateUtil.getCurrentSqlTimestamp());
            syncException.setStatusDesc(mappingFailedMsgPrefix + "json字符串无法转成对应的实体类");
            throw syncException;
        }

        //校验中间类，判断值是否符合要求
        String validateResult = ValidationUtil.validate(obuMiddle);
        if (validateResult.length() > 0) {
            syncException.setMappingEndTime(DateUtil.getCurrentSqlTimestamp());
            syncException.setStatusDesc(mappingFailedMsgPrefix + validateResult);
            throw syncException;
        }

        //最终映射对象
        final Obu obu = new Obu();

        //使用BeanUtils将obuMiddle的大部分属性值赋予obu
        BeanUtils.copyProperties(obuMiddle, obu);

        //设置客户编号
        CustomerMapping customerMapping = mappingService.getCustomerMapping(obuMiddle.getOldUserNo());
        if (null == customerMapping) {
            syncException.setMappingEndTime(DateUtil.getCurrentSqlTimestamp());
            syncException.setStatusDesc(mappingFailedMsgPrefix + "无法根据旧客户编号[" + obuMiddle.getOldUserNo() + "]找到对应的映射记录");
            throw syncException;
        }
        obu.setUserId(customerMapping.getNewUserNo());

        //设置渠道编号
        PointMapping pointMapping = mappingService.getPointMapping(obuMiddle.getOldPointCode());
        if (null == pointMapping) {
            syncException.setMappingEndTime(DateUtil.getCurrentSqlTimestamp());
            syncException.setStatusDesc(mappingFailedMsgPrefix + "无法根据旧网点编号[" + obuMiddle.getOldPointCode() + "]找到对应的映射记录");
            throw syncException;
        }
        obu.setRegisteredChannelId(pointMapping.getNewPointCode());
        obu.setInstallChannelId(pointMapping.getNewPointCode());

        //设置车辆编号: 车牌号码（18位）+间隔符（1位）+车牌颜色（1位），必填
        final String vehicleId = StringUtils.trimToEmpty(obuMiddle.getVehiclePlate()) + "_" + obuMiddle.getVehicleColor();
        obu.setVehicleId(vehicleId);


        //设置启用时间、到期时间、安装时间、状态变更时间
        obu.setEnableTime(DateUtil.format(obuMiddle.getEnableTime()));
        obu.setExpireTime(DateUtil.format(obuMiddle.getExpireTime(), VTAMsgContant.DATE_PATTERN_YMD));
        obu.setRegisteredTime(DateUtil.format(obuMiddle.getRegisteredTime()));
        obu.setInstallTime(DateUtil.format(obuMiddle.getInstallTime()));
        obu.setStatusChangeTime(DateUtil.format(obuMiddle.getStatusChangeTime()));

        //设置操作类型
        obu.setOperation(msg.getOperation());

        //发送最终映射customer 到部中心
        final Timestamp mappingEndTime = DateUtil.getCurrentSqlTimestamp();
        syncException.setMappingEndTime(mappingEndTime);
        syncException.setRequestTime(mappingEndTime);

        final String jsonStr = jsonService.getString(obu);
        SyncException se = communicationService.sendMsg(jsonStr, msg.getBusinessType());

        syncException.setReqFileName(se.getReqFileName());
        syncException.setReqFileMd5(se.getReqFileMd5());
        syncException.setResponseTime(se.getResponseTime());
        syncException.setResponseContent(se.getResponseContent());
        syncException.setResponseCode(se.getResponseCode());
        syncException.setStatusDesc(se.getStatusDesc());

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
