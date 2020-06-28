package com.soft1851.smart.campus.controller;

import com.soft1851.smart.campus.constant.ResponseResult;
import com.soft1851.smart.campus.service.CollegeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author Tao
 * @version 1.0
 * @ClassName CollegeController
 * @Description TODO
 * @date 2020-06-17 10:58
 **/
@RestController
@RequestMapping("/college")
@Api(value = "CollegeController", tags = "学院模块接口")
public class CollegeController {
    @Resource
    private CollegeService collegeService;


    @ApiOperation(value = "获取所有学院数据（用于创建班级下拉框）",notes = "")
    @PostMapping(value = "/all")
    ResponseResult getAllCollege(){
        return collegeService.getAllCollege();
    }
}
