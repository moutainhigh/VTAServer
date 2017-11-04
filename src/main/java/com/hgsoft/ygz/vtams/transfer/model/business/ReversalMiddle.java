package com.hgsoft.ygz.vtams.transfer.model.business;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.io.Serializable;

/**
 * 冲正交易信息
 *
 * @author 赖少涯
 * @date 2017-11-04
 */
public class ReversalMiddle implements Serializable {


    private static final long serialVersionUID = 1L;

    /**
     * 充值交易标识
     */
    @NotBlank(message = "充值交易标识不能为空")
    @Pattern(regexp = "^[0-9]+$", message = "充值交易标识必须由数字组成")
    @JsonProperty("id")
    private String oldRechargeId;

    /**
     * 冲正交易时间
     */
    @NotNull(message = "冲正交易时间不能为空")
    private Long effectiveTime;

    /**
     * 卡号
     */
    @NotBlank(message = "用户卡号不能为空")
    @Pattern(regexp = "^[0-9]{16}$", message = "用户卡号必须为16位数字")
    private String cardId;

    public String getOldRechargeId() {
        return oldRechargeId;
    }

    public void setOldRechargeId(String oldRechargeId) {
        this.oldRechargeId = oldRechargeId;
    }

    public Long getEffectiveTime() {
        return effectiveTime;
    }

    public void setEffectiveTime(Long effectiveTime) {
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
        return "ReversalMiddle{" +
                "oldRechargeId='" + oldRechargeId + '\'' +
                ", effectiveTime=" + effectiveTime +
                ", cardId='" + cardId + '\'' +
                '}';
    }
}
