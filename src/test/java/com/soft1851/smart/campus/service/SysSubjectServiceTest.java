package com.soft1851.smart.campus.service;

import com.soft1851.smart.campus.constant.ResponseResult;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class SysSubjectServiceTest {

    @Resource
    private SysSubjectService sysSubjectService;
    @Test
    void getSubjectLike() {
        ResponseResult responseResult = sysSubjectService.getSubjectLike("大学生");
        System.out.println(responseResult);
    }

    @Test
    void getAllSysSubjectVo() {
        ResponseResult responseResult = sysSubjectService.getAllSysSubjectVo();
        System.out.println(responseResult);
    }
}