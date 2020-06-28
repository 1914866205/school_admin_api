package com.soft1851.smart.campus.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author Tao
 * @Description
 * @Date 2020/5/26
 * @Version 1.0
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD})
public @interface ExcelVoAttribute {
    /**
     * 导出Excel的列名
     * @return
     */
    public abstract String name();

    /**
     * 配置实体属性在excel中的展现顺序，如name 在 A(0)
     * @return
     */
    public abstract int column();

    /**
     * 是否需要数字格式化
     * @return
     */
    public abstract boolean isNumber() default false;

    /**
     * 是否需要日期格式化展示
     * @return
     */
    public abstract boolean isDateTime() default false;
}
