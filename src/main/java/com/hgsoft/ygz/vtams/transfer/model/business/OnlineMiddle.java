package com.hgsoft.ygz.vtams.transfer.model.business;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Date;

/**
 * 线上服务渠道信息上传及变更
 *
 * @author 赖少涯
 * @date 2017-10-28
 */
public class OnlineMiddle implements Serializable {

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

    /**
     * 线上渠道方式
     */
    @NotBlank(message = "线上渠道方式不能为空")
    @Pattern(regexp = "^[0-9]{2}$", message = "线上渠道方式必须为2位数字")
    private String onlineChannelType;

    /**
     * 线上服务渠道名称
     */
    @NotBlank(message = "线上服务渠道名称不能为空")
    @Size(max = 50, message = "线上服务渠道名称不能超过50个字符")
    private String name;

    /**
     * 服务项目
     */
    @NotBlank(message = "服务项目不能为空")
    @Size(min = 2, max = 150, message = "服务项目为2-100个字符")
    @Pattern(regexp = "^([0-9]{2},)*([0-9]{2})+$", message = "多个服务项目必须由英文逗号分隔")
    private String serviceItems;

    @JsonFormat(pattern = "yyyy-MM-dd")
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getServiceItems() {
        return serviceItems;
    }

    public void setServiceItems(String serviceItems) {
        this.serviceItems = serviceItems;
    }

    public Integer getOperation() {
        return operation;
    }

    public void setOperation(Integer operation) {
        this.operation = operation;
    }

    public String getAgencyId() {
        return agencyId;
    }

    public void setAgencyId(String agencyId) {
        this.agencyId = agencyId;
    }

    public String getOnlineChannelType() {
        return onlineChannelType;
    }

    public void setOnlineChannelType(String onlineChannelType) {
        this.onlineChannelType = onlineChannelType;
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
}
