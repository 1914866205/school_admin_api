package com.soft1851.smart.campus.controller;

import com.soft1851.smart.campus.constant.ResponseResult;
import com.soft1851.smart.campus.model.entity.SysSemester;
import com.soft1851.smart.campus.service.SysSemesterService;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author xunmi
 * @ClassName SysSemesterController
 * @Description 学期控制器
 * @Date 2020/6/1
 * @Version 1.0
 **/
@RestController
@RequestMapping("/semester")
@Api(value = "SysSemesterController", tags = "学期管理接口")
public class SysSemesterController {

    @Resource
    private SysSemesterService sysSemesterService;

    @PostMapping("/all")
    public List<SysSemester> findAll() {
        return sysSemesterService.findAll();
    }

    @PostMapping("/id")
    public ResponseResult updateSemesterById(@RequestBody SysSemester sysSemester) {
        sysSemesterService.updateSemesterById(sysSemester);
        return ResponseResult.success();
    }

    @PostMapping("/single")
    public ResponseResult insertSemester(@RequestBody SysSemester sysSemester) {
        sysSemesterService.insertSemester(sysSemester);
        return ResponseResult.success();
    }

    @PostMapping("/delection/id")
    public ResponseResult deleteSemesterById(@RequestBody SysSemester sysSemester) {
        sysSemesterService.updateIsDeletedById(sysSemester);
        return ResponseResult.success();
    }
}
