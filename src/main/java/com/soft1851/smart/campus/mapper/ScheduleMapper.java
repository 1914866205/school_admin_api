package com.soft1851.smart.campus.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.soft1851.smart.campus.model.entity.Schedule;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.ibatis.annotations.Select;

import java.util.Map;

/**
 * @Description TODO
 * @Author wf
 * @Date 2020/6/18
 * @Version 1.0
 */
public interface ScheduleMapper extends BaseMapper<Schedule> {

    /**
     * 根据id查询课程表学期信息
     * @param id
     * @return
     */
    @Select(value = "SELECT s.name, sd.clazz_id, sd.semester_id FROM sys_semester s " +
            "LEFT JOIN schedule sd " +
            "ON s.pk_semester_id = sd.semester_id " +
            "WHERE pk_school_timetable_id = #{id}")
    Map<String, Object> getScheduleSemesterById(long id);
}
