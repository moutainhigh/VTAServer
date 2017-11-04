package com.hgsoft.ygz.vtams.transfer.model.business;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.io.Serializable;

/**
 * 用户卡信息中间类
 *
 * @author 赖少涯
 * @date 2017-11-03 16:30:55
 */
public class UserCardMiddle implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 用户卡编号 = 卡片网络编号(4位)+CPU卡内部编号（16位）
     */
    @NotBlank(message = "用户卡号不能为空")
    @Pattern(regexp = "^[0-9]{16}$", message = "用户卡号必须为16位数字")
    private String id;

    /**
     * 卡类型，3位数字
     */
    @NotNull(message = "卡类型不能为空")
    @Size(min = 3, max = 3, message = "卡类型必须由3位数字组成")
    private Integer cardType;

    /**
     * 卡品牌，1位或2位数字
     */
    @NotNull(message = "卡品牌不能为空")
    @Size(min = 1, max = 2, message = "卡品牌必须由1位或2位数字组成")
    private Integer brand;

    /**
     * 卡型号，不超过100位，非必填
     */
    @Size(max = 100, message = "卡型号不能超过100位")
    private String model;

    /**
     * 客服合作机构编号
     */
    @NotBlank(message = "合作机构编号不能为空")
    @Pattern(regexp = "^[0-9]{11}$", message = "合作机构编号必须为11位数字")
    private String agencyId;

    /**
     * 旧客户编号
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
     * 卡启用时间,时间戳
     */
    @NotNull(message = "卡启用时间不能为空")
    private Long enableTime;

    /**
     * 卡到期时间
     */
    @NotNull(message = "卡到期时间不能为空")
    private Long expireTime;

    /**
     * 开卡方式，1：线上 2：线下
     */
    @NotNull(message = "开卡方式不能为空")
    @Pattern(regexp = "^[12]$", message = "开卡方式只能为1[线上]或2[线下]")
    private Integer issuedType;

    /**
     * 渠道编号
     */
    @JsonProperty("channelId")
    @NotBlank(message = "渠道编号不能为空")
    @Pattern(regexp = "^[0-9A-Za-z]+$", message = "渠道编号必须由数字或字母组成")
    private String oldPointCode;

    /**
     * 开卡时间
     */
    @NotNull(message = "开卡时间不能为空")
    private Long issuedTime;

    /**
     * 用户卡状态
     */
    @NotNull(message = "用户卡状态不能为空")
    @Pattern(regexp = "^[1-6]$", message = "用户卡状态只能为1-6")
    private Integer status;

    /**
     * 用户卡状态变更时间
     */
    @NotNull(message = "用户卡状态变更时间不能为空")
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

    public Integer getCardType() {
        return cardType;
    }

    public void setCardType(Integer cardType) {
        this.cardType = cardType;
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

    public String getAgencyId() {
        return agencyId;
    }

    public void setAgencyId(String agencyId) {
        this.agencyId = agencyId;
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

    public Integer getIssuedType() {
        return issuedType;
    }

    public void setIssuedType(Integer issuedType) {
        this.issuedType = issuedType;
    }

    public String getOldPointCode() {
        return oldPointCode;
    }

    public void setOldPointCode(String oldPointCode) {
        this.oldPointCode = oldPointCode;
    }

    public Long getIssuedTime() {
        return issuedTime;
    }

    public void setIssuedTime(Long issuedTime) {
        this.issuedTime = issuedTime;
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
        return "UserCardMiddle{" +
                "id='" + id + '\'' +
                ", cardType=" + cardType +
                ", brand=" + brand +
                ", model='" + model + '\'' +
                ", agencyId='" + agencyId + '\'' +
                ", oldUserNo='" + oldUserNo + '\'' +
                ", vehiclePlate='" + vehiclePlate + '\'' +
                ", vehicleColor='" + vehicleColor + '\'' +
                ", enableTime=" + enableTime +
                ", expireTime=" + expireTime +
                ", issuedType=" + issuedType +
                ", oldPointCode='" + oldPointCode + '\'' +
                ", issuedTime=" + issuedTime +
                ", status=" + status +
                ", statusChangeTime=" + statusChangeTime +
                ", operation=" + operation +
                '}';
    }
}
