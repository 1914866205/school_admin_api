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
 * @ClassName dynamic
 * @Description 动态
 * @Author xiaobinggan
 * @Date 2020/6/8 10:40 上午
 * @Version 1.0
 **/
@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class Dynamic {

    /**
     * 主键，策略为自增
     */
    @Id
    private String pkDynamicId;

    /**
     * 用户id
     */
    @Column(name = "user_id", nullable = false)
    private String userId;

    /**
     * 标题
     */
    @Column(name = "title", nullable = false, length = 20)
    private String title = "";

    /**
     * 内容
     */
    @Column(name = "content", nullable = false)
    private String content = "";

    /**
     * 点赞数
     */
    @Column(name = "thumbs", nullable = false)
    private Integer thumbs = 0;

    /**
     * 评论数
     */
    @Column(name = "comments", nullable = false)
    private Integer comments = 0;

    /**
     * 类型标签
     */
    @Column(name = "type", nullable = false)
    private String type = "";

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
    @Column(nullable = false, length = 4)
    private Boolean isDeleted;


}
