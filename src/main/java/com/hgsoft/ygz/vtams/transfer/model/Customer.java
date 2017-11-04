package com.hgsoft.ygz.vtams.transfer.model;


import java.io.Serializable;

/**
 * 客户信息上传及变更
 *
 * @author 赖少涯
 * @date 2017-10-09
 */
public class Customer implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 客户编号，17位，发行方编号(6位)+ 开户年月日（6位））+流水顺序码（5位），必填
     */
    private String id;

    /**
     * 客户类型，1位，1:个人,2:单位，必填
     */
    private Integer userType;

    /**
     * 开户人名称，不超过50个字符，必填
     */
    private String userName;

    /**
     * 开户人证件类型，3位数字，必填
     */
    private Integer userIdType;

    /**
     * 开户人证件号码，不超过30个字符，必填
     */
    private String userIdNum;

    /**
     * 开户人/指定经办人电号码，11位，必填
     */
    private String tel;

    /**
     * 开户人地址，不超过100个字符，必填
     */
    private String address;

    /**
     * 开户方式，1:线上,2:线下，必填
     */
    private Integer registeredType;

    /**
     * 开户渠道编号，不超过20位，线下开户=>服务网点编号,线上开户=>线上服务渠道编号，必填
     */
    private String channelId;

    /**
     * 开户时间，YYYY-MM-DDTHH:mm:ss，必填
     */
    private String registeredTime;

    /**
     * 部门/分支机构名称，单位用户必填
     */
    private String department;

    /**
     * 指定经办人姓名,不超过50个字符，单位用户必填
     */
    private String agentName;

    /**
     * 指定经办人证件类型，3位，单位用户必填
     */
    private Integer agentIdType;

    /**
     * 指定经办人证件号,不超过30位，单位用户必填
     */
    private String agentldNum;

    /**
     * 客户状态，1:在用，2:注销，必填
     */
    private Integer status;

    /**
     * 客户状态变更时间,YYYY-MM-DDTHH:mm:ss，必填
     */
    private String statusChangeTime;

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

    public String getRegisteredTime() {
        return registeredTime;
    }

    public void setRegisteredTime(String registeredTime) {
        this.registeredTime = registeredTime;
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

    public String getStatusChangeTime() {
        return statusChangeTime;
    }

    public void setStatusChangeTime(String statusChangeTime) {
        this.statusChangeTime = statusChangeTime;
    }

    public Integer getOperation() {
        return operation;
    }

    public void setOperation(Integer operation) {
        this.operation = operation;
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
