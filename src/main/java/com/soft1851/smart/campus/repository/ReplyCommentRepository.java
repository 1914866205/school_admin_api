package com.soft1851.smart.campus.repository;

import com.soft1851.smart.campus.model.entity.ReplyComment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author Yujie_Zhao
 * @ClassName ReplyCommentRepository
 * @Description 子评论
 * @Date 2020/6/19  13:38
 * @Version 1.0
 **/
public interface ReplyCommentRepository extends JpaRepository<ReplyComment ,String> {
    /**
     *
     * @param commentId
     * @return
     */
    List<ReplyComment> findReplyCommentByCommentId(String commentId);

    /**
     * 批量修改
     * @param ids
     * @return int
     */
    @Modifying
    @Transactional(rollbackFor = RuntimeException.class)
    @Query("update ReplyComment t set t.isDeleted = true where t.commentId in (?1)")
    int updateIsDelete(String ids);

    /**
     * 批量修改
     * @param ids
     * @return int
     */
    @Modifying
    @Transactional(rollbackFor = RuntimeException.class)
    @Query("update ReplyComment t set t.isDeleted = true where t.commentId in (?1)")
    int updateIsDelete(List<String> ids);

}
