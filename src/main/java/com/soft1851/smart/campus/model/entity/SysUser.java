package com.soft1851.smart.campus.model.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;


import java.sql.Timestamp;

/**
 * @Description 系统用户表
 * @Author 涛涛
 * @Date 2020/5/25 21:58
 * @Version 1.0
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class SysUser {
    //用户Id
    @Id
    @Column(length = 128)
    private String pkUserId;

    //用户名字
    @Column(length = 60, nullable = false)
    private String sysUserName;

    //用户密码
    @Column(length = 32, nullable = false)
    private String sysPassword;

    //手机号
    @Column(length = 30, unique = true, nullable = false)
    private String sysUserPhoneNumber;

    //头像
    @Column(nullable = false)
    private String sysUserAvatar;

    //加密盐
    @Column(nullable = false, length = 32)
    private String salt;

    //账户状态 ： 0 禁用   1 ：启用
    @Column(length = 4)
    private Boolean isEnabled;

    //创建时间
    @CreatedDate
    @Column(nullable = false)
    private Timestamp gmtCreate;

    //修改时间
    @Column(nullable = false)
    private Timestamp gmtModified;

    //是否删除（1 逻辑删除， 0 未删除）
    @Column(length = 4, nullable = false)
    private Boolean isDeleted;


}
