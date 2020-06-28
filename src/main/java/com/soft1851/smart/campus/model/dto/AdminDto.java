package com.soft1851.smart.campus.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Tao
 * @version 1.0
 * @ClassName Admin
 * @Description TODO
 * @date 2020-06-01 18:59
 **/
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AdminDto {
    private String userId;
    private String name;
    private String role;
    private String phoneNumber;
    private Boolean isEnabled;
}
