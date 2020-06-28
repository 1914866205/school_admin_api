package com.soft1851.smart.campus.model.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;

/**
 * @author Tao
 * @version 1.0
 * @ClassName Tower
 * @Description 楼栋类
 * @date 2020-05-25 21:59
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "tower",indexes = {@Index(name = "name_index",columnList = "name")})
@EntityListeners(AuditingEntityListener.class)
public class Tower {
    /**
     * 主键
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "pk_tower_id",nullable = false)
    private Long pkTowerId;

    /**
     * 娄冬梅
     */
    @Column(name = "name",nullable = false,length = 20)
    private String name	;

    /**
     * 经度
     */
    @Column(name = "longitude",nullable = false,length = 20)
    private String longitude;

    /**
     * 纬度
     */
    @Column(name = "latitude",nullable = false,length = 20)
    private String latitude;

    /**
     * 类型（0 教学  1 宿舍楼   2 食堂）
     */
    @Column(name = "type",nullable = false,length = 4)
    private Integer type;

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
