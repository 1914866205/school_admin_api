package com.soft1851.smart.campus.annotation;

import com.soft1851.smart.campus.constant.OperationType;
import com.soft1851.smart.campus.constant.OperationUnit;

import java.lang.annotation.*;

/**
 * @Description 日志自定义注解
 * @Author wf
 * @Date 2020/5/26
 * @Version 1.0
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface OperationLogDetail {

    /**
     * 方法描述:可使用占位符获取参数:{{param}}
     */
    String detail() default "";

    /**
     * 日志等级:自己定，此处分为 1-9
     */
    int level() default 0;

    /**
     * 操作类型(enum):主要是 select,insert,update,delete
     */
    OperationType operationType() default OperationType.UNKNOWN;

    /**
     * 被操作的对象(此处使用 enum):可以是任何对象，如表名(user)，或者是工具(redis)
     */
    OperationUnit operationUtil() default OperationUnit.UNKNOWN;

}
