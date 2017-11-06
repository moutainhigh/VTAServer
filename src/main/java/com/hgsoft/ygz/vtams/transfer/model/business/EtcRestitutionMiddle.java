package com.hgsoft.ygz.vtams.transfer.model.business;

import com.hgsoft.ygz.vtams.transfer.common.validation.CustomPattern;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.io.Serializable;

/**
 * 非现金补交交易信息中间类
 * Table：TB_REFEEUPLOAD
 *
 * @author 赖少涯
 * @date 2017-11-03
 */
public class EtcRestitutionMiddle implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 非现金补交交易编号
     */
    @NotBlank(message = "非现金补交交易编号不能为空")
    private String id;

    /**
     * 补交金额，单位:分
     */
    @NotNull(message = "补交金额不能为空")
    @Min(value = 1, message = "补交金额必须大于0")
    private Long fee;

    /**
     * 补交交易产生时间:yyyy-MM-ddTHH:mm:ss
     */
    @NotBlank(message = "补交交易产生时间不能为空")
    private String effectiveTime;

    /**
     * 拆分收费路段总数:大于0的整数
     */
    @NotNull(message = "拆分收费路段总数不能为空")
    @Min(value = 1, message = "拆分收费路段总数必须大于0")
    private Integer sectionCount;

    /**
     * 拆分时间:yyyy-MM-ddTHH:mm:ss
     */
    @NotBlank(message = "拆分时间不能为空")
    private String splitTime;

    /**
     * 业务单据号
     */
    @NotBlank(message = "业务单据号不能为空")
    private String orderNum;

    /**
     * 现场开票标识:1为已开报销凭证，2位未开报销凭证
     */
    @NotNull(message = "现场开票标识不能为空")
    @CustomPattern(regexp = "^[12]$", message = "退费类型只能为1[已开报销凭证]或2[未开报销凭证]")
    private Integer identification;

    /**
     * 退费对应正确交易流水拆分结果:json字符串
     */
    @NotBlank(message = "拆分明细不能为空")
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
