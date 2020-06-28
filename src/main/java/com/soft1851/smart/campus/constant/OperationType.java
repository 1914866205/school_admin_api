package com.soft1851.smart.campus.constant;

/**
 * @Description 操作类型
 * @Author wf
 * @Date 2020/5/26
 * @Version 1.0
 */
public enum  OperationType {

    UNKNOWN("unknown"),
    DELETE("delete"),
    SELECT("select"),
    UPDATE("update"),
    INSERT("insert");

    private String value;

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    OperationType(String s) {
        this.value = s;
    }
}
