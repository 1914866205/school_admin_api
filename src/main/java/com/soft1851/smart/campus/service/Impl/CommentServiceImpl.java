package com.soft1851.smart.campus.service.Impl;

import com.soft1851.smart.campus.constant.ResponseResult;
import com.soft1851.smart.campus.constant.ResultCode;
import com.soft1851.smart.campus.model.dto.CommentDto;
import com.soft1851.smart.campus.model.dto.PageDto;
import com.soft1851.smart.campus.model.entity.Comment;
import com.soft1851.smart.campus.model.entity.ReplyComment;
import com.soft1851.smart.campus.repository.DiscussRepository;
import com.soft1851.smart.campus.repository.ReplyCommentRepository;
import com.soft1851.smart.campus.service.CommentService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author Yujie_Zhao
 * @ClassName CommentServiceImpl
 * @Description 评论
 * @Date 2020/6/19  14:41
 * @Version 1.0
 **/
@Service
public class CommentServiceImpl implements CommentService {
    @Resource
    private DiscussRepository discussRepository;
    @Resource
    private ReplyCommentRepository replyCommentRepository;

    @Override
    public ResponseResult findAll(Boolean isDelete) {
        List<Comment> commentList = discussRepository.findAllByIsDeleted(false);
        System.out.println(commentList.size());
        return ResponseResult.success(commentList);
    }

    @Override
    public ResponseResult findAllComment(PageDto pageDto) {
        Pageable pageable = PageRequest.of(
                pageDto.getCurrentPage(),
                pageDto.getPageSize(),
                Sort.Direction.DESC,
                "gmtCreate");
        Page<Comment> comments = discussRepository.findAllByIsDeleted(pageable);
        List<CommentDto> commentDtoList = new ArrayList<>();
        comments.forEach(comment -> {
            CommentDto commentDto = new CommentDto();
            commentDto.setContent(comment.getContent());
            commentDto.setGmtCreate(comment.getGmtCreate());
            commentDto.setCommentId(comment.getPkCommentId());
            commentDto.setUserId(comment.getUserId());
            commentDto.setDynamicId(comment.getDynamicId());
            List<ReplyComment> replyComment = replyCommentRepository.findReplyCommentByCommentId(comment.getPkCommentId());
            commentDto.setReplyCommentList(replyComment);
            commentDtoList.add(commentDto);
        });
        return ResponseResult.success(commentDtoList);
    }

    @Override
    public ResponseResult deleteComment(String id) {
        //根据id查询角色数据是否存在 ，若存在进行删除，不存则返回 数据有误
        Comment comment = discussRepository.findDynamicByPkCommentIdAndIsDeleted(id,false);
        if (comment != null) {
            comment.setIsDeleted(true);
            discussRepository.saveAndFlush(comment);
            replyCommentRepository.updateIsDelete(id);
            return ResponseResult.success("删除成功");
        } else {
            return ResponseResult.failure(ResultCode.RESULT_CODE_DATA_NONE);
        }
    }

    @Override
    public ResponseResult deletedBatch(String ids) {
        //判断是否有数据
        if (ids.length() != 0) {
            //将接收到的ids字符串，使用逗号分割
            String[] idArr = ids.split(",");
            List<String> idsList = new ArrayList<String>();
            //遍历所有id存入到list
//            for (String id : idArr) {
//                //遍历所有id存入到list
//                idsList.add(Long.valueOf(id));
//            }

            Collections.addAll(idsList, idArr);

            int a = discussRepository.updateIsDelete(idsList);
            replyCommentRepository.updateIsDelete(idsList);

            if(a>0){
                return ResponseResult.success("删除成功");
            }else {
                return ResponseResult.success(ResultCode.DATABASE_ERROR);
            }
        } else {
            return ResponseResult.failure(ResultCode.PARAM_IS_BLANK);
        }

    }
}
