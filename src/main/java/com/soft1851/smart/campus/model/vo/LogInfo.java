package com.soft1851.smart.campus.model.vo;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * @Description 日志记录对象
 * @Author wf
 * @Date 2020/5/26
 * @Version 1.0
 */
@Data
public class LogInfo {

    private String id;

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
     * 操作人的姓名
     */
    private String phoneNumber;

    /**
     * 日志描述
     */
    private String description;

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

    /**
     * 创建时间
     */
    private LocalDateTime createTime;
}
