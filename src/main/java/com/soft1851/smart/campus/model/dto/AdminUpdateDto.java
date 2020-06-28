package com.soft1851.smart.campus.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Tao
 * @version 1.0
 * @ClassName AdminUpdateDto
 * @Description TODO
 * @date 2020-06-01 22:45
 **/
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AdminUpdateDto {
    private String pkUserId;
    private String sysUserName;
    private String sysPassword;
    private String sysUserPhoneNumber;
    private String sysUserAvatar;
    private Boolean isEnabled;
    private Long roleId;
}
