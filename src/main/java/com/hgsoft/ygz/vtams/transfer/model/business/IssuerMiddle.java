package com.hgsoft.ygz.vtams.transfer.model.business;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.hgsoft.ygz.vtams.transfer.common.validation.CustomPattern;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Date;

/**
 * 发行方信息中间类
 * Table：TB_ISSUERUPLOAD
 *
 * @author zhangliang
 * @date 2017-10-09
 */
public class IssuerMiddle implements Serializable {

    private static final long serialVersionUID = 1L;

    @NotBlank(message = "发行方编号不能为空")
    @CustomPattern(regexp = "^[0-9]{6}$", message = "发行方编号必须由6位数字组成")
    private String id;

    @NotBlank(message = "发行方名称不能为空")
    @Size(max = 50, message = "发行方名称不能超过50个字符")
    private String name;

    @NotBlank(message = "联系人姓名不能为空")
    @Size(max = 50, message = "联系人姓名不能超过50个字符")
    private String contact;

    @NotBlank(message = "联系电话不能为空")
    @CustomPattern(regexp = "^[0-9\\-]{2,20}$", message = "联系电话无效")
    private String tel;

    @NotBlank(message = "地址不能为空")
    @Size(max = 100, message = "地址不能超过100个字符")
    private String address;

    @JsonFormat(pattern = "yyyy-MM-dd")
    @NotNull(message = "起始日期不能为空")
    private Date startTime;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date endTime;

    /**
     * 操作 ：1-新增，2-变更，3-删除
     */
    private Integer operation;

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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
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

    @Override
    public String toString() {
        return "Issuer [id=" + id + ", name=" + name + ", contact=" + contact
                + ", tel=" + tel + ", address=" + address + ", startTime="
                + startTime + ", endTime=" + endTime + ", operation="
                + operation + "]";
    }


}
