package com.hgsoft.ygz.vtams.transfer.model;

import java.io.Serializable;

/**
 * 其他补交交易信息
 * Table：TB_REFEEUPLOAD
 *
 * @author 赖少涯
 * @date 2017-11-03
 */
public class OtherRestitution implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 交易编号
     */
    private String id;

    /**
     * 补交金额，单位:分
     */
    private Long fee;

    /**
     * 补交交易产生时间:yyyy-MM-ddTHH:mm:ss
     */
    private String effectiveTime;

    /**
     * 入口车道编号
     */
    private String enTollLaneId;

    /**
     * 实际收费车辆号码+颜色
     */
    private String vehicleId;

    /**
     * 收费车型
     */
    private Integer vehicleType;

    /**
     * 拆分收费路段总数:大于0的整数
     */
    private Integer sectionCount;

    /**
     * 拆分时间:yyyy-MM-ddTHH:mm:ss
     */
    private String splitTime;

    /**
     * 业务单据号
     */
    private String orderNum;

    /**
     * 现场开票标识:1为已开报销凭证，2位未开报销凭证
     */
    private Integer identification;

    /**
     * 拆分结果:json字符串
     */
    private String details;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Long getFee() {
        return fee;
    }

    public void setFee(Long fee) {
        this.fee = fee;
    }

    public String getEffectiveTime() {
        return effectiveTime;
    }

    public void setEffectiveTime(String effectiveTime) {
        this.effectiveTime = effectiveTime;
    }

    public String getEnTollLaneId() {
        return enTollLaneId;
    }

    public void setEnTollLaneId(String enTollLaneId) {
        this.enTollLaneId = enTollLaneId;
    }

    public String getVehicleId() {
        return vehicleId;
    }

    public void setVehicleId(String vehicleId) {
        this.vehicleId = vehicleId;
    }

    public Integer getVehicleType() {
        return vehicleType;
    }

    public void setVehicleType(Integer vehicleType) {
        this.vehicleType = vehicleType;
    }

    public Integer getSectionCount() {
        return sectionCount;
    }

    public void setSectionCount(Integer sectionCount) {
        this.sectionCount = sectionCount;
    }

    public String getSplitTime() {
        return splitTime;
    }

    public void setSplitTime(String splitTime) {
        this.splitTime = splitTime;
    }

    public String getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(String orderNum) {
        this.orderNum = orderNum;
    }

    public Integer getIdentification() {
        return identification;
    }

    public void setIdentification(Integer identification) {
        this.identification = identification;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    @Override
    public String toString() {
        return "OtherRestitution{" +
                "id='" + id + '\'' +
                ", fee=" + fee +
                ", effectiveTime='" + effectiveTime + '\'' +
                ", enTollLaneId='" + enTollLaneId + '\'' +
                ", vehicleId='" + vehicleId + '\'' +
                ", vehicleType='" + vehicleType + '\'' +
                ", sectionCount=" + sectionCount +
                ", splitTime='" + splitTime + '\'' +
                ", orderNum='" + orderNum + '\'' +
                ", identification=" + identification +
                ", details='" + details + '\'' +
                '}';
    }
}
