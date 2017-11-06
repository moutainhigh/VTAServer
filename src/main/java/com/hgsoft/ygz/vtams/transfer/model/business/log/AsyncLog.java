package com.hgsoft.ygz.vtams.transfer.model.business.log;

import com.hgsoft.ygz.vtams.transfer.exception.AsyncException;
import org.apache.commons.beanutils.BeanUtils;

import java.sql.Timestamp;

/**
 * 异步信息日志
 *
 * @author 赖少涯
 * @version 1.0
 * @date 2017-10-31 19:22:25
 * @since 1.7
 */
public class AsyncLog extends MsgLog {

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
    private Timestamp receivedTime;

    /**
     * 映射开始时间
     */
    private Timestamp mappingStartTime;

    /**
     * 映射结束时间
     */
    private Timestamp mappingEndTime;

    /**
     * 入库开始时间
     */
    private Timestamp inputStockStartTime;

    /**
     * 入库结束时间
     */
    private Timestamp inputStockEndTime;

    /**
     * 最终描述信息
     */
    private String statusDesc;


    public AsyncLog() {

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

    public Timestamp getMappingStartTime() {
        return mappingStartTime;
    }

    public void setMappingStartTime(Timestamp mappingStartTime) {
        this.mappingStartTime = mappingStartTime;
    }

    public Timestamp getMappingEndTime() {
        return mappingEndTime;
    }

    public void setMappingEndTime(Timestamp mappingEndTime) {
        this.mappingEndTime = mappingEndTime;
    }

    public Timestamp getInputStockStartTime() {
        return inputStockStartTime;
    }

    public void setInputStockStartTime(Timestamp inputStockStartTime) {
        this.inputStockStartTime = inputStockStartTime;
    }

    public Timestamp getInputStockEndTime() {
        return inputStockEndTime;
    }

    public void setInputStockEndTime(Timestamp inputStockEndTime) {
        this.inputStockEndTime = inputStockEndTime;
    }

    public String getStatusDesc() {
        return statusDesc;
    }

    public void setStatusDesc(String statusDesc) {
        this.statusDesc = statusDesc;
    }

    @Override
    public String toString() {
        return "AsyncLog{" +
                "id=" + id +
                ", batchNo=" + batchNo +
                ", businessContent='" + businessContent + '\'' +
                ", businessType='" + businessType + '\'' +
                ", operation=" + operation +
                ", receivedTime=" + receivedTime +
                ", mappingStartTime=" + mappingStartTime +
                ", mappingEndTime=" + mappingEndTime +
                ", inputStockStartTime=" + inputStockStartTime +
                ", inputStockEndTime=" + inputStockEndTime +
                ", statusDesc='" + statusDesc + '\'' +
                '}';
    }
}
