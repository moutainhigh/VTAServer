package com.hgsoft.ygz.vtams.transfer.model;

import java.io.Serializable;

/**
 * 用户卡黑名单：上传及变更，非实时 <br/>
 * Table：TB_CARDBLACKLISTUPLOAD
 *
 * @author 赖少涯
 * @date 2017-10-09
 */
public class CardBlack implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 发行方编号,6位数字，如：440101，必填
     */
    private String issuerId;

    /**
     * 生成时间,YYYY-MM-DDTHH:mm:ss，必填
     */
    private String creationTime;

    /**
     * 类型,1:卡挂失，2:无卡挂起,3:无卡注销,4:账户透支,5:合作机构黑名单,6:车型不符，必填
     */
    private Integer type;

    /**
     * 用户卡编号,4位卡片网络编号+16位CPU卡内部编号，必填
     */
    private String cardId;

    /**
     * 状态,1:进入黑名单,2:解除黑名单，必填
     */
    private Integer status;

    public String getIssuerId() {
        return issuerId;
    }

    public void setIssuerId(String issuerId) {
        this.issuerId = issuerId;
    }

    public String getCreationTime() {
        return creationTime;
    }

    public void setCreationTime(String creationTime) {
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
