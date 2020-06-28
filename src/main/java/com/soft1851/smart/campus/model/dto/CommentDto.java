package com.soft1851.smart.campus.model.dto;

import com.soft1851.smart.campus.model.entity.ReplyComment;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.util.List;

/**
 * @author Yujie_Zhao
 * @ClassName CommentDto
 * @Description TODO
 * @Date 2020/6/19  13:41
 * @Version 1.0
 **/
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CommentDto {

    /**
     * 评论id
     */
    private String commentId;

    private String dynamicId;
    /**
     * 用户id
     */
    private String userId;

    /**
     * 评论内容
     */
    private String content;
    /**
     * 创建时间
     */
    private Timestamp gmtCreate;

    List<ReplyComment> replyCommentList;
}
