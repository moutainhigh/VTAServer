package com.hgsoft.ygz.vtams.transfer.model.map;

import java.io.Serializable;
import java.util.Date;

/**
 * 流动服务网点顺序码
 *
 * @author 赖少涯
 * @version 1.0
 * @date 2017-10-19 14:33:26
 * @since 1.7
 */
public class MobileServiceSeq implements Serializable {
    private static final long serialVersionUID = 1L;

    private String agencyId;

    private Integer seq;

    private Integer status;

    private Date creationDate;

    private Date lastUpdateDate;

    public String getAgencyId() {
        return agencyId;
    }

    public void setAgencyId(String agencyId) {
        this.agencyId = agencyId;
    }

    public Integer getSeq() {
        return seq;
    }

    public void setSeq(Integer seq) {
        this.seq = seq;
    }

    public Date getLastUpdateDate() {
        return lastUpdateDate;
    }

    public void setLastUpdateDate(Date lastUpdateDate) {
        this.lastUpdateDate = lastUpdateDate;
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

        MobileServiceSeq hallSeq = (MobileServiceSeq) o;

        if (getAgencyId() != null ? !getAgencyId().equals(hallSeq.getAgencyId()) : hallSeq.getAgencyId() != null)
            return false;
        return getSeq() != null ? getSeq().equals(hallSeq.getSeq()) : hallSeq.getSeq() == null;
    }

    @Override
    public int hashCode() {
        int result = getAgencyId() != null ? getAgencyId().hashCode() : 0;
        result = 31 * result + (getSeq() != null ? getSeq().hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "MobileServiceSeq{" +
                "agencyId='" + agencyId + '\'' +
                ", seq=" + seq +
                ", lastUpdateDate=" + lastUpdateDate +
                '}';
    }
}
