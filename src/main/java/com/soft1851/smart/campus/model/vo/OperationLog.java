package com.soft1851.smart.campus.model.vo;

import lombok.Data;

import java.util.Date;

/**
 * @Description 日志记录对象
 * @Author wf
 * @Date 2020/5/26
 * @Version 1.0
 */
@Data
public class OperationLog {

    private String id;
    private Date createTime;

    /**
     * 日志等级
     */
    private Integer level;

    /**
     * 被操作的对象
     */
    private String operationUnit;

    /**
     * 方法名
     */
    private String method;

    /**
     * 参数
     */
    private String args;

    /**
     * 操作人的id
     */
    private String userId;

    /**
     * 操作人的姓名
     */
    private String userName;

    /**
     * 日志描述
     */
    private String describe;

    /**
     * 操作类型
     */
    private String operationType;

    /**
     * 方法运行时间
     */
    private Long runTime;

    /**
     * 方法返回值
     */
    private String returnValue;
}
