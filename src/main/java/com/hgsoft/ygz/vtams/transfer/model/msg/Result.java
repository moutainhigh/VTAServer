package com.hgsoft.ygz.vtams.transfer.model.msg;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * 部中心返回的结果
 *
 * @author laishaoya
 * @date 2017-10-06
 */
public class Result implements Serializable {

    private Timestamp receiveTime;

    private Integer statusCode;

    private String content;

    private String recordId;

    private String source;

    public Timestamp getReceiveTime() {
        return receiveTime;
    }

    public void setReceiveTime(Timestamp receiveTime) {
        this.receiveTime = receiveTime;
    }

    public Integer getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(Integer statusCode) {
        this.statusCode = statusCode;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getRecordId() {
        return recordId;
    }

    public void setRecordId(String recordId) {
        this.recordId = recordId;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    @Override
    public String toString() {
        return "Result{" +
                "receiveTime=" + receiveTime +
                ", statusCode=" + statusCode +
                ", content='" + content + '\'' +
                ", recordId='" + recordId + '\'' +
                ", source='" + source + '\'' +
                '}';
    }
}
