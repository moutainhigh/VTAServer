package com.hgsoft.ygz.vtams.transfer.model;

import java.io.Serializable;

/**
 * obu信息上传及变更
 *
 * @author 赖少涯
 * @date 2017-11-03
 */
public class Obu implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * OBU序号编码，16位,必填
     */
    private String id;

    /**
     * obu品牌，必填
     */
    private Integer brand;

    /**
     * 型号，必填
     */
    private String model;

    /**
     * 客户编号,必填
     */
    private String userId;

    /**
     * 车辆编号,必填
     */
    private String vehicleId;

    /**
     * OBU启用时间:yyyy-MM-ddTHH:mm:ss,必填
     */
    private String enableTime;

    /**
     * OBU到期时间：yyyy-MM-dd,必填
     */
    private String expireTime;

    /**
     * OBU注册方式，1：线上 2：线下,必填
     */
    private Integer registeredType;

    /**
     * OBU注册渠道编号，必填
     */
    private String registeredChannelId;

    /**
     * OBU注册时间:yyyy-MM-ddTHH:mm:ss，必填
     */
    private String registeredTime;

    /**
     * OBU安装方式，1：自行安装 2：网点安装，必填
     */
    private Integer installType;

    /**
     * OBU安装/激活，非必填
     */
    private String installChannelId;

    /**
     * OBU安装/激活时间:yyyy-MM-ddTHH:mm:ss，必填
     */
    private String installTime;

    /**
     * OBU状态,1-8，必填
     */
    private Integer status;

    /**
     * OBU状态变更时间:yyyy-MM-ddTHH:mm:ss，必填
     */
    private String statusChangeTime;

    /**
     * 操作，1-新增 2-变更 3-删除
     */
    private Integer operation;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getBrand() {
        return brand;
    }

    public void setBrand(Integer brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getVehicleId() {
        return vehicleId;
    }

    public void setVehicleId(String vehicleId) {
        this.vehicleId = vehicleId;
    }

    public String getEnableTime() {
        return enableTime;
    }

    public void setEnableTime(String enableTime) {
        this.enableTime = enableTime;
    }

    public String getExpireTime() {
        return expireTime;
    }

    public void setExpireTime(String expireTime) {
        this.expireTime = expireTime;
    }

    public Integer getRegisteredType() {
        return registeredType;
    }

    public void setRegisteredType(Integer registeredType) {
        this.registeredType = registeredType;
    }

    public String getRegisteredChannelId() {
        return registeredChannelId;
    }

    public void setRegisteredChannelId(String registeredChannelId) {
        this.registeredChannelId = registeredChannelId;
    }

    public String getRegisteredTime() {
        return registeredTime;
    }

    public void setRegisteredTime(String registeredTime) {
        this.registeredTime = registeredTime;
    }

    public Integer getInstallType() {
        return installType;
    }

    public void setInstallType(Integer installType) {
        this.installType = installType;
    }

    public String getInstallChannelId() {
        return installChannelId;
    }

    public void setInstallChannelId(String installChannelId) {
        this.installChannelId = installChannelId;
    }

    public String getInstallTime() {
        return installTime;
    }

    public void setInstallTime(String installTime) {
        this.installTime = installTime;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getStatusChangeTime() {
        return statusChangeTime;
    }

    public void setStatusChangeTime(String statusChangeTime) {
        this.statusChangeTime = statusChangeTime;
    }

    public Integer getOperation() {
        return operation;
    }

    public void setOperation(Integer operation) {
        this.operation = operation;
    }

    @Override
    public String toString() {
        return "Obu{" +
                "id='" + id + '\'' +
                ", brand=" + brand +
                ", model='" + model + '\'' +
                ", userId='" + userId + '\'' +
                ", vehicleId='" + vehicleId + '\'' +
                ", enableTime='" + enableTime + '\'' +
                ", expireTime='" + expireTime + '\'' +
                ", registeredType=" + registeredType +
                ", registeredChannelId='" + registeredChannelId + '\'' +
                ", registeredTime='" + registeredTime + '\'' +
                ", installType=" + installType +
                ", installChannelId='" + installChannelId + '\'' +
                ", installTime='" + installTime + '\'' +
                ", status=" + status +
                ", statusChangeTime='" + statusChangeTime + '\'' +
                ", operation=" + operation +
                '}';
    }
}
