package com.soft1851.smart.campus.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author yhChen
 * @Description 添加用户数据Dto
 * @Date 2020/6/10
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class FleaUserDto {
    /**
     * 学号
     */
    private String jobNumber;
}
