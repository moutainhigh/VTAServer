package com.hgsoft.ygz.vtams.transfer.model.business;

import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.io.Serializable;

/**
 * 用户卡黑名单中间类
 *
 * @author 赖少涯
 * @date 2017-10-17
 */
public class CardBlackMiddle implements Serializable {

    private static final long serialVersionUID = 1L;

    private String issuerId;

    @NotNull(message = "生成时间不能为空")
    private Long creationTime;

    @NotNull(message = "黑名单类型不能为空")
    @Pattern(regexp = "^[1-6]$", message = "黑名单类型只能是1-6")
    private Integer type;

    @NotBlank(message = "用户卡号不能为空")
    @Pattern(regexp = "^[0-9]{16}$", message = "用户卡号必须为16位数字")
    private String cardId;

    @NotNull(message = "用户卡状态不能为空")
    @Pattern(regexp = "^[12]$", message = "用户卡状态只能为1[进入黑名单]或2[解除黑名单]")
    private Integer status;

    public String getIssuerId() {
        return issuerId;
    }

    public void setIssuerId(String issuerId) {
        this.issuerId = issuerId;
    }

    public Long getCreationTime() {
        return creationTime;
    }

    public void setCreationTime(Long creationTime) {
        this.creationTime = creationTime;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getCardId() {
        return cardId;
    }

    public void setCardId(String cardId) {
        this.cardId = cardId;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }


    @Override
    public String toString() {
        return "CardBlack{" +
                "issuerId='" + issuerId + '\'' +
                ", creationTime='" + creationTime + '\'' +
                ", type=" + type +
                ", cardId='" + cardId + '\'' +
                ", status=" + status +
                '}';
    }
}
