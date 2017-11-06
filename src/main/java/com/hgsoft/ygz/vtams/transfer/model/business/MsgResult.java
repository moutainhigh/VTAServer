package com.hgsoft.ygz.vtams.transfer.model.business;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * 消息请求的反馈结果
 *
 * @author 赖少涯
 * @version 1.0
 * @date 2017-11-04 07:48:34
 * @since 1.7
 */
public class MsgResult implements Serializable {

    private static final long serialVersionUID = 1L;

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

    /**
     *
     */
    private Integer status;

    /**
     *
     */
    private Integer code;

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

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }
}
