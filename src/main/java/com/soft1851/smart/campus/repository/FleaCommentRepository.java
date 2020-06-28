
package com.soft1851.smart.campus.repository;

import com.soft1851.smart.campus.model.dto.PageDto;
import com.soft1851.smart.campus.model.entity.FleaComment;
import com.soft1851.smart.campus.model.vo.FleaCommentVo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author 倪涛涛
 * @version 1.0.0
 * @ClassName a.java
 * @Description TODO
 * @createTime 2020年06月09日 11:26:00
 */
public interface FleaCommentRepository extends JpaRepository<FleaComment,Long> {

    /**
     * 查询所有
     * @return List<FleaComment>
     */
    @Query(value = "select new com.soft1851.smart.campus.model.vo.FleaCommentVo(c.pkFleaCommentId,r.nickname,b.nickname,c.comment,f.title,c.createTime) " +
            "from FleaComment c " +
            "left join c.reviewer r " +
            "left join c.commentBy b " +
            "left join c.fleaReward f ")
    Page<FleaCommentVo> selectAll(Pageable pageable);

    @Modifying
    @Transactional(rollbackFor = RuntimeException.class)
    @Query(value = "delete from FleaComment c where c.pkFleaCommentId in (:batchId)")
    int batchlDel(List<Long> batchId);
}