package com.soft1851.smart.campus.model.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * @Description 角色
 * @Author 涛涛
 * @Date 2020/5/25 22:13
 * @Version 1.0
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@EntityListeners(AuditingEntityListener.class)//自动更新时间的注释
@DynamicUpdate(true)
public class SysRole {
    //角色id
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long pkRoleId;

    //角色名
    @Column(length = 32, nullable = false)
    private String roleName;

    //角色描述
    @Column(length = 32)
    private String roleDescription;

    @Column(nullable = false)
    private String roleDecoration;

    //排序字段
    @Column(length = 4, nullable = false)
    private Integer sort;


    //创建时间
    @Column(nullable = false,updatable = false)
    private Timestamp gmtCreate;

    /**
     * 修改时间
     */
    @Column( nullable = false)
    private Timestamp gmtModified;

    //是否删除（1 逻辑删除， 0 未删除）
    @Column(length = 4, nullable = false)
    private Boolean isDeleted;

}
