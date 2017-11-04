package com.hgsoft.ygz.vtams.transfer.model;

import java.io.Serializable;

/**
 * 充值交易信息
 *
 * @author 赖少涯
 * @date 2017-11-04
 */
public class Recharge implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 充值交易流水编号 = 渠道类型（1位）+渠道编号（20位）+交易发生的时间（14位yyyyMMddHHmmss）+流水号（2位）
     */
    private String id;

    /**
     * 实收金额
     */
    private Long paidAmount;

    /**
     * 赠送金额，单位：分
     */
    private Long giftAmount;

    /**
     * 充值金额，单位：分
     */
    private Long rechargeAmount;

    /**
     * 卡片网络编号(4401) + CPU卡内部编号
     */
    private String cardId;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    @Override
    public String toString() {
        return "Recharge{" +
                "id='" + id + '\'' +
                ", paidAmount=" + paidAmount +
                ", giftAmount=" + giftAmount +
                ", rechargeAmount=" + rechargeAmount +
                ", cardId='" + cardId + '\'' +
                '}';
    }
}
