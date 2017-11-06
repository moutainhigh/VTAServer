package com.hgsoft.ygz.vtams.transfer.services.impl;

import java.sql.Timestamp;

import com.hgsoft.ygz.vtams.transfer.common.enums.operationEnum;
import com.hgsoft.ygz.vtams.transfer.common.validation.CustomGroup;
import com.hgsoft.ygz.vtams.transfer.constant.VTAMsgContant;
import com.hgsoft.ygz.vtams.transfer.model.Customer;
import com.hgsoft.ygz.vtams.transfer.model.business.CustomerMiddle;
import com.hgsoft.ygz.vtams.transfer.model.business.MsgResult;
import com.hgsoft.ygz.vtams.transfer.model.business.log.AsyncLog;
import com.hgsoft.ygz.vtams.transfer.model.business.log.SyncLog;
import com.hgsoft.ygz.vtams.transfer.model.map.CustomerSeq;
import com.hgsoft.ygz.vtams.transfer.model.map.PointMapping;
import com.hgsoft.ygz.vtams.transfer.util.ValidationUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hgsoft.ygz.vtams.transfer.exception.SyncException;
import com.hgsoft.ygz.vtams.transfer.model.Vehicle;
import com.hgsoft.ygz.vtams.transfer.model.business.BusinessReq;
import com.hgsoft.ygz.vtams.transfer.model.business.VehicleMiddle;
import com.hgsoft.ygz.vtams.transfer.model.map.CustomerMapping;
import com.hgsoft.ygz.vtams.transfer.services.ICommunicationService;
import com.hgsoft.ygz.vtams.transfer.services.IJsonService;
import com.hgsoft.ygz.vtams.transfer.services.IMappingService;
import com.hgsoft.ygz.vtams.transfer.services.IVehicleService;
import com.hgsoft.ygz.vtams.transfer.util.DateUtil;
import org.springframework.transaction.annotation.Transactional;

/**
 * 客户车辆信息服务类
 *
 * @author 赖少涯
 * @date 2017-11-03
 */
@Service("vehicleService")
public class VehicleServiceImpl implements IVehicleService {

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

        final String mappingFailedMsgPrefix = "车辆信息映射失败:";

        //将消息内容转成对应的实体类
        VehicleMiddle vehicleMiddle = jsonService.getObject(msg.getBusinessContent(), VehicleMiddle.class);
        if (null == vehicleMiddle) {
            syncException.setMappingEndTime(DateUtil.getCurrentSqlTimestamp());
            syncException.setStatusDesc(mappingFailedMsgPrefix + "json字符串无法转成对应的实体类");
            throw syncException;
        }

        //校验中间类，判断值是否符合要求
        String validateResult = ValidationUtil.validate(vehicleMiddle);
        if (validateResult.length() > 0) {
            syncException.setMappingEndTime(DateUtil.getCurrentSqlTimestamp());
            syncException.setStatusDesc(mappingFailedMsgPrefix + validateResult);
            throw syncException;
        }

        //最终映射对象
        final Vehicle vehicle = new Vehicle();

        //使用BeanUtils将VehicleMiddle的大部分属性值赋予vehicle
        BeanUtils.copyProperties(vehicleMiddle, vehicle);

        //设置车辆编号: 车牌号码（18位）+间隔符（1位）+车牌颜色（1位），必填
        final String id = StringUtils.trimToEmpty(vehicleMiddle.getVehiclePlate()) + "_" + vehicleMiddle.getVehicleColor();
        vehicle.setId(id);

        //设置客户编号
        CustomerMapping customerMapping = mappingService.getCustomerMapping(vehicleMiddle.getOldUserNo());
        if (null == customerMapping) {
            syncException.setMappingEndTime(DateUtil.getCurrentSqlTimestamp());
            syncException.setStatusDesc(mappingFailedMsgPrefix + "无法根据旧客户编号[" + vehicleMiddle.getOldUserNo() + "]找到对应的映射记录");
            throw syncException;
        }
        vehicle.setUserId(customerMapping.getNewUserNo());

        //设置渠道编号
        PointMapping pointMapping = mappingService.getPointMapping(vehicleMiddle.getOldPointCode());
        if (null == pointMapping) {
            syncException.setMappingEndTime(DateUtil.getCurrentSqlTimestamp());
            syncException.setStatusDesc(mappingFailedMsgPrefix + "无法根据旧网点编号[" + vehicleMiddle.getOldPointCode() + "]找到对应的映射记录");
            throw syncException;
        }
        vehicle.setChannelId(pointMapping.getNewPointCode());

        //设置录入时间
        vehicle.setRegisteredTime(DateUtil.format(vehicleMiddle.getRegisteredTime()));

        //设置操作类型
        vehicle.setOperation(msg.getOperation());

        //TODO:过滤公务车
        //TODO:过滤存量失败车辆

        //发送最终映射customer 到部中心
        final Timestamp mappingEndTime = DateUtil.getCurrentSqlTimestamp();
        syncException.setMappingEndTime(mappingEndTime);
        syncException.setRequestTime(mappingEndTime);

        final String jsonStr = jsonService.getString(vehicle);
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
