package com.soft1851.smart.campus.model.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Description TODO
 * @Author wf
 * @Date 2020/6/20
 * @Version 1.0
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SchedulesVo {
    /**
     * 周几
     */
    private Integer weekDay;
    /**
     * 课程名称
     */
    private String course;
    /**
     * 第几节课
     */
    private Integer time;
    /**
     * 课程id
     */
    private Long courseId;
    /**
     * 教师jobNumber
     */
    private String teacherId;
    /**
     * 教师名称
     */
    private String teacher;
    /**
     * 楼栋名称
     */
    private String towerName;
    /**
     * 楼栋id
     */
    private Long towerId;
    /**
     * 房间名称
     */
    private String roomName;
    /**
     * 房间id
     */
    private Long roomId;

}
