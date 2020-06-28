package com.soft1851.smart.campus.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

/**
 * @author Yujie_Zhao
 * @ClassName DynamicFindDto
 * @Description 查找id
 * @Date 2020/6/16  8:47
 * @Version 1.0
 **/
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DynamicFindDto {
    private String userName;

    private String nickName;
    /**
     * 主键，策略为自增
     */
    private String pkDynamicId;

    /**
     * 用户id
     */
    private String userId;

    /**
     * 标题
     */
    private String title;

    /**
     * 内容
     */
    private String content;
    /**
     * 点赞数
     */
    private Integer thumbs;
    /**
     * 评论数
     */
    private Integer comments;

    /**
     * 类型标签
     */
    private String type;

    /**
     * 创建时间
     */
    private Timestamp gmtCreate;

    /**
     * 修改时间
     */
    private Timestamp gmtModified;

    /**
     * 删除标志（0 逻辑删除， 1 未删除）
     */
    private Boolean isDeleted;


}
