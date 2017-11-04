package com.hgsoft.ygz.vtams.transfer.model.business;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.hgsoft.ygz.vtams.transfer.common.validation.DateCompare;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Date;

/**
 * 客服合作机构中间类
 *
 * @author 赖少涯
 * @date 2017-10-23
 */
public class AgencyMiddle implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 客服合作机构编号，11位
     */
    @NotBlank(message = "合作机构编号不能为空")
    @Pattern(regexp = "^[0-9]{11}$", message = "合作机构编号必须为11位数字")
    private String id;

    /**
     * 合作机构名称
     */
    @NotBlank(message = "合作机构名称不能为空")
    @Size(max = 50, message = "合作机构名称不能超过50个字符")
    private String name;

    /**
     * 合作机构类型
     */
    @JsonIgnore
    private String agencyType;

    /**
     * 联系人姓名
     */
    @NotBlank(message = "联系人姓名不能为空")
    @Size(max = 50, message = "联系人姓名不能超过50个字符")
    private String contact;

    /**
     * 联系电话
     */
    @NotBlank(message = "联系电话不能为空")
    @Size(max = 20, message = "联系电话不能超过20位")
    private String tel;

    /**
     * 起始日期
     */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @NotNull(message = "起始日期不能为空")
    private Date startTime;

    /**
     * 终止日期
     */
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date endTime;

    /**
     * 操作 ：1:新增，2:变更，3:删除 <br/>
     * 该字段并不会保存在数据库中，仅仅在发送的时候需要
     */
    private Integer operation;

    /**
     * 是否可用
     */
    @JsonIgnore
    private String enabledFlag;

    /**
     * 操作者
     */
    @JsonIgnore
    private String operator;

    @JsonIgnore
    private String issuerId;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
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

    public String getAgencyType() {
        return agencyType;
    }

    public void setAgencyType(String agencyType) {
        this.agencyType = agencyType;
    }

    public String getEnabledFlag() {
        return enabledFlag;
    }

    public void setEnabledFlag(String enabledFlag) {
        this.enabledFlag = enabledFlag;
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    public String getIssuerId() {
        return issuerId;
    }

    public void setIssuerId(String issuerId) {
        this.issuerId = issuerId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (null == o) {
            return false;
        }

        if (this.getClass() != o.getClass()) {
            return false;
        }

        AgencyMiddle agency = (AgencyMiddle) o;

        return getId() != null ? getId().equals(agency.getId()) : agency.getId() == null;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + (getId() != null ? getId().hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "AgencyMiddle{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", agencyType='" + agencyType + '\'' +
                ", contact='" + contact + '\'' +
                ", tel='" + tel + '\'' +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                ", operation=" + operation +
                ", enabledFlag='" + enabledFlag + '\'' +
                '}';
    }
}
