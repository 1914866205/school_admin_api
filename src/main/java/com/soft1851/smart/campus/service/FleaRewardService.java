package com.soft1851.smart.campus.service;

import com.soft1851.smart.campus.constant.ResponseResult;
import com.soft1851.smart.campus.model.dto.*;
import com.soft1851.smart.campus.model.entity.FleaReward;
import org.springframework.data.domain.Page;

/**
 * @author 倪涛涛
 * @version 1.0.0
 * @ClassName FleaRewardService.java
 * @Description TODO
 * @createTime 2020年06月09日 14:05:00
 */
public interface FleaRewardService {
    /**
     * 根据content查询
     *
     * @param fleaSearchDto FleaSearchDto
     * @return Page<FleaReward>
     */
    Page<FleaReward> findFleaRewardByContent(FleaSearchDto fleaSearchDto);

    /**
     * 分页查询所有
     *
     * @param pageDto PageDto
     * @return Page<FleaReward>
     */
    ResponseResult findAll(PageDto pageDto);

    /**
     * 查出最新的两条悬赏数据
     *
     * @return ResponseResult
     */
    ResponseResult getRewardTopThree();

    /**
     * 添加悬赏
     *
     * @param fleaRewardDto FleaRewardDto
     * @return ResponseResult
     */
    ResponseResult save(FleaRewardDto fleaRewardDto);

    /**
     * 修改悬赏
     *
     * @param fleaRewardDto FleaRewardDto
     * @return ResponseResult
     */
    ResponseResult update(FleaRewardDto fleaRewardDto);

    /**
     * 删除悬赏
     *
     * @param rewardId Long
     * @return ResponseResult
     */
    ResponseResult delete(Long rewardId);

    /**
     * 删除单个
     *
     * @param fleaRewardIdDto FleaRewardIdDto
     * @return ResponseResult
     */
    ResponseResult deleteOneById(FleaRewardIdDto fleaRewardIdDto);

    /**
     * 批量删除
     * @param fleaRewardBatchIdDto FleaRewardBatchIdDto
     * @return ResponseResult
     */
    ResponseResult batchDeleteById(FleaRewardBatchIdDto fleaRewardBatchIdDto);
}
