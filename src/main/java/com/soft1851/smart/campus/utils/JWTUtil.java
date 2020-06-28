package com.soft1851.smart.campus.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import lombok.extern.slf4j.Slf4j;

import java.util.Date;

/**
 * @Description TODO
 * @Author wf
 * @Date 2020/5/23
 * @Version 1.0
 */
@Slf4j
public class JWTUtil {
    /**
     * 加密
     *
     * @param phoneNumber
     * @param secret
     * @return String
     */
    public static String getToken(final String phoneNumber, String secret, String roleId ) {
        String token = null;
        token = JWT.create()
                .withClaim("phoneNumber", phoneNumber)
                .withClaim("roleId", roleId)
                .withExpiresAt(new Date(System.currentTimeMillis() + 5*60*1000)) //过期时间
                // 使用了HMAC256加密算法, mySecret是用来加密数字签名的密钥
                .sign(Algorithm.HMAC256(secret)); //signature

        return token;
    }

    /**
     * 获的token的信息无需secret解密也能获得
     *
     * @param token
     * @return token中的用户id
     */
    public static String getPhoneNumber(String token) {
        try {
            DecodedJWT jwt = JWT.decode(token);
            return jwt.getClaim("phoneNumber").asString();
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * 获取角色id
     * @param token
     * @return
     */
    public static String getRoleId(String token) {
        try {
            DecodedJWT jwt = JWT.decode(token);
            return jwt.getClaim("roleId").asString();
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * 解密
     *
     * @param token
     * @return DecodedJWT
     */
    public static boolean deToken(final String token,String phoneNumber, String secret, String roleId) {
        try {
            JWTVerifier verifier = null;
            verifier = JWT.require(Algorithm.HMAC256(secret))
                    .withClaim("phoneNumber", phoneNumber)
                    .withClaim("roleId", roleId)
                    .build();
            assert verifier != null;
            verifier.verify(token);
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    public static void main(String[] args) {
        /*String token = JWTUtil.getToken("1", "123");
        String userId = JWTUtil.getPhoneNumber(token);
        log.info(token);
        System.out.println(userId);*/
    }


}
