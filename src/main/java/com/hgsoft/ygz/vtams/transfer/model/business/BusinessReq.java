package com.hgsoft.ygz.vtams.transfer.model.business;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * 业务请求信息：实际可分为同步和异步信息
 *
 * @author laishaoya
 * @date 2017-10-16
 */
public class BusinessReq implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;

    private Long batchNo;

    /**
     * 业务消息内容
     */
    private String businessContent;

    /**
     * 业务类型
     */
    private String businessType;

    /**
     * 操作类型：1:新增，2:修改，3:删除
     */
    private Integer operation;

    /**
     * 创建时间：时间戳
     */
    private Timestamp createTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getBatchNo() {
        return batchNo;
    }

    public void setBatchNo(Long batchNo) {
        this.batchNo = batchNo;
    }

    public String getBusinessContent() {
        return businessContent;
    }

    public void setBusinessContent(String businessContent) {
        this.businessContent = businessContent;
    }

    public String getBusinessType() {
        return businessType;
    }

    public void setBusinessType(String businessType) {
        this.businessType = businessType;
    }

    public Integer getOperation() {
        return operation;
    }

    public void setOperation(Integer operation) {
        this.operation = operation;
    }

    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (null == o) {
            return false;
        }

        if (this.getClass() != o.getClass()) {
            return false;
        }
        BusinessReq that = (BusinessReq) o;
        return getId() != null ? getId().equals(that.getId()) : that.getId() == null;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + (getId() != null ? getId().hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "BusinessReq{" +
                "id=" + id +
                ", batchNo=" + batchNo +
                ", businessContent='" + businessContent + '\'' +
                ", businessType='" + businessType + '\'' +
                ", operation=" + operation +
                ", createTime=" + createTime +
                '}';
    }
}
