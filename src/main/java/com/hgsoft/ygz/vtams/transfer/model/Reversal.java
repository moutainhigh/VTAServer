package com.hgsoft.ygz.vtams.transfer.model;

import java.io.Serializable;

/**
 * 冲正交易信息
 *
 * @author zhangliang
 * @date 2017/10/21
 */
public class Reversal implements Serializable {


    private static final long serialVersionUID = 1L;

    /**
     * 冲正交易流水编号 = 充值交易编号 + 冲正标识('1')
     */
    private String id;

    /**
     * 冲正交易时间:yyyy-MM-ddTHH:mm:ss
     */
    private String effectiveTime;

    /**
     * 卡号
     */
    private String cardId;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEffectiveTime() {
        return effectiveTime;
    }

    public void setEffectiveTime(String effectiveTime) {
        this.effectiveTime = effectiveTime;
    }

    public String getCardId() {
        return cardId;
    }

    public void setCardId(String cardId) {
        this.cardId = cardId;
    }

    @Override
    public String toString() {
        return "Reversal{" +
                "id='" + id + '\'' +
                ", effectiveTime='" + effectiveTime + '\'' +
                ", cardId='" + cardId + '\'' +
                '}';
    }
}
