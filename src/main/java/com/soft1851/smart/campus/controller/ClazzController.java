package com.soft1851.smart.campus.controller;

import com.soft1851.smart.campus.constant.ResponseResult;
import com.soft1851.smart.campus.model.dto.*;
import com.soft1851.smart.campus.model.entity.Clazz;
import com.soft1851.smart.campus.service.ClazzService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author Tao
 */
@RestController
@RequestMapping("/clazz")
@Api(value = "ClazzController", tags = "班级管理接口")
public class ClazzController {

    @Resource
    private ClazzService clazzService;

    /**
     * 分页查询所有班级
     *
     * @param pageDto
     * @return
     */
    @ApiOperation(value = "分页查询所有反馈", notes = "")
    @PostMapping(value = "/all")
    ResponseResult findAllClazz(@RequestBody PageDto pageDto) {
        return clazzService.findAllClazz(pageDto);
    }

    @ApiOperation(value = "添加班级", notes = "请求参数为：班主任教工号/学院id/年级/班级名")
    @PostMapping(value = "/increase/clazz")
    ResponseResult increaseClazz(@RequestBody Clazz clazz) {
        return clazzService.increaseClazz(clazz);
    }

    @ApiOperation(value = "向班级中添加学生", notes = "请求参数为：班级id，学生id的字符串（逗号隔开）")
    @PostMapping(value = "/increase/student")
    ResponseResult increaseStudentToClazz(@RequestBody StudentToClazzDto studentToClazzDto) {
        return clazzService.increaseStudentToClazz(studentToClazzDto.getClazzId(),studentToClazzDto.getStudentIds());
    }

    @ApiOperation(value = "删除班级数据", notes = "请求参数为：clazzId（另一个studentIds参数无用/去掉）")
    @PostMapping(value = "/deletion/id")
    ResponseResult deletedClazz(@RequestBody StudentToClazzDto studentToClazzDto) {
        return clazzService.deletedClazz(studentToClazzDto.getClazzId());
    }

    @ApiOperation(value = "修改班级数据", notes = "修改参数为：班级id，班主任教工号/学院id/年级/班级名")
    @PostMapping(value = "/modification")
    ResponseResult increaseStudentToClazz(@RequestBody UpdateClazzDto updateClazzDto) {
        System.out.println(updateClazzDto);
        return clazzService.updateClazz(updateClazzDto);
    }

    @ApiOperation(value = "批量删除班级数据", notes = "请求参数为：班级ids(班级id以逗号隔开的字符串)")
    @PostMapping(value = "/deletionBath")
    ResponseResult deleteBatchByClazzId(@RequestBody BatchDeletionDto batchDeletionDto) {
        return clazzService.deleteBatchByClazzId(batchDeletionDto.getIds());
    }

    @ApiOperation(value = "根据年级或者班级名模糊查询班级id", notes = "请求参数为：Filed1")
    @PostMapping(value = "/like")
    ResponseResult getAllClazzs(@RequestBody QueryDto queryDto) {
        return clazzService.getAllClazzs(queryDto.getFiled1());
    }

    @ApiOperation(value = "查询所有班级用于下拉框", notes = "")
    @PostMapping(value = "/exam/all")
    ResponseResult getAllClazz() {
        return clazzService.getAllClazz();
    }
}

