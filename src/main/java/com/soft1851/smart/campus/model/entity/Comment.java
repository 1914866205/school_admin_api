package com.soft1851.smart.campus.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
 * @ClassName Comment
 * @Description 评论
 * @Author xiaobinggan
 * @Date 2020/6/8 1:05 下午
 * @Version 1.0
 **/
@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class Comment {

    /**
     * 主键，策略为自增
     */
    @Id
    private String pkCommentId;


    /**
     * 动态id
     */
    @Column(name = "dynamic_id", nullable = false)
    private String dynamicId;


    /**
     * 用户id
     */
    @Column(name = "user_id", nullable = false)
    private String userId;

    /**
     * 评论内容
     */
    @Column(name = "content", nullable = false)
    private String content = "";
    /**
     * 创建时间
     */
    @CreatedDate
    @Column(nullable = false)
    private Timestamp gmtCreate;

    /**
     * 修改时间
     */
    @JsonIgnore
    @LastModifiedDate
    @Column(nullable = false)
    private Timestamp gmtModified;

    /**
     * 删除标志（0 逻辑删除， 1 未删除）
     */
//    @JsonIgnore
    @Column(nullable = false, length = 4)
    private Boolean isDeleted;
}
