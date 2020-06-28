package com.soft1851.smart.campus.service.Impl;

import com.soft1851.smart.campus.constant.ResponseResult;
import com.soft1851.smart.campus.model.dto.FleaCommentDto;
import com.soft1851.smart.campus.model.dto.FleaRewardBatchIdDto;
import com.soft1851.smart.campus.model.dto.PageDto;
import com.soft1851.smart.campus.model.entity.FleaComment;
import com.soft1851.smart.campus.repository.FleaCommentRepository;
import com.soft1851.smart.campus.repository.FleaRewardRepository;
import com.soft1851.smart.campus.repository.FleaUserRepository;
import com.soft1851.smart.campus.service.FleaCommentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author yhChen
 * @Description
 * @Date 2020/6/12
 */
@Slf4j
@Service
public class FleaCommentServiceImpl implements FleaCommentService {

    @Resource
    private FleaCommentRepository commentRepository;
    @Override
    public ResponseResult delComment(FleaCommentDto commentDto) {
        commentRepository.deleteById(commentDto.getCommentId());
        return ResponseResult.success("删除成功");
    }

    @Override
    public ResponseResult getAll(PageDto pageDto) {
        Pageable pageable = PageRequest.of(pageDto.getCurrentPage(),pageDto.getPageSize(), Sort.Direction.DESC,"createTime");
        return ResponseResult.success(commentRepository.selectAll(pageable));
    }

    @Override
    public ResponseResult batchDel(FleaRewardBatchIdDto idDto) {
        return ResponseResult.success(commentRepository.batchlDel(idDto.getId()));
    }
}
