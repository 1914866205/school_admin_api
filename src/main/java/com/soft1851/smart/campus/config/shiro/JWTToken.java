package com.soft1851.smart.campus.config.shiro;

import org.apache.shiro.authc.AuthenticationToken;

/**
 * @Description TODO
 * @Author wf
 * @Date 2020/5/23
 * @Version 1.0
 */
public class JWTToken implements AuthenticationToken {
    //密钥
    private String token;

    public JWTToken(String token) {
        this.token = token;
    }

    @Override
    public Object getPrincipal() {
        return token;
    }

    @Override
    public Object getCredentials() {
        return token;
    }
}
