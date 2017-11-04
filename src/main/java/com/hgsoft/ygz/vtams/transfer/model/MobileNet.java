package com.hgsoft.ygz.vtams.transfer.model;

import java.io.Serializable;
import java.util.Date;

/**
 * 流动服务网点信息
 * Table：TB_MOBILESERVICEUPLAOD
 *
 * @author zhangliang
 * @date 2017-10-09
 */
public class MobileNet implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 流动服务点编号
     */
    private String id;

    private String startTime;

    private String endTime;
    private Integer operation;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public Integer getOperation() {
        return operation;
    }

    public void setOperation(Integer operation) {
        this.operation = operation;
    }

    @Override
    public String toString() {
        return "MobileNet [id=" + id + ", startTime=" + startTime
                + ", endTime=" + endTime + ", operation=" + operation + "]";
    }

}
