package com.hgsoft.ygz.vtams.transfer.model;

import java.io.Serializable;
import java.util.Date;

/**
 * 发行方信息
 * Table：TB_ISSUERUPLOAD
 *
 * @author 张梁，赖少涯
 * @date 2017-10-09
 */
public class Issuer implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 发行方编号
     */
    private String id;

    /**
     * 发行方名称
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
     * 起始日期:yyyy-MM-dd
     */
    private String startTime;

    /**
     * 终止日期:yyyy-MM-dd
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
        return "Issuer [id=" + id + ", name=" + name + ", contact=" + contact
                + ", tel=" + tel + ", address=" + address + ", startTime="
                + startTime + ", endTime=" + endTime + ", operation="
                + operation + "]";
    }


}
