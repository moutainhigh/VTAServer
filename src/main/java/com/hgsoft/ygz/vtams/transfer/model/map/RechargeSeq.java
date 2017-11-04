package com.hgsoft.ygz.vtams.transfer.model.map;

import java.io.Serializable;
import java.util.Date;

/**
 * 充值交易顺序码
 *
 * @author 赖少涯
 * @version 1.0
 * @date 2017-11-03 14:33:26
 * @since 1.7
 */
public class RechargeSeq implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 渠道类型，1位
     */
    private String channelType;

    /**
     * 网点编号
     */
    private String pointCode;

    /**
     * 退款发生时间
     */
    private String dateStr;

    private Integer seq;

    private Integer status;

    private Date creationDate;

    private Date lastUpdateDate;

    public String getChannelType() {
        return channelType;
    }

    public void setChannelType(String channelType) {
        this.channelType = channelType;
    }

    public String getPointCode() {
        return pointCode;
    }

    public void setPointCode(String pointCode) {
        this.pointCode = pointCode;
    }

    public String getDateStr() {
        return dateStr;
    }

    public void setDateStr(String dateStr) {
        this.dateStr = dateStr;
    }

    public Integer getSeq() {
        return seq;
    }

    public void setSeq(Integer seq) {
        this.seq = seq;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public Date getLastUpdateDate() {
        return lastUpdateDate;
    }

    public void setLastUpdateDate(Date lastUpdateDate) {
        this.lastUpdateDate = lastUpdateDate;
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

        RechargeSeq refundSeq = (RechargeSeq) o;

        if (getChannelType() != null ? !getChannelType().equals(refundSeq.getChannelType()) : refundSeq.getChannelType() != null)
            return false;
        if (getPointCode() != null ? !getPointCode().equals(refundSeq.getPointCode()) : refundSeq.getPointCode() != null)
            return false;
        if (getDateStr() != null ? !getDateStr().equals(refundSeq.getDateStr()) : refundSeq.getDateStr() != null)
            return false;
        return getSeq() != null ? getSeq().equals(refundSeq.getSeq()) : refundSeq.getSeq() == null;
    }

    @Override
    public int hashCode() {
        int result = getChannelType() != null ? getChannelType().hashCode() : 0;
        result = 31 * result + (getPointCode() != null ? getPointCode().hashCode() : 0);
        result = 31 * result + (getDateStr() != null ? getDateStr().hashCode() : 0);
        result = 31 * result + (getSeq() != null ? getSeq().hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "RechargeSeq{" +
                "channelType='" + channelType + '\'' +
                ", pointCode='" + pointCode + '\'' +
                ", dateStr='" + dateStr + '\'' +
                ", seq=" + seq +
                ", status=" + status +
                ", creationDate=" + creationDate +
                ", lastUpdateDate=" + lastUpdateDate +
                '}';
    }
}
