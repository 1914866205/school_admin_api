package com.soft1851.smart.campus.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;

/**
 * @author xunmi
 * @ClassName ScheduleDto
 * @Description TODO
 * @Date 2020/5/30
 * @Version 1.0
 **/
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ScheduleDto {
    /**
     * 班级id
     */
    private Long clazzId;

    /**
     * 学期id
     */
    private Long semesterId;

    /**
     * 第几周（第一周、第二周...）
     */
    private Integer week;
}
