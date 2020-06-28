package com.soft1851.smart.campus.model.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * 描述:
 *
 * @author：Guorc
 * @create 2020-06-16 8:09
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FleaCommentVo {
    private Long commentId;
    /**
     * 回复人信息
     */
    private String reviwerName;
    /**
     * 评论人信息
     */
    private String commentByName;
    /**
     * 评论内容
     */
    private String comment;

    /**
     * 评论悬赏帖标题
     */
    private String  title;

    private Date createTime;
}
