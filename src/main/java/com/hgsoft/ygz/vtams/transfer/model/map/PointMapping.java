package com.hgsoft.ygz.vtams.transfer.model.map;

import java.io.Serializable;
import java.util.Date;

/**
 * 网点信息映射<br/>
 * 该表保存 营业网点编号、新的网点编号、客服合作机构编码<br/>
 * 一个旧网点编号对应一个新的网点编号,多个营业网点编号可对应一个合作机构编码
 *
 * @author 赖少涯
 * @date 2017-10-18
 */
public class PointMapping implements Serializable {

    private static final long serialVersionUID = 1L;
    /**
     * 旧网点编号：9位 or 7位
     */
    private String oldPointCode;

    /**
     * 新网点编号：19位
     */
    private String newPointCode;

    /**
     * 渠道类型
     */
    private String channelType;

    /**
     * 客服合作机构编号11位
     */
    private String agencyId;

    /**
     * 状态
     */
    private Integer status;

    /**
     * 创建时间
     */
    private Date creationDate;

    public PointMapping() {

    }

    public PointMapping(String oldPointCode, String newPointCode, String agencyId, String channelType) {
        this.oldPointCode = oldPointCode;
        this.newPointCode = newPointCode;
        this.agencyId = agencyId;
        this.channelType = channelType;
        this.status = 1;
        this.creationDate = new Date();
    }

    public String getChannelType() {
        return channelType;
    }

    public void setChannelType(String channelType) {
        this.channelType = channelType;
    }

    public String getAgencyId() {
        return agencyId;
    }

    public void setAgencyId(String agencyId) {
        this.agencyId = agencyId;
    }

    public String getOldPointCode() {
        return oldPointCode;
    }

    public void setOldPointCode(String oldPointCode) {
        this.oldPointCode = oldPointCode;
    }

    public String getNewPointCode() {
        return newPointCode;
    }

    public void setNewPointCode(String newPointCode) {
        this.newPointCode = newPointCode;
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
        PointMapping that = (PointMapping) o;
        return getOldPointCode() != null ? getOldPointCode().equals(that.getOldPointCode()) : that.getOldPointCode() == null;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + (getOldPointCode() != null ? getOldPointCode().hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "PointMapping{" +
                "oldPointCode='" + oldPointCode + '\'' +
                ", newPointCode='" + newPointCode + '\'' +
                ", status=" + status +
                ", creationDate=" + creationDate +
                '}';
    }
}
