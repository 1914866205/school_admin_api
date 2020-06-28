package com.soft1851.smart.campus.service;

import com.soft1851.smart.campus.constant.ResponseResult;
import com.soft1851.smart.campus.model.dto.PageDto;

/**
 * @author Yujie_Zhao
 * @ClassName CommentService
 * @Description 评论
 * @Date 2020/6/19  13:40
 * @Version 1.0
 **/
public interface CommentService {

    ResponseResult findAll(Boolean isDelete);

    /**
     * 分页查询所有咨讯
     * @param pageDto
     * @return ResponseResult
     */
    ResponseResult findAllComment(PageDto pageDto);


    /**
     * 单个删除咨询
     * @param id
     * @return
     */
    ResponseResult deleteComment(String id);

    /**
     * 批量删除咨询
     * @param ids
     * @return
     */
    ResponseResult deletedBatch(String ids);

}
