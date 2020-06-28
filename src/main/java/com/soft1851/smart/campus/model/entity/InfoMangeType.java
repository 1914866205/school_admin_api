package com.soft1851.smart.campus.model.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * @Description TODO
 * @Author wf
 * @Date 2020/5/25
 * @Version 1.0
 */
@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
@Table(name = "info_manage_type", indexes = {@Index(name = "type_idIndex", columnList = "type_id")})
public class InfoMangeType {

    /**
     * 主键id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long pkInfoManageTypeId;

    /**
     * 分类id
     */
    @Column(name = "type_id")
    private Long typeId;

    /**
     * 资讯类型id
     */
    @Column(name = "info_id" ,length = 32)
    private Long infoId;

    /**
     * 创建时间
     */
    @CreatedDate
    @Column(name = "gmt_create")
    private Timestamp gmtCreate;

    /**
     * 更新时间
     */
    @LastModifiedDate
    @Column(name = "gmt_modified")
    private Timestamp gmtModified;

    /**
     * 删除标志（0 未删除， 1 逻辑删除）
     */
    @Column(name = "is_deleted")
    private Boolean isDeleted;
}
