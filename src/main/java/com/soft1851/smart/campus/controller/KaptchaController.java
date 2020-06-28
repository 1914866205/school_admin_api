package com.soft1851.smart.campus.controller;

import cn.hutool.http.server.HttpServerResponse;
import com.soft1851.smart.campus.model.dto.QueryDto;
import com.soft1851.smart.campus.service.RedisService;
import com.soft1851.smart.campus.utils.KaptchaUtil;
import com.sun.net.httpserver.HttpServer;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.annotation.Resource;
import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Map;

/**
 * @Description TODO
 * @Author wf
 * @Date 2020/6/10
 * @Version 1.0
 */
@RestController
@RequestMapping("kaptcha")
public class KaptchaController {
    @Resource
    private RedisService redisService;

    @PostMapping()
    public void createCode() {
        Map<String, Object> code = KaptchaUtil.generatorOperationVerificationCode();
        ServletRequestAttributes sra = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        assert sra != null;
        HttpServletResponse response = sra.getResponse();
        String codeText = code.get("verificationCode").toString();
        BufferedImage image = (BufferedImage) code.get("verificationCodeImage");
        redisService.set("code", codeText, (long) 1);
        System.out.println(redisService.getValue("code", String.class));
        assert response != null;
        System.out.println("结果是: " + codeText);
        response.setContentType("image/jpeg");
        response.setDateHeader("Expires", 0);
        try {
            ImageIO.write(image, "png", response.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
