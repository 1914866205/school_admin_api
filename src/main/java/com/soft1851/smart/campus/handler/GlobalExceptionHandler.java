package com.soft1851.smart.campus.handler;

import com.soft1851.smart.campus.constant.ResponseResult;
import com.soft1851.smart.campus.constant.ResultCode;
import com.soft1851.smart.campus.exception.CustomException;
import com.soft1851.smart.campus.exception.JwtException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @ClassName GlobalExceptionHandler
 * @Description TODO
 * @Author wf
 * @Date 2020/4/15
 * @Version 1.0
 */
//@RestControllerAdvice(annotations = {RestController.class, Controller.class})
@Slf4j
public class GlobalExceptionHandler {

    /**
     * 统一运行时异常处理
     * @param e
     * @return
     */
//    @ExceptionHandler
    @ResponseBody
    public ResponseResult dealWithException(Exception e) {
        return ResponseResult.failure(ResultCode.RESULT_CODE_DATA_NONE);
    }
    /**
     * 自定义异常的处理，统一在这里捕获返回JSON格式的友好提示
     *
     * @param exception
     * @return ResponseResult
     */
    @ExceptionHandler(value = {JwtException.class})
    @ResponseBody
    public ResponseResult sendError(JwtException exception) {
        log.error(exception.getMessage());
        return ResponseResult.failure(exception.getResultCode());
    }

    @ExceptionHandler(value = {CustomException.class})
    @ResponseBody
    public ResponseResult handleCustomException(CustomException customException) {
        return ResponseResult.failure(customException.getResultCode());
    }


}