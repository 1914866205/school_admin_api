package com.soft1851.smart.campus.model.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

/**
 * @author Tao
 * @version 1.0
 * @ClassName ExamVo
 * @Description 教务查询所有字段
 * @date 2020-06-22 8:18
 **/
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ExamVo {
    private Long pkExaminationId;
    private String semester;
    private Long subjectId;
    private Long clazzId;
    private String teacherName;
    private String teacherId;
    private String area;
    private Integer score;
    private String type;
    private String jobNumber;
    private Timestamp gmtCreate;
    private Timestamp gmtModified;
    private Boolean isDeleted;
    private Boolean isJoin;
    private Timestamp finishTime;
    private Timestamp startTime;
    private String name;
}
