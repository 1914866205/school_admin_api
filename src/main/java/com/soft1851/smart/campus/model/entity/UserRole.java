package com.soft1851.smart.campus.model.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.sql.Timestamp;

/**
 * @Description 用户角色关联表
 * @Author 涛涛
 * @Date 2020/5/25 21:47
 * @Version 1.0
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class UserRole {
    /**
     * 主键
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long pkRoleId;

    /**
     * 用户id
     */
    @Column(unique = true)
    @NotNull(message = "sysUserId不能为空")
    private String sysUserId;

    /**
     * 角色id
     */
    @Column()
    private Long roleId;

    /**
     * 创建时间
     */
    @Column(nullable = false)
    private Timestamp gmtCreate;

    /**
     * 修改时间
     */
    @Column(nullable = false)
    private Timestamp gmtModified;

    /**
     * 是否删除（1 逻辑删除， 0 未删除）
     */
    @Column(length = 4, nullable = false)
    private Boolean isDeleted;
}
