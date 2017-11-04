package com.hgsoft.ygz.vtams.transfer.model.business.log;

import java.sql.Timestamp;

/**
 * 同步信息日志
 *
 * @author 赖少涯
 * @version 1.0
 * @date 2017-10-31 19:22:25
 * @since 1.7
 */
public class SyncLog extends MsgLog {

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
     * 请求文件名
     */
    private String reqFileName;

    /**
     * 请求的json
     */
    private String reqJsonStr;

    /**
     * 请求文件内容的md5值
     */
    private String reqFileMd5;

    /**
     * 第一次发送请求的时间
     */
    private Timestamp requestTime;

    /**
     * 重连次数
     */
    private Integer retryTimes;

    /**
     * 响应码
     */
    private Integer responseCode;

    /**
     * 响应内容
     */
    private String responseContent;

    /**
     * 响应时间
     */
    private Timestamp responseTime;

    /**
     * 最终描述信息
     */
    private String statusDesc;

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

    public String getReqFileName() {
        return reqFileName;
    }

    public void setReqFileName(String reqFileName) {
        this.reqFileName = reqFileName;
    }

    public String getReqJsonStr() {
        return reqJsonStr;
    }

    public void setReqJsonStr(String reqJsonStr) {
        this.reqJsonStr = reqJsonStr;
    }

    public String getReqFileMd5() {
        return reqFileMd5;
    }

    public void setReqFileMd5(String reqFileMd5) {
        this.reqFileMd5 = reqFileMd5;
    }

    public Timestamp getRequestTime() {
        return requestTime;
    }

    public void setRequestTime(Timestamp requestTime) {
        this.requestTime = requestTime;
    }

    public Integer getRetryTimes() {
        return retryTimes;
    }

    public void setRetryTimes(Integer retryTimes) {
        this.retryTimes = retryTimes;
    }

    public Integer getResponseCode() {
        return responseCode;
    }

    public void setResponseCode(Integer responseCode) {
        this.responseCode = responseCode;
    }

    public String getResponseContent() {
        return responseContent;
    }

    public void setResponseContent(String responseContent) {
        this.responseContent = responseContent;
    }

    public Timestamp getResponseTime() {
        return responseTime;
    }

    public void setResponseTime(Timestamp responseTime) {
        this.responseTime = responseTime;
    }

    public String getStatusDesc() {
        return statusDesc;
    }

    public void setStatusDesc(String statusDesc) {
        this.statusDesc = statusDesc;
    }
}

