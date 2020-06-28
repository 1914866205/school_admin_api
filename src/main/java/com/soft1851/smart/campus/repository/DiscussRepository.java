package com.soft1851.smart.campus.repository;

import com.soft1851.smart.campus.model.entity.Comment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author Yujie_Zhao
 * @ClassName DiscussRepository
 * @Description 评论
 * @Date 2020/6/19  16:08
 * @Version 1.0
 **/
public interface DiscussRepository extends JpaRepository<Comment,String> {


    /**
     *
     * @param isDelete
     * @return
     */
    List<Comment> findAllByIsDeleted(Boolean isDelete);

    /**
     * 分页
     * @param pageable
     * @return
     */
    @Query(value = "select c  " +
            "FROM Comment c " +
            "where  c.isDeleted = false ")
    Page<Comment> findAllByIsDeleted(Pageable pageable);

    /**
     * 根据动态id查询存在
     * @param id
     * @param isDelete
     * @return
     */
    Comment findDynamicByPkCommentIdAndIsDeleted(String id, Boolean isDelete);

    /**
     * 批量修改
     * @param ids
     * @return int
     */
    @Modifying
    @Transactional(rollbackFor = RuntimeException.class)
    @Query("update Comment t set t.isDeleted = true where t.pkCommentId in (?1)")
    int updateIsDelete(List<String> ids);
}
