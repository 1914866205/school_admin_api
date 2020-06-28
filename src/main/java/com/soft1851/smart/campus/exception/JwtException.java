package com.soft1851.smart.campus.exception;

import com.soft1851.smart.campus.constant.ResultCode;

/**
 * @ClassName JwtException
 * @Description 自定义异常
 * @Author wf
 * @Date 2020/4/15
 * @Version 1.0
 */
public class JwtException extends RuntimeException {

    protected ResultCode resultCode;

    public JwtException(String msg, ResultCode resultCode) {
        super(msg);
        this.resultCode = resultCode;
    }

    public ResultCode getResultCode() {
        return resultCode;
    }
}