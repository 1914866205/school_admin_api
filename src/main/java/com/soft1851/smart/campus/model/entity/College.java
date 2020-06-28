package com.soft1851.smart.campus.model.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * @author Tao
 * @version 1.0
 * @ClassName College
 * @Description 学院
 * @date 2020-06-17 9:53
 **/
@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class College {
    /**
     * 主键
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long pkCollegeId;

    /**
     * 学院名称
     */
    @Column(nullable = false, length = 128)
    private String collegeName;

    /**
     * 学院院长教工号
     */
    @Column(nullable = false, length = 50)
    private String deanJobNumber;

    /**
     * 创建时间
     */
    @Column(nullable = false)
    private Timestamp gmtCreate;

    /**
     * 更新时间
     */
    @Column(nullable = false)
    @CreationTimestamp
    private  Timestamp gmtModified;


    /**
     * 删除标志
     */
    @Column(nullable = false,length = 4)
    private  Boolean isDeleted;


}
