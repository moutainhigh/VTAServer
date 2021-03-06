package com.hgsoft.ygz.vtams.transfer.model;

import java.io.Serializable;

/**
 * 非现金补交交易信息
 * Table：TB_ETCRESTITUTIONUPLOAD
 *
 * @author 赖少涯
 * @date 2017-11-03
 */
public class EtcRestitution implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 非现金补交交易编号
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
     * 退费对应正确交易流水拆分结果:json字符串
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
        return "EtcRestitution{" +
                "id='" + id + '\'' +
                ", fee=" + fee +
                ", effectiveTime='" + effectiveTime + '\'' +
                ", sectionCount=" + sectionCount +
                ", splitTime='" + splitTime + '\'' +
                ", orderNum='" + orderNum + '\'' +
                ", identification=" + identification +
                ", details='" + details + '\'' +
                '}';
    }
}
