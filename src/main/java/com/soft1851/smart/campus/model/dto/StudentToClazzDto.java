package com.soft1851.smart.campus.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Tao
 * @version 1.0
 * @ClassName StudentToClazzDto
 * @Description TODO
 * @date 2020-06-17 14:51
 **/
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class StudentToClazzDto {
    private Long clazzId;
    private String studentIds;
}
