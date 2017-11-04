package com.hgsoft.ygz.vtams.transfer.model;

import java.io.Serializable;
import java.util.Date;

/**
 * 线上服务渠道信息
 * Table：TB_ONLINEUPLOAD
 *
 * @author zhangliang
 * @date 2017-10-09
 */
public class Online implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 线上服务渠道编号
     */
    private String id;

    /**
     * 线上服务渠道名称
     */
    private String name;
    private String serviceItems;
    private String startTime;
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

    public String getServiceItems() {
        return serviceItems;
    }

    public void setServiceItems(String serviceItems) {
        this.serviceItems = serviceItems;
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
        return "Online [id=" + id + ", name=" + name + ", serviceItems="
                + serviceItems + ", startTime=" + startTime + ", endTime="
                + endTime + ", operation=" + operation + "]";
    }

}
