package com.hgsoft.ygz.vtams.transfer.model.map;

import java.io.Serializable;
import java.util.Date;

/**
 * 自助服务终端顺序码
 *
 * @author 赖少涯
 * @version 1.0
 * @date 2017-10-19 14:33:26
 * @since 1.7
 */
public class TerminalSeq implements Serializable {
    private static final long serialVersionUID = 1L;

    private String agencyId;

    private String cityCode;

    private String districtCode;

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

    public String getCityCode() {
        return cityCode;
    }

    public void setCityCode(String cityCode) {
        this.cityCode = cityCode;
    }

    public String getDistrictCode() {
        return districtCode;
    }

    public void setDistrictCode(String districtCode) {
        this.districtCode = districtCode;
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

        TerminalSeq hallSeq = (TerminalSeq) o;

        if (getAgencyId() != null ? !getAgencyId().equals(hallSeq.getAgencyId()) : hallSeq.getAgencyId() != null)
            return false;
        if (getCityCode() != null ? !getCityCode().equals(hallSeq.getCityCode()) : hallSeq.getCityCode() != null)
            return false;
        if (getDistrictCode() != null ? !getDistrictCode().equals(hallSeq.getDistrictCode()) : hallSeq.getDistrictCode() != null)
            return false;
        return getSeq() != null ? getSeq().equals(hallSeq.getSeq()) : hallSeq.getSeq() == null;
    }

    @Override
    public int hashCode() {
        int result = getAgencyId() != null ? getAgencyId().hashCode() : 0;
        result = 31 * result + (getCityCode() != null ? getCityCode().hashCode() : 0);
        result = 31 * result + (getDistrictCode() != null ? getDistrictCode().hashCode() : 0);
        result = 31 * result + (getSeq() != null ? getSeq().hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "TerminalSeq{" +
                "agencyId='" + agencyId + '\'' +
                ", cityCode='" + cityCode + '\'' +
                ", districtCode='" + districtCode + '\'' +
                ", seq=" + seq +
                ", lastUpdateDate=" + lastUpdateDate +
                '}';
    }
}
