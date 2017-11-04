package com.hgsoft.ygz.vtams.transfer.model;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * OBU黑名单：上传及变更，非实时 <br/>
 * Table：TB_OBUBLACKLISTUPLOAD
 *
 * @author laishaoya
 * @date 2017-10-09
 */
public class ObuBlack implements Serializable {

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
     * 类型,1:标签挂失，2:无卡挂起,3:无签注销,4:车型不符，必填
     */
    private Integer type;

    /**
     * OBU序号编码,16位字符长度，必填
     */
    @JsonProperty("OBUId")
    private String obuId;

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

    public String getObuId() {
        return obuId;
    }

    public void setObuId(String obuId) {
        this.obuId = obuId;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "OBUBlack{" +
                "issuerId='" + issuerId + '\'' +
                ", creationTime='" + creationTime + '\'' +
                ", type=" + type +
                ", obuId='" + obuId + '\'' +
                ", status=" + status +
                '}';
    }
}
