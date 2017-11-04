package com.hgsoft.ygz.vtams.transfer.model;

import java.io.Serializable;

/**
 * 退款交易：上传，非实时 <br/>
 * Table:TB_REFUNDUPLOAD
 *
 * @author 赖少涯
 * @date 2017-10-09
 */
public class Refund implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 退款交易编号，37位，渠道类型(1位)+渠道编号（20位）+退款发生时间（14位）+流水号（2位顺序码），必填
     */
    private String id;

    /**
     * 客户编号，17位，必填
     */
    private String userId;

    /**
     * 用户卡编号，20位，卡片网络编号（4位）+CPU卡内部编号（16位），必填
     */
    private String cardId;

    /**
     * 退款金额，单位:分，必填
     */
    private Long fee;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
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

    @Override
    public String toString() {
        return "Refund{" +
                "id='" + id + '\'' +
                ", userId='" + userId + '\'' +
                ", cardId='" + cardId + '\'' +
                ", fee=" + fee +
                '}';
    }
}
