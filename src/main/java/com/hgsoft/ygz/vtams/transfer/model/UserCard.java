package com.hgsoft.ygz.vtams.transfer.model;

import java.io.Serializable;

/**
 * 用户卡信息
 *
 * @author 赖少涯
 * @date 2017-11-03 16:30:55
 */
public class UserCard implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 用户卡编号 = 卡片网络编号(4位)+CPU卡内部编号（16位）
     */
    private String id;

    /**
     * 卡类型，3位数字
     */
    private Integer cardType;

    /**
     * 卡品牌，1位或2位数字
     */
    private Integer brand;

    /**
     * 卡型号，不超过100位，非必填
     */
    private String model;

    /**
     * 客服合作机构编号
     */
    private String agencyId;

    /**
     * 客户编号
     */
    private String userId;

    /**
     * 车辆编号
     */
    private String vehicleId;

    /**
     * 卡启用时间:yyyy-MM-ddTHH:mm:ss
     */
    private String enableTime;

    /**
     * 卡到期时间:yyyy-MM-ddTHH:mm:ss
     */
    private String expireTime;

    /**
     * 开卡方式，1：线上 2：线下
     */
    private Integer issuedType;

    /**
     * 开卡渠道编号,线下录入=>服务网点编号,线上录入=>线上服务渠道编号
     */
    private String channelId;

    /**
     * 开卡时间:yyyy-MM-ddTHH:mm:ss
     */
    private String issuedTime;

    /**
     * 用户卡状态
     */
    private Integer status;

    /**
     * 用户卡状态变更时间:yyyy-MM-ddTHH:mm:ss
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

    public Integer getCardType() {
        return cardType;
    }

    public void setCardType(Integer cardType) {
        this.cardType = cardType;
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

    public String getAgencyId() {
        return agencyId;
    }

    public void setAgencyId(String agencyId) {
        this.agencyId = agencyId;
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

    public Integer getIssuedType() {
        return issuedType;
    }

    public void setIssuedType(Integer issuedType) {
        this.issuedType = issuedType;
    }

    public String getChannelId() {
        return channelId;
    }

    public void setChannelId(String channelId) {
        this.channelId = channelId;
    }

    public String getIssuedTime() {
        return issuedTime;
    }

    public void setIssuedTime(String issuedTime) {
        this.issuedTime = issuedTime;
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
}
