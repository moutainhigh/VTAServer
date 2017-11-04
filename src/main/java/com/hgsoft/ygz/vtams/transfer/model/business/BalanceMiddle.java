package com.hgsoft.ygz.vtams.transfer.model.business;


import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.io.Serializable;

/**
 * 用户卡账余额中间类
 *
 * @author zhangliang
 * @date 2017/10/9
 */
public class BalanceMiddle implements Serializable {

    private static final long serialVersionUID = 1L;

    @NotBlank(message = "用户编号不能为空")
    @Pattern(regexp = "^[0-9]{20}$", message = "用户编号必须为20位数字")
    private String userId;

    @NotBlank(message = "用户卡号不能为空")
    @Pattern(regexp = "^[0-9]{16}$", message = "用户卡号必须为16位数字")
    private String cardId;

    @NotNull(message = "卡账余额不能为空")
    @Min(value = 1, message = "卡账余额必须大于0")
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
        return "BalanceMiddle [userId=" + userId + ", cardId=" + cardId
                + ", fee=" + fee + "]";
    }


}
