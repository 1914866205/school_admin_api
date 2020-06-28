package com.soft1851.smart.campus.service;

import com.soft1851.smart.campus.model.dto.SchedulesDto;
import com.soft1851.smart.campus.model.entity.Schedule;
import com.soft1851.smart.campus.model.entity.SysCourse;
import com.soft1851.smart.campus.model.vo.CourseVo;

import java.util.List;

/**
 * @author xunmi
 * @ClassName ScheduleService
 * @Description 课表业务逻辑层
 * @Date 2020/5/30
 * @Version 1.0
 **/
public interface ScheduleService {

    /**
     * 通过指定 学期 班级 周次 查找出课表信息
     *
     * @param semesterId
     * @param clazzId
     * @param week
     * @return
     */
    List<CourseVo> getScheduleInfo(Long semesterId, Long clazzId, Integer week);

    /**
     * 通过课表 id 查找课表信息
     *
     * @param scheduleId
     * @return
     */
    List<CourseVo> getScheduleInfoById(Long scheduleId);

    /**
     * 增加课表的方法
     *
     * @param schedulesDto
     */
    void increase(SchedulesDto schedulesDto);
}
