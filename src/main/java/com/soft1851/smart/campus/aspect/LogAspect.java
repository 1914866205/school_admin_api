package com.soft1851.smart.campus.aspect;

import com.alibaba.fastjson.JSON;
import com.soft1851.smart.campus.annotation.OperationLogDetail;
import com.soft1851.smart.campus.model.vo.LogInfo;
import com.soft1851.smart.campus.utils.FileUtil;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

/**
 * @Description APO切面类
 * @Author wf
 * @Date 2020/5/26
 * @Version 1.0
 */
@Aspect
@Component
public class LogAspect {

    private static final Logger Log = LoggerFactory.getLogger(LogAspect.class);

    private LogInfo logInfo = new LogInfo();

    /**
     * 定义切点
     * 使用注解的方式
     */
    @Pointcut("@annotation(com.soft1851.smart.campus.annotation.OperationLogDetail)")
    public void operationLog(){}

    /**
     * 环绕增强，相当于 MethodInterceptor
     */
    @Around("operationLog()")
    public Object doAround(ProceedingJoinPoint joinPoint) throws Throwable {
        Object res = null;
        long time = System.currentTimeMillis();
        try {
            res =  joinPoint.proceed();
            time = System.currentTimeMillis() - time;
            return res;
        } finally {
            try {
                //方法执行完成后增加日志
                addLog(joinPoint,res,time);
            }catch (Exception e){
                Log.error("LogAspect 操作失败：" + e.getMessage());
            }
        }
    }

    /**
     * 方法执行完成后增加日志
     * @param joinpoint
     * @param res
     * @param time
     */
    private void addLog(JoinPoint joinpoint, Object res, long time) {
        //获取方法签名
        MethodSignature signature = (MethodSignature) joinpoint.getSignature();
        //实例化日志实体类
        logInfo.setRunTime(time);
        logInfo.setReturnValue(JSON.toJSONString(res));
        String phoneNumber = "";
        logInfo.setCreateTime(LocalDateTime.now());
        logInfo.setMethod(signature.getDeclaringTypeName() + "." + signature.getName());
        OperationLogDetail annotation = null;
        try {
            //获取抽象方法
            Method method = signature.getMethod();
            //获取当前类的对象
            Class<?> clazz = joinpoint.getTarget().getClass();
            //获取当前类的对象
            method = clazz.getMethod(method.getName(), method.getParameterTypes());
            annotation = method.getAnnotation(OperationLogDetail.class);
        } catch (Exception e) {
            Log.error("获取当前类有OperationLogDetail 注解的方法 异常", e);
        }
        if (annotation != null) {
            logInfo.setOperationType(annotation.operationType().getValue());
            logInfo.setOperationUnit(annotation.operationUtil().getValue());
            logInfo.setDescription(annotation.detail());
        }
        Log.info("记录日志：" + logInfo.toString());
    }

//    /**
//     * 方法执行完成后增加日志
//     * @param joinpoint
//     * @param res
//     * @param time
//     */
//    private void addLog(JoinPoint joinpoint, Object res, long time) {
//        MethodSignature signature = (MethodSignature) joinpoint.getSignature();
//        OperationLog operationLog = new OperationLog();
//        operationLog.setRunTime(time);
//        operationLog.setReturnValue(JSON.toJSONString(res));
//        String phoneNumber = "";
//        Log.info("参数为：", joinpoint.getArgs()[0]);
//        operationLog.setId(phoneNumber);
//        operationLog.setCreateTime(new Date());
//        operationLog.setMethod(signature.getDeclaringTypeName() + "." + signature.getName());
//        operationLog.setUserId("#{currentUserId}");
//        operationLog.setUserName("#{currentUserName}");
//        OperationLogDetail annotation = null;
//        try {
//            //获取抽象方法
//            Method method = signature.getMethod();
//            //获取当前类的对象
//            Class<?> clazz = joinpoint.getTarget().getClass();
//            //获取当前类的对象
//            method = clazz.getMethod(method.getName(), method.getParameterTypes());
//            annotation = method.getAnnotation(OperationLogDetail.class);
//        } catch (Exception e) {
//            Log.error("获取当前类有OperationLogdETAIL 注解的方法 异常", e);
//        }
//        if (annotation != null) {
//            operationLog.setLevel(annotation.level());
//            operationLog.setDescribe(annotation.detail());
//            operationLog.setOperationType(annotation.operationType().getValue());
//            operationLog.setOperationUnit(annotation.operationUtil().getValue());
//        }
//        Log.info("记录日志：" + operationLog.toString());
//    }

    /**
     * 对当前登录用户和占位符处理
     * @param argNames 方法参数名称数组
     * @param args 方法参数数组
     * @param annotation 注解信息
     * @return 返回处理后的描述
     */
    @Deprecated
    private String getDetail(String[] argNames, Object[] args, OperationLogDetail annotation){

        Map<Object, Object> map = new HashMap<>(4);
        for(int i = 0;i < argNames.length;i++){
            map.put(argNames[i],args[i]);
        }

        String detail = annotation.detail();
        try {
            detail = "'" + "#{currentUserName}" + "'=》" + annotation.detail();
            for (Map.Entry<Object, Object> entry : map.entrySet()) {
                Object k = entry.getKey();
                Object v = entry.getValue();
                detail = detail.replace("{{" + k + "}}", JSON.toJSONString(v));
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return detail;
    }

    @Before("operationLog()")
    public void doBeforeAdvice(JoinPoint joinPoint){
        Log.info("进入方法前执行.....");
    }

    /**
     * 处理完请求，返回内容
     * @param ret
     */
    @AfterReturning(returning = "ret", pointcut = "operationLog()")
    public void doAfterReturning(Object ret) {
        logInfo.setReturnValue(ret.toString());
        FileUtil.saveLogInfo(logInfo.toString());
        Log.info("日志信息：" + logInfo.toString());
    }

    /**
     * 后置异常通知
     */
    @AfterThrowing("operationLog()")
    public void throwss(JoinPoint jp){
        Log.info("方法异常时执行.....");
    }

    /**
     * 后置最终通知,final 增强，不管是抛出异常或者正常退出都会执行
     */
    @After("operationLog()")
    public void after(JoinPoint jp){
        Log.info("方法最后执行.....");
    }


}
