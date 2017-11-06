package com.hgsoft.ygz.vtams.transfer.model.business;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.hgsoft.ygz.vtams.transfer.common.validation.CustomPattern;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.io.Serializable;

/**
 * 退款交易中间类
 *
 * @author 赖少涯
 * @date 2017-11-03
 */
public class RefundMiddle implements Serializable {

    private static final long serialVersionUID = 1L;

    @NotBlank(message = "网点编号不能为空")
    @CustomPattern(regexp = "^[0-9A-Za-z]+$", message = "网点编号必须由数字或字母组成")
    @JsonProperty("customPointCode")
    private String oldPointCode;

    @NotBlank(message = "用户编号不能为空")
    @CustomPattern(regexp = "^[0-9]{20}$", message = "用户编号必须由20位数字组成")
    @JsonProperty("userId")
    private String oldUserNo;

    @NotBlank(message = "用户卡号不能为空")
    @CustomPattern(regexp = "^[0-9]{16}$", message = "用户卡号必须为16位数字")
    private String cardId;

    @NotNull(message = "退款金额不能为空")
    @Min(value = 1, message = "退款金额必须大于0")
    private Long fee;

    /**
     * 退款发生时间
     */
    @NotNull(message = "退款发生时间不能为空")
    private Long refundTime;

    public String getOldPointCode() {
        return oldPointCode;
    }

    public void setOldPointCode(String oldPointCode) {
        this.oldPointCode = oldPointCode;
    }

    public String getOldUserNo() {
        return oldUserNo;
    }

    public void setOldUserNo(String oldUserNo) {
        this.oldUserNo = oldUserNo;
    }

    public String getCardId() {
        return cardId;
    }

    public void setCardId(String cardId) {
        this.cardId = cardId;
    }

    public Long getFee() {
        return fee;
    }

    public void setFee(Long fee) {
        this.fee = fee;
    }

    public Long getRefundTime() {
        return refundTime;
    }

    public void setRefundTime(Long refundTime) {
        this.refundTime = refundTime;
    }

    @Override
    public String toString() {
        return "RefundMiddle{" +
                "oldPointCode='" + oldPointCode + '\'' +
                ", oldUserNo='" + oldUserNo + '\'' +
                ", cardId='" + cardId + '\'' +
                ", fee=" + fee +
                ", refundTime=" + refundTime +
                '}';
    }
}
