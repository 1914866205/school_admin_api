package com.soft1851.smart.campus.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author su
 * @className JobAddDto
 * @Description TODO
 * @Date 2020/6/24
 * @Version 1.0
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class JobAddDto {

    private Long id;
    private String name;
    private String description;
    private String boss;
    private String bossPhone;
    private String bossAvatar;
    private Long companyId;
    private String workplace;
    private String workingTime;
    private Integer min;
    private Integer max;
    private String experience;
    private String diploma;
    private Long jobTypeId;
}
