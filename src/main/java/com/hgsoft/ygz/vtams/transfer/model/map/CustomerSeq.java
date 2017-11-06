package com.hgsoft.ygz.vtams.transfer.model.map;

import java.io.Serializable;
import java.util.Date;

/**
 * 客户信息顺序码
 *
 * @author 赖少涯
 * @version 1.0
 * @date 2017-11-02 14:33:26
 * @since 1.7
 */
public class CustomerSeq implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 发行方编号
     */
    private String issuerId;

    /**
     * 年月日
     */
    private String yearMonthDay;

    private Integer seq;

    private Integer status;

    private Date creationDate;

    private Date lastUpdateDate;

    public String getIssuerId() {
        return issuerId;
    }

    public void setIssuerId(String issuerId) {
        this.issuerId = issuerId;
    }

    public String getYearMonthDay() {
        return yearMonthDay;
    }

    public void setYearMonthDay(String yearMonthDay) {
        this.yearMonthDay = yearMonthDay;
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

        CustomerSeq that = (CustomerSeq) o;

        if (getIssuerId() != null ? !getIssuerId().equals(that.getIssuerId()) : that.getIssuerId() != null)
            return false;
        if (getYearMonthDay() != null ? !getYearMonthDay().equals(that.getYearMonthDay()) : that.getYearMonthDay() != null)
            return false;
        return getSeq() != null ? getSeq().equals(that.getSeq()) : that.getSeq() == null;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + (getIssuerId() != null ? getIssuerId().hashCode() : 0);
        result = prime * result + (getYearMonthDay() != null ? getYearMonthDay().hashCode() : 0);
        result = prime * result + (getSeq() != null ? getSeq().hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "CustomerSeq{" +
                "issuerId='" + issuerId + '\'' +
                ", yearMonthDay='" + yearMonthDay + '\'' +
                ", seq=" + seq +
                ", status=" + status +
                ", creationDate=" + creationDate +
                ", lastUpdateDate=" + lastUpdateDate +
                '}';
    }
}
