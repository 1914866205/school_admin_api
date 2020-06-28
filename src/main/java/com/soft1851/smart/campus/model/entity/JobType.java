package com.soft1851.smart.campus.model.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * @author su
 * @className JobType
 * @Description TODO
 * @Date 2020/6/10
 * @Version 1.0
 **/
@EqualsAndHashCode(callSuper = true)
@Table(name = "job_type")
@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class JobType extends Model<JobType> {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long pkJobTypeId;

    @TableField("name")
    private String name;

    @TableField("parent_id")
    @Column(length = 10)
    private  Long parentId;

    /**
     * 删除标志
     */
    @TableField("is_deleted")
    @Column(nullable = false,length = 1)
    private Boolean isDeleted;
    /**
     * 创建时间
     */
    @TableField("gmt_create")
    @Column(nullable = false)
    private Timestamp gmtCreate;

    /**
     * 更新时间
     */
    @TableField("gmt_modified")
    @Column(nullable = false)
    @CreationTimestamp
    private  Timestamp gmtModified;


}
