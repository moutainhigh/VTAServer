package com.hgsoft.ygz.vtams.transfer.model;

import java.io.Serializable;

/**
 * 用户卡账余额上传及变更
 *
 * @author zhangliang
 * @date 2017/10/9
 */
public class Balance implements Serializable {


    private static final long serialVersionUID = 1L;

    /**
     * 用户编号 = 发行方编号（6位数字）+用户注册年月日（6位数字）+流水顺序码（5数字）
     */
    private String userId;

    /**
     * CPU用户卡内部编号，20位字符长度
     */
    private String cardId;

    /**
     * 卡账余额，单位(分)
     */
    private Long fee;

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
        return "Balance [userId=" + userId + ", cardId=" + cardId + ", fee="
                + fee + "]";
    }


}
