package com.soft1851.smart.campus.model.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Tao
 * @version 1.0
 * @ClassName ExaminationStudentVo
 * @Description 某个教务下的学生字段
 * @date 2020-06-22 10:53
 **/
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ExaminationStudentVo {
    private String jobNumber;
    private String userName;
    private String name;
}
