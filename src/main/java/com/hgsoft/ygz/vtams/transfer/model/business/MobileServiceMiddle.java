package com.hgsoft.ygz.vtams.transfer.model.business;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.io.Serializable;
import java.util.Date;

/**
 * 流动服务网点信息上传及变更
 *
 * @author 赖少涯
 * @date 2017-10-28
 */
public class MobileServiceMiddle implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 原有的网点编号
     */
    @NotBlank(message = "旧网点编号不能为空")
    @Pattern(regexp = "^[0-9A-Za-z]+$", message = "旧网点编号必须由数字或字母组成")
    private String originalCode;

    /**
     * 合作机构编号
     */
    @NotBlank(message = "合作机构编号不能为空")
    @Pattern(regexp = "^[0-9]{11}$", message = "合作机构编号必须为11位数字")
    private String agencyId;

    @JsonFormat(pattern = "yyyy-MM-dd")
    @NotNull(message = "起始日期不能为空")
    private Date startTime;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date endTime;

    private Integer operation;

    public String getOriginalCode() {
        return originalCode;
    }

    public void setOriginalCode(String originalCode) {
        this.originalCode = originalCode;
    }

    public String getAgencyId() {
        return agencyId;
    }

    public void setAgencyId(String agencyId) {
        this.agencyId = agencyId;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public Integer getOperation() {
        return operation;
    }

    public void setOperation(Integer operation) {
        this.operation = operation;
    }

}
