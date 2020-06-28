package com.soft1851.smart.campus.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Tao
 * @version 1.0
 * @ClassName UpdateNewExaminationDto
 * @Description TODO
 * @date 2020-06-21 22:54
 **/
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UpdateNewExaminationDto {
    private String area;
    private String startTime;
    private String finishTime;
    private Long subjectId;
    private String teacherName;
    private String teacherId;
    private String type;

    private String semester;
    private Long oldSubjectId;
    private Long clazzId;
}
