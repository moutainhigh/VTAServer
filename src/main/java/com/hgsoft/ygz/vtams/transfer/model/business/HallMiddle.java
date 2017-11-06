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
 * 服务网点信息上传及变更中间类
 *
 * @author 赖少涯
 * @date 2017-10-28
 */
public class HallMiddle implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 原有的网点编号
     */
    @NotBlank(message = "旧网点编号不能为空")
    @CustomPattern(regexp = "^[0-9A-Za-z]+$", message = "旧网点编号必须由数字或字母组成")
    private String originalCode;

    /**
     * 合作机构编号
     */
    @NotBlank(message = "合作机构编号不能为空")
    @CustomPattern(regexp = "^[0-9]{11}$", message = "合作机构编号必须为11位数字")
    private String agencyId;

    @NotBlank(message = "地市编号不能为空")
    @CustomPattern(regexp = "^[0-9]{2}$", message = "地市编号必须为2位数字")
    private String cityId;

    @NotBlank(message = "区县编号不能为空")
    @CustomPattern(regexp = "^[0-9]{2}$", message = "区县编号必须为2位")
    private String countyId;

    /**
     * 服务网点名称
     */
    @NotBlank(message = "网点名称不能为空")
    @Size(max = 50, message = "网点名称不能超过50个字符")
    private String name;

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
    @CustomPattern(regexp = "^[0-9\\-]{2,20}$", message = "联系电话无效")
    private String tel;

    /**
     * 地址
     */
    @NotBlank(message = "地址不能为空")
    @Size(max = 100, message = "地址不能超过100个字符")
    private String address;

    /**
     * 服务项目
     */
    @NotBlank(message = "服务项目不能为空")
    @Size(min = 2, max = 150, message = "服务项目为2-100个字符")
    @CustomPattern(regexp = "^([0-9]{2},)*([0-9]{2})+$", message = "多个服务项目必须由英文逗号分隔")
    private String serviceItems;

    /**
     * 营业时间
     */
    private String businessHours;

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
     * 操作 ：1-新增，2-变更，3-删除
     */
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

    public String getCityId() {
        return cityId;
    }

    public void setCityId(String cityId) {
        this.cityId = cityId;
    }

    public String getCountyId() {
        return countyId;
    }

    public void setCountyId(String countyId) {
        this.countyId = countyId;
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

    public String getServiceItems() {
        return serviceItems;
    }

    public void setServiceItems(String serviceItems) {
        this.serviceItems = serviceItems;
    }

    public String getBusinessHours() {
        return businessHours;
    }

    public void setBusinessHours(String businessHours) {
        this.businessHours = businessHours;
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
