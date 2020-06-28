package com.soft1851.smart.campus.model.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;

/**
 * @author xunmi
 * @ClassName CourseVo
 * @Description TODO
 * @Date 2020/5/30
 * @Version 1.0
 **/
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CourseVo {

    /**
     * 课程名称
     */
    private String subjectName;

    /**
     * 背景颜色
     */
    private String backgroundColor;

    /**
     * 教师名称
     */
    private String teacherName;

    /**
     * 房间名称
     */
    private String roomName;

    /**
     * 周几
     */
    private Integer weekDay;

    /**
     * 上课时间
     *
     * 1  →  上午 1-2节
     *
     * 2 → 上午 3-4节
     */
    private Integer time;

    /**
     * 周次（例如 1-5，8-13）
     */
    private String weekDuration;

}
