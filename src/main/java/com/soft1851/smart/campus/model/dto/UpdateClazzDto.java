package com.soft1851.smart.campus.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Tao
 * @version 1.0
 * @ClassName UpdateClazzDto
 * @Description TODO
 * @date 2020-06-17 17:40
 **/
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UpdateClazzDto {
    private Long clazzId;
    private String clazzHeadmaster;
    private Long collegeId;
    private String grade;
    private String name;
}
