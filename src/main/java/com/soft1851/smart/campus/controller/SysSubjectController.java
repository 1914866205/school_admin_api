package com.soft1851.smart.campus.controller;

import com.soft1851.smart.campus.constant.ResponseResult;
import com.soft1851.smart.campus.model.dto.QueryDto;
import com.soft1851.smart.campus.model.entity.SysSubject;
import com.soft1851.smart.campus.service.SysSubjectService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Description TODO
 * @Author wf
 * @Date 2020/6/21
 * @Version 1.0
 */
@RestController
@RequestMapping(value = "/subject")
@Api(value = "SysSubjectController", tags = "科目管理接口")
public class SysSubjectController {
    @Resource
    private SysSubjectService sysSubjectService;

    @ApiOperation(value = "获取所有科目接口")
    @PostMapping(value = "/list")
    public List<SysSubject> selectAll() {
        return sysSubjectService.selectAll();
    }

    /**
     * 模糊查询科目
     * @param queryDto
     * @return
     */
    @ApiOperation(value = "查询所有科目",notes = "关键字给filed1")
    @PostMapping(value = "/all")
    ResponseResult findAllStatement(@RequestBody QueryDto queryDto){
        return sysSubjectService.getSubjectLike(queryDto.getFiled1());
    }

    /**
     * 查询所有科目(用于考务下拉框)
     * @return
     */
    @ApiOperation(value = "查询所有科目(用于考务下拉框)",notes = "")
    @PostMapping(value = "/exam/all")
    ResponseResult getAllSysSubjectVo(){
        return sysSubjectService.getAllSysSubjectVo();
    }
}
