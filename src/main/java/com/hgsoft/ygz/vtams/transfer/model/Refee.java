package com.hgsoft.ygz.vtams.transfer.model;

import java.io.Serializable;

/**
 * 退费交易信息
 * Table：TB_REFEEUPLOAD
 *
 * @author 赖少涯
 * @date 2017-11-03
 */
public class Refee implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 退费交易编号
     */
    private String id;

    /**
     * 退费类型
     */
    private Integer type;

    /**
     * 退费金额=应收金额-实收金额，单位分，负数
     */
    private Long fee;

    /**
     * 退费交易产生时间:yyyy-MM-ddTHH:mm:ss
     */
    private String effectiveTime;

    /**
     * 拆分路段总数:大于0的整数
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
     * 现场开票标识,1:已开报销凭证,2:未开报销凭证
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

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
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
        return "Refee{" +
                "id='" + id + '\'' +
                ", type=" + type +
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
