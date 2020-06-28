package com.soft1851.smart.campus.controller;

import com.soft1851.smart.campus.constant.ResponseResult;
import com.soft1851.smart.campus.constant.ResultCode;
import com.soft1851.smart.campus.model.dto.LoginDto;
import com.soft1851.smart.campus.repository.SysUserRepository;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.subject.Subject;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @Description TODO
 * @Author wf
 * @Date 2020/5/23
 * @Version 1.0
 */
@RestController
@Slf4j
@RequestMapping(value = "/user")
@Api(value = "JWTController", tags = "JWT测试接口")
public class JWTController {

    @Resource
    private SysUserRepository sysUserRepository;

    @PostMapping("/login")
    public ResponseResult login(@RequestBody LoginDto loginDto) {
        /*System.out.println(loginDto.getAccount());
        SysUser user = sysUserRepository.getSysUserBySysUserPhoneNumber(loginDto.getAccount());
        System.out.println(user);
        if (user.getSysPassword().equals(loginDto.getPassword())) {
            return ResponseResult.success(JWTUtil.getToken(loginDto.getAccount(), loginDto.getPassword()));
        }else {
            throw new UnauthorizedException();
        }*/
        return null;
    }

    @GetMapping("/article")
    public ResponseResult article() {
        Subject subject = SecurityUtils.getSubject();
        //判断是否登录
        if (subject.isAuthenticated()) {
            return ResponseResult.success("你已经登录了");
        } else {
            return ResponseResult.failure(ResultCode.DATA_IS_WRONG);
        }
    }

    @GetMapping("setting")
    @RequiresPermissions(value = "权限管理")
    public ResponseResult requireAuth() {
        return ResponseResult.success("你拥有设置权限");
    }

    @GetMapping("/test")
    public ResponseResult test() {
        return ResponseResult.success("test");
    }

    //@RequiresPermissions(value = "other")
    @GetMapping("other")
    public String require() {
        return "你拥有other权限";
    }
}