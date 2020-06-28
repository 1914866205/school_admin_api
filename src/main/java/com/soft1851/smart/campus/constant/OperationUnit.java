package com.soft1851.smart.campus.constant;

/**
 * @Description 被操作的单元
 * @Author wf
 * @Date 2020/5/26
 * @Version 1.0
 */
public enum OperationUnit {

    /**
     * 被操作的单元
     */
    UNKNOWN("unknown"),
    USER("user"),
    EMPLOYEE("employee"),
    Redis("redis");

    private String value;


    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
    OperationUnit(String s) {
        this.value = s;
    }

}
