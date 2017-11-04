package com.hgsoft.ygz.vtams.transfer.model.map;

import java.io.Serializable;
import java.util.Date;

/**
 * 充值交易新旧编号映射
 *
 * @author 赖少涯
 * @date 2017-11-04
 */
public class RechargeMapping implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 旧的充值交易标识
     */
    private String oldRechargeId;

    /**
     * 新标识
     */
    private String newRechargeId;

    /**
     * 状态
     */
    private Integer status;

    /**
     * 创建时间
     */
    private Date creationDate;

    public RechargeMapping() {

    }

    public RechargeMapping(String oldRechargeId, String newRechargeId) {
        this.oldRechargeId = oldRechargeId;
        this.newRechargeId = newRechargeId;
        this.status = 1;
        this.creationDate = new Date();
    }


    public String getOldRechargeId() {
        return oldRechargeId;
    }

    public void setOldRechargeId(String oldRechargeId) {
        this.oldRechargeId = oldRechargeId;
    }

    public String getNewRechargeId() {
        return newRechargeId;
    }

    public void setNewRechargeId(String newRechargeId) {
        this.newRechargeId = newRechargeId;
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

        RechargeMapping that = (RechargeMapping) o;

        return getOldRechargeId() != null ? getOldRechargeId().equals(that.getOldRechargeId()) : that.getOldRechargeId() == null;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + (getOldRechargeId() != null ? getOldRechargeId().hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "RechargeMapping{" +
                "oldRechargeId='" + oldRechargeId + '\'' +
                ", newRechargeId='" + newRechargeId + '\'' +
                ", status=" + status +
                ", creationDate=" + creationDate +
                '}';
    }
}
