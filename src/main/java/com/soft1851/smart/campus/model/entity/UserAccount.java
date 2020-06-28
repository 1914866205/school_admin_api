package com.soft1851.smart.campus.model.entity;

import com.soft1851.smart.campus.annotation.ExcelVoAttribute;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.sql.Date;
import java.sql.Timestamp;

/**
 * @author Yujie_Zhao
 * @ClassName SysUserAccount
 * @Description 用户账号实体类
 * @Date 2020/5/25  21:56
 * @Version 1.0
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name = "user_account",indexes = {@Index(name = "user_nameIndex",columnList = "user_name")})
public class UserAccount {
    /**
     * 主键，策略为自增
     */
    @Id
    @Column(length = 32)
    private String pkUserAccountId;

    /**
     * 账号
     * nullable = false为非空约束，unique = true是唯一约束.
     */
    @ExcelVoAttribute(name = "用户账号", column = 0)
    @Column(nullable = false, unique = true)
    private String userAccount;

    /**
     * 真实姓名
     */
    @ExcelVoAttribute(name = "用户名称", column = 1)
    @Column(name ="user_name",nullable = false, length = 32)
    private String userName;
    /**
     * 昵称
     */
    @ExcelVoAttribute(name = "用户昵称", column = 2)
    @Column(nullable = false,length = 50)
    private String nickname;

    /**
     * 教工号/学号
     */
    @ExcelVoAttribute(name = "教工号", column = 3)
    @Column(nullable = false, unique = true ,length = 32)
    private String jobNumber;

    /**
     * 密码
     */
    @Column(nullable = false,length = 32)
    private String password;


    /**
     * 头像
     */
    @ExcelVoAttribute(name = "用户头像", column = 4)
    private String avatar;

    /**
     * 角色
     */
    @Column(nullable = false, length = 50)
    private String role;

    /**
     * 手机号
     */
    @ExcelVoAttribute(name = "用户学号", column = 5)
    @Column(nullable = false, unique = true, length = 11)
    private String phoneNumber;

    /**
     * 状态
     */
    @ExcelVoAttribute(name = "帐号状态", column = 6)
    @Column(nullable = false, length = 4)
    private Boolean status;

    /**
     * 所属班级id
     */
    @Column(nullable = false)
    private Long clazzId;

    /**
     * 校园卡号
     */
    @ExcelVoAttribute(name = "校园卡号", column = 7)
    @Column(nullable = false, unique = true,length = 32)
    private String cardNumber;

    /**
     * 创建时间
     */
    @ExcelVoAttribute(name = "创建时间", column = 11)
    @CreatedDate
    @Column(nullable = false)
    private Timestamp gmtCreate;

    /**
     * 修改时间
     */
    @LastModifiedDate
    @UpdateTimestamp
    @Column(nullable = false)
    private Timestamp gmtModified;

    /**
     * 删除标志（0 逻辑删除， 1 未删除）
     */
    @Column(nullable = false,length = 4)
    private Boolean isDeleted;

    /**
     * 性别
     */
    @ExcelVoAttribute(name = "性别", column = 8)
    @Column(nullable = false, length = 2)
    private String gender;

    /**
     * 地址
     */
    @ExcelVoAttribute(name = "地址", column = 9)
    @Column(length = 128)
    private String address;

    @ExcelVoAttribute(name = "生日", column = 10)
    @Column(nullable = false)
    private Date birthday;
}
