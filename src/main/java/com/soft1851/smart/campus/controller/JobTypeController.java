package com.soft1851.smart.campus.controller;

import com.soft1851.smart.campus.constant.ResponseResult;
import com.soft1851.smart.campus.model.dto.JobDto;
import com.soft1851.smart.campus.service.JobTypeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author su
 * @className JobTypeController
 * @Description TODO
 * @Date 2020/6/17
 * @Version 1.0
 **/
@Slf4j
@RestController
@RequestMapping("/jobType")
@Api(tags = "校园聘的接口")
public class JobTypeController {

    @Resource
    private JobTypeService jobTypeService;

    @PostMapping("/list")
    @ApiOperation(value = "Job类型列表")
    public ResponseResult typeList(){
        return ResponseResult.success(jobTypeService.typeList());
    }

    @PostMapping("/add")
    @ApiOperation(value = "添加Job类型")
    public ResponseResult addType(@RequestBody JobDto jobDto){
        return ResponseResult.success(jobTypeService.add(jobDto));
    }

    @PostMapping("/update")
    @ApiOperation(value = "更新类型")
    public ResponseResult updateType(@RequestBody JobDto jobDto){
        return ResponseResult.success(jobTypeService.update(jobDto));
    }

}
