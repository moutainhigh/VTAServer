package com.hgsoft.ygz.vtams.transfer.model.business;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.io.Serializable;

/**
 * 充值交易信息中间类
 *
 * @author 赖少涯
 * @date 2017-11-04
 */
public class RechargeMiddle implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 充值交易标识
     */
    @NotBlank(message = "充值交易标识不能为空")
    @Pattern(regexp = "^[0-9]+$", message = "充值交易标识必须由数字组成")
    @JsonProperty("scAddSureId")
    private String oldRechargeId;

    /**
     * 实收金额
     */
    @NotNull(message = "实收金额不能为空")
    private Long paidAmount;

    /**
     * 赠送金额，单位：分
     */
    @NotNull(message = "赠送金额不能为空")
    private Long giftAmount;

    /**
     * 充值金额，单位：分
     */
    @NotNull(message = "充值金额不能为空")
    @Min(value = 1, message = "充值金额必须大于0")
    private Long rechargeAmount;

    /**
     * 卡片网络编号(4401) + CPU卡内部编号
     */
    @NotBlank(message = "用户卡号不能为空")
    @Pattern(regexp = "^[0-9]{16}$", message = "用户卡号必须为16位数字")
    private String cardId;

    /**
     * 渠道编号
     */
    @JsonProperty("customPointCode")
    @NotBlank(message = "渠道编号不能为空")
    @Pattern(regexp = "^[0-9A-Za-z]+$", message = "渠道编号必须由数字或字母组成")
    private String oldPointCode;

    @NotNull(message = "交易发生时间不能为空")
    private Long tradeTime;

    public String getOldRechargeId() {
        return oldRechargeId;
    }

    public void setOldRechargeId(String oldRechargeId) {
        this.oldRechargeId = oldRechargeId;
    }

    public Long getPaidAmount() {
        return paidAmount;
    }

    public void setPaidAmount(Long paidAmount) {
        this.paidAmount = paidAmount;
    }

    public Long getGiftAmount() {
        return giftAmount;
    }

    public void setGiftAmount(Long giftAmount) {
        this.giftAmount = giftAmount;
    }

    public Long getRechargeAmount() {
        return rechargeAmount;
    }

    public void setRechargeAmount(Long rechargeAmount) {
        this.rechargeAmount = rechargeAmount;
    }

    public String getCardId() {
        return cardId;
    }

    public void setCardId(String cardId) {
        this.cardId = cardId;
    }

    public String getOldPointCode() {
        return oldPointCode;
    }

    public void setOldPointCode(String oldPointCode) {
        this.oldPointCode = oldPointCode;
    }

    public Long getTradeTime() {
        return tradeTime;
    }

    public void setTradeTime(Long tradeTime) {
        this.tradeTime = tradeTime;
    }

    @Override
    public String toString() {
        return "RechargeMiddle{" +
                "oldRechargeId='" + oldRechargeId + '\'' +
                ", paidAmount=" + paidAmount +
                ", giftAmount=" + giftAmount +
                ", rechargeAmount=" + rechargeAmount +
                ", cardId='" + cardId + '\'' +
                ", oldPointCode='" + oldPointCode + '\'' +
                ", tradeTime=" + tradeTime +
                '}';
    }
}
