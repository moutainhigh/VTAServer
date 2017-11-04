package com.hgsoft.ygz.vtams.transfer.common.enums;

/**
 * 操作类型
 */
public enum operationEnum {
    add(1, "新增"), update(2, "变更"), delete(3, "删除");

    private Integer value;
    private String description;

    operationEnum(Integer value, String description) {
        this.value = value;
        this.description = description;
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
