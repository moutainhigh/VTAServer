package com.hgsoft.ygz.vtams.transfer.model;

import java.io.Serializable;
import java.util.Date;

/**
 * 自动服务终端信息
 * Table：TB_TERMINALUPLOAD
 *
 * @author 赖少涯
 * @date 2017-10-09
 */
public class Terminal implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 自助服务终端编号
     */
    private String id;
    private String address;
    private String serviceItems;
    private String businessHours;
    private String startTime;
    private String endTime;

    /**
     * 1-新增，2-变更，3-删除
     */
    private Integer operation;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
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
        return "Terminal [id=" + id + ", address=" + address
                + ", serviceItems=" + serviceItems + ", businessHours="
                + businessHours + ", startTime=" + startTime + ", endTime="
                + endTime + ", operation=" + operation + "]";
    }


}
