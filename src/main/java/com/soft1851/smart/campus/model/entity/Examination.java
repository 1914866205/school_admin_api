package com.soft1851.smart.campus.model.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * @author Yujie_Zhao
 * @ClassName Examination
 * @Description 考务实体类
 * @Date 2020/5/25  22:22
 * @Version 1.0
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "examination",indexes = {@Index(name = "clazz_idIndex",columnList = "clazz_id")})
public class Examination {
    /**
     * 主键，策略为自增
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long pkExaminationId;

    /**
     * 学期
     * nullable = false为非空约束，unique = true是唯一约束
     */
    @Column(nullable = false, length = 60)
    private String semester;

    /**
     * 科目id
     */
    @Column(nullable = false)
    private Long subjectId;

    /**
     * 班级id
     */
    @Column(name = "clazz_id",nullable = false)
    private Long clazzId;

    /**
     * 监考老师
     */
    @Column(nullable = false, length = 32)
    private String teacherName;

    /**
     * 监考老师用户id
     */
    @Column(nullable = false, length = 256)
    private String teacherId;


    /**
     * 地点
     */
    @Column(nullable = false)
    private String area;

    /**
     * 试卷分数
     */
    @Column(nullable = false, length = 4)
    private Integer score;

    /**
     * 考试类型
     */
    @Column(nullable = false ,length = 32)
    private String type;

    /**
     * 考生学号
     */
    @Column(nullable = false ,length = 32)
    private String jobNumber;

    /**
     * 创建时间
     */
    @Column(nullable = false)
    private Timestamp gmtCreate;


    /**
     * 修改时间
     */
    @UpdateTimestamp
    @Column(nullable = false)
    private Timestamp gmtModified;

    /**
     * 删除标志（0 逻辑删除， 1 未删除）
     */
    @Column(nullable = false,length = 4)
    private Boolean isDeleted;

    /**
     * 是否参与（0 未参与， 1 已参与）
     */
    @Column(nullable = false,length = 4)
    private Boolean isJoin;

    /**
     * 考试结束时间
     */
    @Column(nullable = false)
    private Timestamp finishTime;
    /**
     * 考试开始时间
     */
    @Column(nullable = false)
    private Timestamp startTime;
}
