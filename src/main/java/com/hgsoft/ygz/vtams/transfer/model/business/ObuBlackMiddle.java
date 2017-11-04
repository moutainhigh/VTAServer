package com.hgsoft.ygz.vtams.transfer.model.business;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.io.Serializable;

/**
 * OBU黑名单中间类
 *
 * @author 赖少涯
 * @date 2017-11-03
 */
public class ObuBlackMiddle implements Serializable {

    private static final long serialVersionUID = 1L;

    private String issuerId;

    @NotNull(message = "生成时间不能为空")
    private Long creationTime;

    @NotNull(message = "黑名单类型不能为空")
    @Pattern(regexp = "^[1-4]$", message = "黑名单类型只能是1-4")
    private Integer type;

    @JsonProperty("OBUId")
    @NotBlank(message = "obu编号不能为空")
    @Pattern(regexp = "^[0-9]{16}$", message = "obu编号必须为16位数字")
    private String obuId;

    @NotNull(message = "obu状态不能为空")
    @Pattern(regexp = "^[12]$", message = "obu状态只能为1[进入黑名单]或2[解除黑名单]")
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
        return "ObuBlackMiddle [issuerId=" + issuerId + ", creationTime="
                + creationTime + ", type=" + type + ", obuId=" + obuId
                + ", status=" + status + "]";
    }


}
