package com.soft1851.smart.campus.service.Impl;

import com.soft1851.smart.campus.constant.ResponseResult;
import com.soft1851.smart.campus.constant.ResultCode;
import com.soft1851.smart.campus.model.dto.*;
import com.soft1851.smart.campus.model.entity.FleaReward;
import com.soft1851.smart.campus.model.vo.RewardVo;
import com.soft1851.smart.campus.repository.FleaRewardRepository;
import com.soft1851.smart.campus.repository.FleaUserRepository;
import com.soft1851.smart.campus.service.FleaRewardService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

/**
 * @Author yhChen
 * @Description
 * @Date 2020/6/12
 */
@Slf4j
@Service
public class FleaRewardServiceImpl implements FleaRewardService {
    @Resource
    private FleaRewardRepository fleaRewardRepository;
    @Resource
    private FleaUserRepository fleaUserRepository;

    @Override
    public Page<FleaReward> findFleaRewardByContent(FleaSearchDto fleaSearchDto) {
        //创建分页构建器   按照时间降序排序
        Pageable pageable = PageRequest.of(fleaSearchDto.getCurrentPage(), fleaSearchDto.getPageSize(), Sort.Direction.DESC, "goodsCreateTime");
        //根据内容模糊搜索
        List<FleaReward> result = fleaRewardRepository.findFleaRewardsByDescriptionLikeOrTitleLike("%" + fleaSearchDto.getContent() + "%", "%" + fleaSearchDto.getContent() + "%");
        Page<FleaReward> fleaGoodsInfo = new PageImpl<FleaReward>(result, pageable, result.size());
        return fleaGoodsInfo;
    }

    @Override
    public ResponseResult findAll(PageDto pageDto) {
        //创建分页构建器   按照时间降序排序
        Pageable pageable = PageRequest.of(pageDto.getCurrentPage(), pageDto.getPageSize());
        return ResponseResult.success(fleaRewardRepository.findAllFleaReward(pageable));
    }

    @Override
    public ResponseResult getRewardTopThree() {
        Pageable pageable = PageRequest.of(0, 3, Sort.Direction.DESC, "createTime");
        List<RewardVo> rewardVoList = fleaRewardRepository.getTopReward(pageable);
        if (rewardVoList.size() == 0) {
            return ResponseResult.failure(ResultCode.RESULT_CODE_DATA_NONE);
        }
        return ResponseResult.success(rewardVoList);
    }

    @Override
    public ResponseResult save(FleaRewardDto fleaRewardDto) {
        FleaReward fleaReward = FleaReward.builder()
                .description(fleaRewardDto.getDescription())
                .imageUrl(fleaRewardDto.getImageUrl())
                .title(fleaRewardDto.getTitle())
                .fleaUser(fleaUserRepository.getOne(fleaRewardDto.getFleaUserId()))
                .createTime(Timestamp.valueOf(LocalDateTime.now()))
                .isDeleted(false).build();
        return ResponseResult.success(fleaRewardRepository.save(fleaReward));
    }

    @Override
    public ResponseResult update(FleaRewardDto fleaRewardDto) {
        FleaReward fleaReward = fleaRewardRepository.findFleaRewardByPkRewardIdEquals(fleaRewardDto.getPkRewardId());
        fleaReward.setDescription(fleaRewardDto.getDescription());
        fleaReward.setImageUrl(fleaRewardDto.getImageUrl());
        fleaReward.setTitle(fleaRewardDto.getTitle());
        return ResponseResult.success(fleaRewardRepository.save(fleaReward));
    }

    @Override
    public ResponseResult delete(Long rewardId) {
        //查到了，就删除，数据消失
        if (fleaRewardRepository.findById(rewardId).isPresent()) {
            fleaRewardRepository.deleteById(rewardId);
        }
        return ResponseResult.success();
    }

    @Override
    public ResponseResult deleteOneById(FleaRewardIdDto fleaRewardIdDto) {
        fleaRewardRepository.deleteRewardByPkRewardId(fleaRewardIdDto.getFleaRewardId());
        return ResponseResult.success("删除成功");
    }

    @Override
    public ResponseResult batchDeleteById(FleaRewardBatchIdDto fleaRewardBatchIdDto) {
        fleaRewardRepository.batchDelete(fleaRewardBatchIdDto.getId());
        return ResponseResult.success("删除成功");
    }
}
