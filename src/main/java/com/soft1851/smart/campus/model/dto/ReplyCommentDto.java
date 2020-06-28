package com.soft1851.smart.campus.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

/**
 * @author Yujie_Zhao
 * @ClassName ReplyCommentDto
 * @Description TODO
 * @Date 2020/6/19  14:25
 * @Version 1.0
 **/
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ReplyCommentDto {

    private String pkReplyCommentId;

    private String commentId;

    private String userId;

    private String content;

    private Timestamp gmtCreate;

}
