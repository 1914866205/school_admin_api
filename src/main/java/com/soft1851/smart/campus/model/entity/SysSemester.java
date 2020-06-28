package com.soft1851.smart.campus.model.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;


/**
 * @author Tao
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class SysSemester {
    /**
     * 主键
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "pk_semester_id", nullable = false)
    private Long pkSemesterId;

    /**
     * 名称
     */
    @Column(name = "name" ,nullable = false,length = 32)
    private String name;

    /**
     * 周次数
     */
    @Column(name = "week_count" ,nullable = false)
    private Integer weekCount;

    /**
     * 创建时间
     */
    @Column(name = "gmt_create" ,nullable = false)
    private Timestamp gmtCreate;

    /**
     * 更新时间
     */
    @UpdateTimestamp
    @Column(name = "gmt_modified" ,nullable = false)
    private Timestamp gmtModified;

    /**
     * 删除标志
     */
    @Column(name = "is_deleted" ,nullable = false,length = 4)
    private Boolean isDeleted;

    @Column(name = "open_school_time")
    private Timestamp openSchoolTime;
}
