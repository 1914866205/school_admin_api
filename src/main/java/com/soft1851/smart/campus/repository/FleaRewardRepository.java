
package com.soft1851.smart.campus.repository;

import com.soft1851.smart.campus.model.dto.FleaRewardBatchIdDto;
import com.soft1851.smart.campus.model.entity.FleaReward;
import com.soft1851.smart.campus.model.vo.FleaRewardVo;
import com.soft1851.smart.campus.model.vo.RewardVo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.List;

/**
 * @author 倪涛涛
 * @version 1.0.0
 * @ClassName a.java
 * @Description TODO
 * @createTime 2020年06月09日 11:26:00
 */
public interface FleaRewardRepository extends JpaRepository<FleaReward, Long> {
    /**
     * 根据描述和标题模糊查询
     *
     * @param description String
     * @param title       String
     * @return List<FleaReward>
     */
    List<FleaReward> findFleaRewardsByDescriptionLikeOrTitleLike(String description, String title);

    /**
     * 根据创建时间查出前两条数据
     *
     * @param pageable Pageable
     * @return List<RewardVo>
     */
    @Query(value = "select new com.soft1851.smart.campus.model.vo.RewardVo(f.title,f.description,f.imageUrl,f.createTime,u.username,u.phoneNumber) " +
            "from FleaReward f " +
            "left join f.fleaUser u")
    List<RewardVo> getTopReward(Pageable pageable);

    /**
     * 根据id查询
     *
     * @param pkRewardId Long
     * @return FleaReward
     */
    FleaReward findFleaRewardByPkRewardIdEquals(Long pkRewardId);

    /**
     * 查询所有悬赏
     *
     * @param pageable Pageable
     * @return List<FleaRewardVo>
     */
    @Query(value = "select new com.soft1851.smart.campus.model.vo.FleaRewardVo(fr.pkRewardId,fr.title,fr.description,fr.imageUrl," +
            "fr.createTime,fr.isDeleted,fu.nickname,fu.username,fu.sex) " +
            "from FleaReward fr " +
            "left join fr.fleaUser fu ")
    Page<FleaRewardVo> findAllFleaReward(Pageable pageable);

    /**
     * 根据id修改isDeleted字段来删除
     *
     * @param pkRewardId Long
     * @return int
     */
    @Modifying
    @Transactional(rollbackFor = RuntimeException.class)
    @Query(value = "update FleaReward set isDeleted = 1 where pkRewardId = ?1 ")
    int deleteRewardByPkRewardId(Long pkRewardId);

    /**
     * 批量删除
     *
     * @param batchId List<Long>
     * @return int
     */
    @Modifying
    @Transactional(rollbackFor = RuntimeException.class)
    @Query(value = "update FleaReward set isDeleted = 1 where pkRewardId in (:batchId) ")
    int batchDelete(List<Long> batchId);
}
