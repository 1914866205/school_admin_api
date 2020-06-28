package com.soft1851.smart.campus.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

/**
 * @author su
 * @className PartJobDto
 * @Description TODO
 * @Date 2020/6/19
 * @Version 1.0
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PartJobDto {

    private Long id;
    private String name;
    private String description;
    private String bossId;
    private String bossName;
    private String bossPhone;
    private String bossAvatar;
    private String workplace;
    private String workingDate;
    private String workingTime;
    private BigDecimal pay;
    private String payType;
    private String jobType;
    private Integer number;

}
