package com.hgsoft.ygz.vtams.transfer.model.business;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.io.Serializable;

/**
 * obu信息上传及变更
 *
 * @author 赖少涯
 * @date 2017-11-03
 */
public class ObuMiddle implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * OBU序号编码，16位,必填
     */
    @NotBlank(message = "obu序号编码不能为空")
    private String id;

    /**
     * obu品牌，必填
     */
    @NotNull(message = "obu品牌不能为空")
    private Integer brand;

    /**
     * 型号，必填
     */
    @NotBlank(message = "obu型号不能为空")
    private String model;

    /**
     * 客户编号,必填
     */
    @JsonProperty("userId")
    @NotBlank(message = "客户编号不能为空")
    @Pattern(regexp = "^[0-9]{20}$", message = "客户编号必须由20位数字组成")
    private String oldUserNo;

    /**
     * 车牌号码
     */
    @NotBlank(message = "车牌号码不能为空")
    private String vehiclePlate;

    /**
     * 车牌颜色
     */
    @NotBlank(message = "车牌颜色不能为空")
    @Pattern(regexp = "^[0-9]$", message = "车牌颜色只能是1位数字")
    private String vehicleColor;

    /**
     * OBU启用时间，时间戳
     */
    @NotNull(message = "obu启用时间不能为空")
    private Long enableTime;

    /**
     * OBU到期时间：yyyy-MM-dd,必填
     */
    @NotNull(message = "obu到期时间不能为空")
    private Long expireTime;

    /**
     * OBU注册方式，1：线上 2：线下,必填
     */
    @NotNull(message = "obu注册方式不能为空")
    @Pattern(regexp = "^[12]$", message = "obu注册方式只能为1[线上]或2[线下]")
    private Integer registeredType;

    /**
     * OBU注册渠道编号，必填
     */
    @JsonProperty("registeredChannelId")
    @NotBlank(message = "渠道编号不能为空")
    @Pattern(regexp = "^[0-9A-Za-z]+$", message = "渠道编号必须由数字或字母组成")
    private String oldPointCode;

    /**
     * OBU注册时间
     */
    @NotNull(message = "obu注册时间不能为空")
    private Long registeredTime;

    /**
     * OBU安装方式，1：自行安装 2：网点安装，必填
     */
    @NotNull(message = "obu安装方式不能为空")
    @Pattern(regexp = "^[12]$", message = "obu安装方式只能为1[自行安装]或2[网点安装]")
    private Integer installType;

    /**
     * OBU安装/激活，非必填
     */
    private String installChannelId;

    /**
     * OBU安装/激活时间，必填
     */
    @NotNull(message = "obu安装/激活时间不能为空")
    private Long installTime;

    /**
     * OBU状态,1-8，必填
     */
    @NotNull(message = "obu状态不能为空")
    @Pattern(regexp = "^[1-8]$", message = "obu状态只能为1-8")
    private Integer status;

    /**
     * OBU状态变更时间:yyyy-MM-ddTHH:mm:ss，必填
     */
    @NotNull(message = "obu状态变更时间不能为空")
    private Long statusChangeTime;

    /**
     * 操作，1-新增 2-变更 3-删除
     */
    private Integer operation;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getBrand() {
        return brand;
    }

    public void setBrand(Integer brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getOldUserNo() {
        return oldUserNo;
    }

    public void setOldUserNo(String oldUserNo) {
        this.oldUserNo = oldUserNo;
    }

    public String getVehiclePlate() {
        return vehiclePlate;
    }

    public void setVehiclePlate(String vehiclePlate) {
        this.vehiclePlate = vehiclePlate;
    }

    public String getVehicleColor() {
        return vehicleColor;
    }

    public void setVehicleColor(String vehicleColor) {
        this.vehicleColor = vehicleColor;
    }

    public Long getEnableTime() {
        return enableTime;
    }

    public void setEnableTime(Long enableTime) {
        this.enableTime = enableTime;
    }

    public Long getExpireTime() {
        return expireTime;
    }

    public void setExpireTime(Long expireTime) {
        this.expireTime = expireTime;
    }

    public Integer getRegisteredType() {
        return registeredType;
    }

    public void setRegisteredType(Integer registeredType) {
        this.registeredType = registeredType;
    }

    public String getOldPointCode() {
        return oldPointCode;
    }

    public void setOldPointCode(String oldPointCode) {
        this.oldPointCode = oldPointCode;
    }

    public Long getRegisteredTime() {
        return registeredTime;
    }

    public void setRegisteredTime(Long registeredTime) {
        this.registeredTime = registeredTime;
    }

    public Integer getInstallType() {
        return installType;
    }

    public void setInstallType(Integer installType) {
        this.installType = installType;
    }

    public String getInstallChannelId() {
        return installChannelId;
    }

    public void setInstallChannelId(String installChannelId) {
        this.installChannelId = installChannelId;
    }

    public Long getInstallTime() {
        return installTime;
    }

    public void setInstallTime(Long installTime) {
        this.installTime = installTime;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Long getStatusChangeTime() {
        return statusChangeTime;
    }

    public void setStatusChangeTime(Long statusChangeTime) {
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
        return "ObuMiddle{" +
                "id='" + id + '\'' +
                ", brand=" + brand +
                ", model='" + model + '\'' +
                ", oldUserNo='" + oldUserNo + '\'' +
                ", vehiclePlate='" + vehiclePlate + '\'' +
                ", vehicleColor='" + vehicleColor + '\'' +
                ", enableTime=" + enableTime +
                ", expireTime=" + expireTime +
                ", registeredType=" + registeredType +
                ", oldPointCode='" + oldPointCode + '\'' +
                ", registeredTime=" + registeredTime +
                ", installType=" + installType +
                ", installChannelId='" + installChannelId + '\'' +
                ", installTime=" + installTime +
                ", status=" + status +
                ", statusChangeTime=" + statusChangeTime +
                ", operation=" + operation +
                '}';
    }
}
