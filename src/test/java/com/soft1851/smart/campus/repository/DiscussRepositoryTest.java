package com.soft1851.smart.campus.repository;

import com.soft1851.smart.campus.model.entity.Comment;
import com.soft1851.smart.campus.model.entity.ReplyComment;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import javax.annotation.Resource;
import java.util.List;

@SpringBootTest
class DiscussRepositoryTest {

    @Resource
    private DiscussRepository discussRepository;

    @Resource
    private ReplyCommentRepository replyCommentRepository;
    @Test
    void findAllByIsDeleted() {
        Pageable pageable = PageRequest.of(
                0,
                3,
                Sort.Direction.DESC,
                "gmtCreate");
        Page<Comment> sysBooks = discussRepository.findAllByIsDeleted(pageable);
        System.out.println(sysBooks.getContent());
    }

    @Test
    void findReplyCommentByCommentId(){
        List<ReplyComment> replyComments = replyCommentRepository.findReplyCommentByCommentId("15");
        System.out.println(replyComments);
    }

    @Test
    void delete(){
        replyCommentRepository.updateIsDelete("15");
    }
}