package com.hgsoft.ygz.vtams.transfer.model;

import java.io.Serializable;
import java.util.Date;

/**
 * 服务网点信息
 * Table：TB_HALLUPLOAD
 *
 * @author 张梁，赖少涯
 * @date 2017-10-09
 */
public class Hall implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 服务网点编号<br/>
     * 客服合作机构编号(11) + 地市编号(2) + 区县编号(2) + 网点顺序码(4)=19位
     */
    private String id;

    /**
     * 服务网点名称
     */
    private String name;

    /**
     * 联系人姓名
     */
    private String contact;

    /**
     * 联系电话
     */
    private String tel;

    /**
     * 地址
     */
    private String address;

    /**
     * 服务项目
     */
    private String serviceItems;

    /**
     * 营业时间
     */
    private String businessHours;

    /**
     * 起始日期
     */
    private String startTime;

    /**
     * 终止日期
     */
    private String endTime;

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

    public Integer getOperation() {
        return operation;
    }

    public void setOperation(Integer operation) {
        this.operation = operation;
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

    @Override
    public String toString() {
        return "Hall [id=" + id + ", name=" + name + ", contact=" + contact
                + ", tel=" + tel + ", address=" + address + ", serviceItems="
                + serviceItems + ", businessHours=" + businessHours
                + ", startTime=" + startTime + ", endTime=" + endTime
                + ", operation=" + operation + "]";
    }


}
