package com.soft1851.smart.campus.controller;

import com.soft1851.smart.campus.constant.ResponseResult;
import com.soft1851.smart.campus.model.dto.CourseDto;
import com.soft1851.smart.campus.model.dto.PageDto;
import com.soft1851.smart.campus.model.dto.QueryDto;
import com.soft1851.smart.campus.model.entity.SysCourse;
import com.soft1851.smart.campus.service.SysCourseService;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * @author xunmi
 * @ClassName SysCourseController
 * @Description 课表中的课程控制器
 * @Date 2020/6/1
 * @Version 1.0
 **/
@RestController
@RequestMapping("/course")
@Api(value = "SysCourseController", tags = "课程管理接口")
public class SysCourseController {

    @Resource
    private SysCourseService sysCourseService;

    /**
     * 新增课程
     *
     * @param sysCourse
     */
    @PostMapping("/increase")
    public void increase(@RequestBody SysCourse sysCourse) {
        System.out.println(sysCourse);
        sysCourseService.increase(sysCourse);
    }

    @PostMapping(value = "/list")
    public List<Map<String, Object>> getAllCourse(@RequestBody PageDto pageDto) {
        return sysCourseService.getAllCourses(pageDto);
    }

    @PostMapping(value = "/modification")
    public ResponseResult updateCourseById(@RequestBody CourseDto courseDto) {
        sysCourseService.updateCourseById(courseDto);
        return ResponseResult.success();
    }

    @PostMapping(value = "/delection")
    public ResponseResult updateCourseIsDeletedById(@RequestBody QueryDto queryDto) {
        sysCourseService.updateCourseIsDeletedById(Long.parseLong(queryDto.getField().toString()));
        return ResponseResult.success();
    }
}
