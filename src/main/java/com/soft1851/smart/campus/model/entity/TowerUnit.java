package com.soft1851.smart.campus.model.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * @Description TODO
 * @Author wf
 * @Date 2020/6/16
 * @Version 1.0
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class TowerUnit {
    /**
     * 主键id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long unitId;

    /**
     * 单元名称
     */
    @Column(name = "name", nullable = false)
    private String name;
    /**
     * 创建时间
     */
    @Column(name = "gmt_create",nullable = false)
    private Timestamp gmtCreate;

    /**
     * 更新时间
     */
    @LastModifiedDate
    @UpdateTimestamp
    @Column(name = "gmt_modified",nullable = false)
    private Timestamp gmtModified;

    /**
     * 删除标志
     */
    @Column(name = "is_deleted",nullable = false,length = 4)
    private Boolean isDeleted;
}
