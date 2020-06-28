package com.soft1851.smart.campus.controller;

import com.soft1851.smart.campus.constant.ResponseResult;
import com.soft1851.smart.campus.model.dto.JobAddDto;
import com.soft1851.smart.campus.model.dto.JobPageDto;
import com.soft1851.smart.campus.service.JobService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author su
 * @className JobController
 * @Description TODO
 * @Date 2020/6/17
 * @Version 1.0
 **/
@Slf4j
@RestController
@RequestMapping("/job")
@Api(tags = "校园聘的接口")
public class JobController {

    @Resource
    private JobService jobService;

    @PostMapping("/list")
    @ApiOperation(value = "job列表")
    public ResponseResult jobList(@RequestBody JobPageDto jobPageDto){
        return ResponseResult.success(jobService.jobList(jobPageDto));
    }

    @PostMapping("/add")
    @ApiOperation(value = "添加职位")
    public ResponseResult jobAdd(@RequestBody JobAddDto jobAddDto){
        return ResponseResult.success(jobService.insertJob(jobAddDto));
    }

    @PostMapping("/update")
    @ApiOperation(value = "编辑职位")
    public ResponseResult jobUpdate(@RequestBody JobAddDto jobAddDto){
        return ResponseResult.success(jobService.updateJob(jobAddDto));
    }



}
