package com.soft1851.smart.campus.controller;

import com.soft1851.smart.campus.constant.ResponseResult;
import com.soft1851.smart.campus.model.dto.BatchDeletionDto;
import com.soft1851.smart.campus.model.dto.DeletionDto;
import com.soft1851.smart.campus.model.dto.PageDto;
import com.soft1851.smart.campus.service.CommentService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author Yujie_Zhao
 * @ClassName CommentController
 * @Description 动态评论
 * @Date 2020/6/22  8:34
 * @Version 1.0
 **/
@RestController
@Slf4j
@RequestMapping(value = "/comment")
@Api(value = "CommentController",tags = {"校评论接口"})
public class CommentController {
    @Resource
    private CommentService commentService;

    /**
     * 查询所有
     * @return
     */
    @ApiOperation(value = "查找所有动态",notes = "")
    @PostMapping(value = "/allComments")
    public ResponseResult findAll(){
        return commentService.findAll(false);
    }

    /**
     * 批量查找动态(逻辑删除)
     * @return
     */
    @ApiOperation(value = "批量查找动态",notes = "")
    @PostMapping(value = "/all")
    public ResponseResult findAllDynamic(@RequestBody PageDto pageDto){
        return commentService.findAllComment(pageDto);
    }

    /**
     * 删除单个动态(逻辑删除)
     * @return
     */
    @ApiOperation(value = "删除单个动态",notes = "")
    @PostMapping(value = "/deletion")
    public ResponseResult deleteDynamic(@RequestBody DeletionDto deletionDto){
        System.out.println(deletionDto.getId());
        return commentService.deleteComment(deletionDto.getId());
    }

    /**
     * 批量删除动态
     * @return
     */
    @ApiOperation(value = "批量删除动态",notes = "入参为字符串，以逗号隔开")
    @PostMapping(value = "/deletionBath")
    public ResponseResult deletedBatch(@RequestBody BatchDeletionDto batchDeletionDto){
        return commentService.deletedBatch(batchDeletionDto.getIds());
    }
}
