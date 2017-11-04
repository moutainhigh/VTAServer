package com.hgsoft.ygz.vtams.transfer.model.map;

import java.io.Serializable;
import java.util.Date;

/**
 * 客户信息映射
 * 该表保存旧客户编号、新的客户编号<br/>
 *
 * @author 赖少涯
 * @date 2017-11-02
 */
public class CustomerMapping implements Serializable {

    private static final long serialVersionUID = 1L;
    /**
     * 旧客户编号：20位
     */
    private String oldUserNo;

    /**
     * 新客户编号：17位
     */
    private String newUserNo;

    /**
     * 状态
     */
    private Integer status;

    /**
     * 创建时间
     */
    private Date creationDate;

    public CustomerMapping() {

    }

    public CustomerMapping(String oldUserNo, String newUserNo) {
        this.oldUserNo = oldUserNo;
        this.newUserNo = newUserNo;
        this.status = 1;
        this.creationDate = new Date();
    }

    public String getOldUserNo() {
        return oldUserNo;
    }

    public void setOldUserNo(String oldUserNo) {
        this.oldUserNo = oldUserNo;
    }

    public String getNewUserNo() {
        return newUserNo;
    }

    public void setNewUserNo(String newUserNo) {
        this.newUserNo = newUserNo;
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
        CustomerMapping that = (CustomerMapping) o;
        return getOldUserNo() != null ? getOldUserNo().equals(that.getOldUserNo()) : that.getOldUserNo() == null;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + (getOldUserNo() != null ? getOldUserNo().hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "CustomerMapping{" +
                "oldUserNo='" + oldUserNo + '\'' +
                ", newUserNo='" + newUserNo + '\'' +
                ", status=" + status +
                ", creationDate=" + creationDate +
                '}';
    }
}
