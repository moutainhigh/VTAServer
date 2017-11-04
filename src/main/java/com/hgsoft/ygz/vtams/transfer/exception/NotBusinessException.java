package com.hgsoft.ygz.vtams.transfer.exception;

import java.sql.Timestamp;

/**
 * 非业务性异常
 *
 * @author 赖少涯
 * @date 2017-10-17
 */
public class NotBusinessException extends RuntimeException {

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
    private Timestamp receivedTime;

    /**
     * 最终描述信息
     */
    private String statusDesc;

    public NotBusinessException() {

    }

    public NotBusinessException(String statusDesc) {
        this.statusDesc = statusDesc;
    }

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

    public Timestamp getReceivedTime() {
        return receivedTime;
    }

    public void setReceivedTime(Timestamp receivedTime) {
        this.receivedTime = receivedTime;
    }

    public String getStatusDesc() {
        return statusDesc;
    }

    public void setStatusDesc(String statusDesc) {
        this.statusDesc = statusDesc;
    }
}
