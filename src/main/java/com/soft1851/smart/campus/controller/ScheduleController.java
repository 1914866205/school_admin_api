package com.soft1851.smart.campus.controller;

import com.soft1851.smart.campus.constant.ResponseResult;
import com.soft1851.smart.campus.model.dto.ScheduleDto;
import com.soft1851.smart.campus.model.dto.SchedulesDto;
import com.soft1851.smart.campus.model.dto.SingleParam;
import com.soft1851.smart.campus.service.ScheduleService;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @author xunmi
 * @ClassName ScheduleController
 * @Description 课表控制器
 * @Date 2020/5/30
 * @Version 1.0
 **/
@RestController
@RequestMapping("/timetable")
@Api(value = "ScheduleController", tags = "课程表管理接口")
public class ScheduleController {

    @Resource
    private ScheduleService scheduleService;

    /**
     * 获取指定 学期、班级、周次的课表信息接口
     *
     * @param scheduleDto
     * @return
     */
    @PostMapping("/info")
    public ResponseResult getInfo(@RequestBody ScheduleDto scheduleDto) {
        return ResponseResult.success(scheduleService.getScheduleInfo(scheduleDto.getSemesterId(), scheduleDto.getClazzId(), scheduleDto.getWeek()));
    }

    /**
     * 获取课表 id 查找课表信息接口
     *
     * @param singleParam
     * @return
     */
    @PostMapping("/scheduleId")
    public ResponseResult getInfoById(@RequestBody SingleParam singleParam) {
        return ResponseResult.success(scheduleService.getScheduleInfoById(singleParam.getPkId()));
    }

    /**
     * 新增课表
     *
     * @param schedulesDto
     */
    @PostMapping("/increase")
    public void increase(@RequestBody SchedulesDto schedulesDto) {
        System.out.println(schedulesDto);
        scheduleService.increase(schedulesDto);
    }


}
