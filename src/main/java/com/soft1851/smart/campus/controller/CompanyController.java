package com.soft1851.smart.campus.controller;

import com.soft1851.smart.campus.constant.ResponseResult;
import com.soft1851.smart.campus.model.dto.CompanyDto;
import com.soft1851.smart.campus.model.dto.JobDto;
import com.soft1851.smart.campus.model.dto.JobPageDto;
import com.soft1851.smart.campus.service.CompanyService;
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
 * @className CompanyController
 * @Description TODO
 * @Date 2020/6/17
 * @Version 1.0
 **/
@Slf4j
@RestController
@RequestMapping("/company")
@Api(tags = "校园聘的接口")
public class CompanyController {

    @Resource
    private CompanyService companyService;


    @PostMapping("/list")
    @ApiOperation(value = "公司列表",  notes = "请求参数为JobPageDto")
    public ResponseResult typeList(@RequestBody JobPageDto jobPageDto){
        return ResponseResult.success(companyService.findAll(jobPageDto));
    }

    @PostMapping("/add")
    @ApiOperation(value = "添加公司",  notes = "请求参数为CompanyDto")
    public ResponseResult addCompany(@RequestBody CompanyDto companyDto){
        return ResponseResult.success(companyService.addCompany(companyDto));
    }

    @PostMapping("/update")
    @ApiOperation(value = "更新公司",  notes = "请求参数为CompanyDto")
    public ResponseResult upCompany(@RequestBody CompanyDto companyDto){
        return ResponseResult.success(companyService.update(companyDto));
    }

    @PostMapping("/remove")
    @ApiOperation(value = "删除公司信息",  notes = "请求参数为CompanyDto")
    public ResponseResult upCompany(@RequestBody JobDto jobDto){
        return ResponseResult.success(companyService.delete(jobDto));
    }
}
