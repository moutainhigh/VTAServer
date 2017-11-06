package com.hgsoft.ygz.vtams.transfer.model.business;


import com.hgsoft.ygz.vtams.transfer.common.validation.CustomPattern;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.io.Serializable;

import static com.hgsoft.ygz.vtams.transfer.common.validation.CustomGroup.*;

/**
 * 客户信息上传及变更 中间类
 *
 * @author 赖少涯
 * @date 2017-11-02
 */
public class CustomerMiddle implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 旧客户编号 20位
     */
    @NotBlank(message = "客户编号不能为空", groups = {Primary.class})
    @CustomPattern(regexp = "^[0-9]{20}$", message = "客户编号必须由20位数字组成", groups = {Primary.class})
    private String id;

    /**
     * 客户类型，1位，1:个人,2:单位，必填
     */
    @NotNull(message = "客户类型不能为空", groups = {Primary.class})
    @CustomPattern(regexp = "(1|2)", message = "客户类型只能填1[个人]或2[单位]", groups = {Primary.class})
    private Integer userType;

    /**
     * 开户人名称，不超过50个字符，必填
     */
    @NotBlank(message = "开户人名称不能为空", groups = {Primary.class})
    @Size(max = 50, message = "开户人名称不能超过50个字符", groups = {Primary.class})
    private String userName;

    /**
     * 开户人证件类型，3位数字，必填
     */
    @NotNull(message = "开户人证件类型不能为空", groups = {Primary.class})
    @CustomPattern(regexp = "^[0-9]{3}$", message = "开户人证件类型必须由3位数字组成", groups = {Primary.class})
    private Integer userIdType;

    /**
     * 开户人证件号码，不超过30个字符，必填
     */
    @NotBlank(message = "开户人证件号码不能为空", groups = {Primary.class})
    @Size(max = 30, message = "开户人证件号码不能超过30个字符", groups = {Primary.class})
    private String userIdNum;

    /**
     * 开户人/指定经办人电号码，必填
     */
    @NotBlank(message = "开户人/指定经办人电话不能为空", groups = {Primary.class})
    @CustomPattern(regexp = "^[0-9\\-]{2,20}", message = "开户人/指定经办人电话电话无效", groups = {Primary.class})
    private String tel;

    /**
     * 开户人地址，不超过100个字符，必填
     */
    @NotBlank(message = "地址不能为空", groups = {Primary.class})
    @Size(max = 100, message = "地址不能超过100个字符", groups = {Primary.class})
    private String address;

    /**
     * 开户方式，1:线上,2:线下，必填
     */
    @NotNull(message = "开户方式不能为空", groups = {Primary.class})
    @CustomPattern(regexp = "(1|2)", message = "开户方式只能填1[线上]或2[线下]", groups = {Primary.class})
    private Integer registeredType;

    /**
     * 开户渠道编号
     */
    @NotBlank(message = "开户渠道编号不能为空", groups = {Primary.class})
    @CustomPattern(regexp = "(^[0-9A-Za-z]{7}$)|(^[0-9A-Za-z]{9}$)", message = "开户渠道编号必须为7位或9位字符", groups = {Primary.class})
    private String channelId;

    /**
     * 开户时间，时间戳
     */
    @NotNull(message = "开户时间不能为空", groups = {Primary.class})
    private Long registeredTime;

    /**
     * 部门/分支机构名称，单位用户必填
     */
    @NotBlank(message = "单位用户部门名称不能为空", groups = {Secondary.class})
    @CustomPattern(regexp = "SAWYER", message = "你只能是我的", groups = {Secondary.class})
    private String department;

    /**
     * 指定经办人姓名,不超过50个字符，单位用户必填
     */
    @NotBlank(message = "单位用户指定经办人姓名不能为空", groups = {Secondary.class})
    @Size(max = 50, message = "单位用户指定经办人姓名不能超过50个字符", groups = {Secondary.class})
    private String agentName;

    /**
     * 指定经办人证件类型，3位，单位用户必填
     */
    @NotNull(message = "单位用户指定经办人证件类型不能为空", groups = {Secondary.class})
    @CustomPattern(regexp = "^[0-9]{3}", message = "单位用户证件类型必须为3位", groups = {Secondary.class})
    private Integer agentIdType;

    /**
     * 指定经办人证件号,不超过30位，单位用户必填
     */
    @NotBlank(message = "单位用户指定经办人证件号不能为空", groups = {Secondary.class})
    @Size(max = 30, message = "单位用户指定经办人证件号不能超过30个字符", groups = {Secondary.class})
    private String agentldNum;

    /**
     * 客户状态，1:在用，2:注销，必填
     */
    @NotNull(message = "客户状态不能为空", groups = {Primary.class})
    @CustomPattern(regexp = "(1|2)", message = "客户状态只能填1[在用]或2[注销]", groups = {Primary.class})
    private Integer status;

    /**
     * 客户状态变更时间,时间戳
     */
    @NotNull(message = "客户状态变更时间不能为空", groups = {Primary.class})
    private Long statusChangeTime;

    /**
     * 操作，1:新增，2:变更，3:删除，必填
     */
    private Integer operation;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getUserType() {
        return userType;
    }

    public void setUserType(Integer userType) {
        this.userType = userType;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Integer getUserIdType() {
        return userIdType;
    }

    public void setUserIdType(Integer userIdType) {
        this.userIdType = userIdType;
    }

    public String getUserIdNum() {
        return userIdNum;
    }

    public void setUserIdNum(String userIdNum) {
        this.userIdNum = userIdNum;
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

    public Integer getRegisteredType() {
        return registeredType;
    }

    public void setRegisteredType(Integer registeredType) {
        this.registeredType = registeredType;
    }

    public String getChannelId() {
        return channelId;
    }

    public void setChannelId(String channelId) {
        this.channelId = channelId;
    }


    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getAgentName() {
        return agentName;
    }

    public void setAgentName(String agentName) {
        this.agentName = agentName;
    }

    public Integer getAgentIdType() {
        return agentIdType;
    }

    public void setAgentIdType(Integer agentIdType) {
        this.agentIdType = agentIdType;
    }

    public String getAgentldNum() {
        return agentldNum;
    }

    public void setAgentldNum(String agentldNum) {
        this.agentldNum = agentldNum;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getOperation() {
        return operation;
    }

    public void setOperation(Integer operation) {
        this.operation = operation;
    }

    public Long getRegisteredTime() {
        return registeredTime;
    }

    public void setRegisteredTime(Long registeredTime) {
        this.registeredTime = registeredTime;
    }

    public Long getStatusChangeTime() {
        return statusChangeTime;
    }

    public void setStatusChangeTime(Long statusChangeTime) {
        this.statusChangeTime = statusChangeTime;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "id='" + id + '\'' +
                ", userType=" + userType +
                ", userName='" + userName + '\'' +
                ", userIdType=" + userIdType +
                ", userIdNum='" + userIdNum + '\'' +
                ", tel='" + tel + '\'' +
                ", address='" + address + '\'' +
                ", registeredType=" + registeredType +
                ", channelId='" + channelId + '\'' +
                ", registeredTime='" + registeredTime + '\'' +
                ", department='" + department + '\'' +
                ", agentName='" + agentName + '\'' +
                ", agentIdType=" + agentIdType +
                ", agentldNum='" + agentldNum + '\'' +
                ", status=" + status +
                ", statusChangeTime='" + statusChangeTime + '\'' +
                ", operation=" + operation +
                '}';
    }
}
