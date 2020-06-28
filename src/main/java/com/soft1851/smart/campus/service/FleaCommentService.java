package com.soft1851.smart.campus.service;

import com.soft1851.smart.campus.constant.ResponseResult;
import com.soft1851.smart.campus.model.dto.FleaCommentDto;
import com.soft1851.smart.campus.model.dto.FleaRewardBatchIdDto;
import com.soft1851.smart.campus.model.dto.PageDto;

/**
 * @author 倪涛涛
 * @version 1.0.0
 * @ClassName FleaGoodsService.java
 * @Description TODO
 * @createTime 2020年06月09日 13:58:00
 */
public interface FleaCommentService {
    /**
     * 根据评论id删除评论
     * @param commentDto
     * @return
     */
    ResponseResult delComment(FleaCommentDto commentDto);

    /**
     * 查询所有评论
     * @return
     */
    ResponseResult getAll(PageDto pageDto);

    /**
     * 批量删除
     * @param idDto
     * @return
     */
    ResponseResult batchDel(FleaRewardBatchIdDto idDto);
}
