package com.soft1851.smart.campus.exception;


import com.soft1851.smart.campus.constant.ResultCode;

/**
 * @Description TODO
 * @Author wf
 * @Date 2020/4/21
 * @Version 1.0
 */
public class CustomException extends RuntimeException {
    protected ResultCode resultCode;

    public CustomException(String msg, ResultCode resultCode) {
        super(msg);
        this.resultCode = resultCode;
    }

    public ResultCode getResultCode() {
        return resultCode;
    }
}
