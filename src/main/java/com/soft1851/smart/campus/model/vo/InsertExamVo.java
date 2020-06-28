package com.soft1851.smart.campus.model.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Tao
 * @version 1.0
 * @ClassName InsertExamVo
 * @Description TODO
 * @date 2020-06-23 15:34
 **/
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class InsertExamVo {
    private String semester;
    private Long subjectId;
    private Long clazzId;
    private String teacherName;
    private String teacherId;
    private String area;
    private Integer score;
    private String type;
    private String finishTime;
    private String startTime;
}
