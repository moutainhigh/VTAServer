package com.hgsoft.ygz.vtams.transfer.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

/**
 * 客户车辆信息：上传及变更
 * Table：TB_VEHICLEUPLOAD
 *
 * @author 赖少涯
 * @date 2017-10-09
 */
public class Vehicle implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 车辆编号: 车牌号码（18位）+间隔符（1位）+车牌颜色（1位），必填
     */
    private String id;

    /**
     * 收费车型，必填
     */
    private Integer type;

    /**
     * 所属客户编号,17位数字，参见客户信息表,必填
     */
    private String userId;

    /**
     * 机动车所有人名称,不超过50个字符，非必填
     */
    private String ownerName;

    /**
     * 机动车所有人证件类型,3位数字，非必填
     */
    private Integer ownerIdType;

    /**
     * 机动车所有人证件号码,非必填
     */
    private String ownerIdNum;

    /**
     * 联系方式，不超过11位，非必填
     */
    private String ownerTel;

    /**
     * 联系地址，不超过100个字符，非必填
     */
    private String address;

    /**
     * 指定联系人姓名,不超过50个字符，若机动车所有人是单位，则填写单位指定联系人姓名.必填
     */
    private String contact;

    /**
     * 录入方式，1:线上,2:线下，必填
     */
    private Integer registeredType;

    /**
     * 录入渠道编号，线下录入=>服务网点编号,线上录入=>线上服务渠道编号，必填
     */
    private String channelId;

    /**
     * 录入时间，YYYY-MM-DDTHH:mm:ss，必填
     */
    private String registeredTime;

    /**
     * 行驶证车辆类型，非必填
     */
    private String vehicleType;

    /**
     * 行驶证品牌型号，非必填
     */
    private String vehicleModel;

    /**
     * 车辆使用性质，1位数字，非必填
     */
    private Integer useCharacter;

    /**
     * 车辆识别代码，不超过50个字符，必填
     */
    @JsonProperty("VIN")
    private String vin;

    /**
     * 车辆发动机号，不超过50个字符，非必填
     */
    private String engineNum;

    /**
     * 注册日期，YYYY-MM-DD，非必填
     */
    private String registerDate;

    /**
     * 发证日期，YYYY-MM-DD，非必填
     */
    private String issueDate;

    /**
     * 档案编号,不超过50个字符，非必填
     */
    private String fileNum;

    /**
     * 核定载人数，非必填
     */
    private Integer approvedCount;

    /**
     * 总质量，非必填
     */
    private Integer totalMass;

    /**
     * 整备质量，非必填
     */
    private Integer maintenanceMass;

    /**
     * 核定载质量，非必填
     */
    private Integer permittedWeight;

    /**
     * 外廓尺寸，长x宽x高,如：10000x2530x1250，单位:mm,不超过100个字符. 非必填
     */
    private String outsideDimensions;

    /**
     * 准牵引总质量，非必填
     */
    private Integer permittedTowWeight;

    /**
     * 检验记录，不超过50个字符，非必填
     */
    private String testRecord;

    /**
     * 车轮数，非必填
     */
    private Integer wheelCount;

    /**
     * 车轴数，非必填
     */
    private Integer axleCount;

    /**
     * 轴距，单位:mm,不超过4位数字，非必填
     */
    private Integer axleDistance;

    /**
     * 轴型,不超过50位数字，非必填
     */
    private String axisType;

    /**
     * 操作，1:新增,2:变更,3:删除，必填
     */
    private Integer operation;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }

    public Integer getOwnerIdType() {
        return ownerIdType;
    }

    public void setOwnerIdType(Integer ownerIdType) {
        this.ownerIdType = ownerIdType;
    }

    public String getOwnerIdNum() {
        return ownerIdNum;
    }

    public void setOwnerIdNum(String ownerIdNum) {
        this.ownerIdNum = ownerIdNum;
    }

    public String getOwnerTel() {
        return ownerTel;
    }

    public void setOwnerTel(String ownerTel) {
        this.ownerTel = ownerTel;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
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

    public String getVehicleType() {
        return vehicleType;
    }

    public void setVehicleType(String vehicleType) {
        this.vehicleType = vehicleType;
    }

    public String getVehicleModel() {
        return vehicleModel;
    }

    public void setVehicleModel(String vehicleModel) {
        this.vehicleModel = vehicleModel;
    }

    public Integer getUseCharacter() {
        return useCharacter;
    }

    public void setUseCharacter(Integer useCharacter) {
        this.useCharacter = useCharacter;
    }

    public String getVin() {
        return vin;
    }

    public void setVin(String vin) {
        this.vin = vin;
    }

    public String getEngineNum() {
        return engineNum;
    }

    public void setEngineNum(String engineNum) {
        this.engineNum = engineNum;
    }

    public String getRegisterDate() {
        return registerDate;
    }

    public void setRegisterDate(String registerDate) {
        this.registerDate = registerDate;
    }

    public String getIssueDate() {
        return issueDate;
    }

    public void setIssueDate(String issueDate) {
        this.issueDate = issueDate;
    }

    public String getFileNum() {
        return fileNum;
    }

    public void setFileNum(String fileNum) {
        this.fileNum = fileNum;
    }

    public Integer getApprovedCount() {
        return approvedCount;
    }

    public void setApprovedCount(Integer approvedCount) {
        this.approvedCount = approvedCount;
    }

    public Integer getTotalMass() {
        return totalMass;
    }

    public void setTotalMass(Integer totalMass) {
        this.totalMass = totalMass;
    }

    public Integer getMaintenanceMass() {
        return maintenanceMass;
    }

    public void setMaintenanceMass(Integer maintenanceMass) {
        this.maintenanceMass = maintenanceMass;
    }

    public Integer getPermittedWeight() {
        return permittedWeight;
    }

    public void setPermittedWeight(Integer permittedWeight) {
        this.permittedWeight = permittedWeight;
    }

    public String getOutsideDimensions() {
        return outsideDimensions;
    }

    public void setOutsideDimensions(String outsideDimensions) {
        this.outsideDimensions = outsideDimensions;
    }

    public Integer getPermittedTowWeight() {
        return permittedTowWeight;
    }

    public void setPermittedTowWeight(Integer permittedTowWeight) {
        this.permittedTowWeight = permittedTowWeight;
    }

    public String getTestRecord() {
        return testRecord;
    }

    public void setTestRecord(String testRecord) {
        this.testRecord = testRecord;
    }

    public Integer getWheelCount() {
        return wheelCount;
    }

    public void setWheelCount(Integer wheelCount) {
        this.wheelCount = wheelCount;
    }

    public Integer getAxleCount() {
        return axleCount;
    }

    public void setAxleCount(Integer axleCount) {
        this.axleCount = axleCount;
    }

    public Integer getAxleDistance() {
        return axleDistance;
    }

    public void setAxleDistance(Integer axleDistance) {
        this.axleDistance = axleDistance;
    }

    public String getAxisType() {
        return axisType;
    }

    public void setAxisType(String axisType) {
        this.axisType = axisType;
    }

    public Integer getOperation() {
        return operation;
    }

    public void setOperation(Integer operation) {
        this.operation = operation;
    }

    @Override
    public String toString() {
        return "Vehicle{" +
                "id='" + id + '\'' +
                ", type=" + type +
                ", userId='" + userId + '\'' +
                ", ownerName='" + ownerName + '\'' +
                ", ownerIdType=" + ownerIdType +
                ", ownerIdNum='" + ownerIdNum + '\'' +
                ", ownerTel='" + ownerTel + '\'' +
                ", address='" + address + '\'' +
                ", contact='" + contact + '\'' +
                ", registeredType=" + registeredType +
                ", channelId='" + channelId + '\'' +
                ", registeredTime='" + registeredTime + '\'' +
                ", vehicleType='" + vehicleType + '\'' +
                ", vehicleModel='" + vehicleModel + '\'' +
                ", useCharacter=" + useCharacter +
                ", vin='" + vin + '\'' +
                ", engineNum='" + engineNum + '\'' +
                ", registerDate='" + registerDate + '\'' +
                ", issueDate='" + issueDate + '\'' +
                ", fileNum='" + fileNum + '\'' +
                ", approvedCount=" + approvedCount +
                ", totalMass=" + totalMass +
                ", maintenanceMass=" + maintenanceMass +
                ", permittedWeight=" + permittedWeight +
                ", outsideDimensions='" + outsideDimensions + '\'' +
                ", permittedTowWeight=" + permittedTowWeight +
                ", testRecord='" + testRecord + '\'' +
                ", wheelCount=" + wheelCount +
                ", axleCount=" + axleCount +
                ", axleDistance=" + axleDistance +
                ", axisType='" + axisType + '\'' +
                ", operation=" + operation +
                '}';
    }
}
