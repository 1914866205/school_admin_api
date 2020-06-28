package com.soft1851.smart.campus.filter;

import cn.hutool.http.server.HttpServerRequest;
import com.soft1851.smart.campus.config.shiro.JWTToken;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.web.filter.authc.BasicHttpAuthenticationFilter;
import org.springframework.context.annotation.Configuration;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

/**
 * @Description TODO
 * @Author wf
 * @Date 2020/5/23
 * @Version 1.0
 */
@Slf4j
public class JWTFilter extends BasicHttpAuthenticationFilter {
    //登录标识
    private static String LOGIN_SIGN = "Authorization";

    /**
     * 检测用户是否登录
     * 检测header里面是否包含Authorization字段即可}
     *
     * @param request
     * @param response
     * @return
     */
    @Override
    protected boolean isLoginAttempt(ServletRequest request, ServletResponse response) {
        /*HttpServerRequest req = (HttpServerRequest) request;*/
        HttpServletRequest req = (HttpServletRequest) request;
        String authorization = req.getHeader(LOGIN_SIGN);
        return authorization != null;
    }

    @Override
    protected boolean executeLogin(ServletRequest request, ServletResponse response) throws Exception {
        HttpServletRequest req = (HttpServletRequest) request;
        String header = req.getHeader(LOGIN_SIGN);
        JWTToken token = new JWTToken(header);
        //触发realm中的登录
        getSubject(request, response).login(token);
        //如果没有抛出异常则代表登录成功，返回true
        return true;
    }

    /**
     * 返回true的原因：
     * 例如我们提供一个地址 GET /article
     * 登入用户和游客看到的内容是不同的
     * 如果在这里返回了false，请求会被直接拦截，用户看不到任何东西
     * 所以我们在这里返回true，Controller中可以通过 subject.isAuthenticated() 来判断用户是否登入
     * 如果有些资源只有登入用户才能访问，我们只需要在方法上面加上 @RequiresAuthentication 注解即可
     * 但是这样做有一个缺点，就是不能够对GET,POST等请求进行分别过滤鉴权(因为我们重写了官方的方法)，但实际上对应用影响不大
     * @param request
     * @param response
     * @param mappedValue
     * @return
     */
    @Override
    protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) {
        if (isLoginAttempt(request, response)) {
            try {
                executeLogin(request, response);
            } catch (Exception e) {
                log.info("登录权限不足");
            }
        }
        return true;
    }

    @Override
    protected boolean preHandle(ServletRequest request, ServletResponse response) throws Exception {
        return super.preHandle(request, response);
    }


}
