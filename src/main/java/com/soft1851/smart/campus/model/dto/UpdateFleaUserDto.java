package com.soft1851.smart.campus.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author yhChen
 * @Description
 * @Date 2020/6/10
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UpdateFleaUserDto {
    /**
     * 用户id
     */
    private Long pkFleaUserId;

    /**
     * 用户昵称
     */
    private String nickname;

    /**
     * 手机号
     */
    private String phoneNumber;

    /**
     * 用户头像
     */
    private String avatar;

    /**
     * 性别
     */
    private String sex;
}
