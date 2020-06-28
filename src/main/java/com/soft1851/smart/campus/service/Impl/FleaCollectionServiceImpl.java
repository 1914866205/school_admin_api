package com.soft1851.smart.campus.service.Impl;

import com.soft1851.smart.campus.constant.ResponseResult;
import com.soft1851.smart.campus.model.dto.CancelCollectionDto;
import com.soft1851.smart.campus.model.dto.CollectionDto;
import com.soft1851.smart.campus.model.entity.FleaCollection;
import com.soft1851.smart.campus.repository.FleaCollectionRepository;
import com.soft1851.smart.campus.repository.FleaGoodsRepository;
import com.soft1851.smart.campus.repository.FleaUserRepository;
import com.soft1851.smart.campus.service.FleaCollectionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.sql.Timestamp;
import java.time.LocalDateTime;

/**
 * @Author yhChen
 * @Description
 * @Date 2020/6/12
 */
@Slf4j
@Service
public class FleaCollectionServiceImpl implements FleaCollectionService {
    @Resource
    private FleaCollectionRepository collectionRepository;
    @Resource
    private FleaGoodsRepository goodsRepository;
    @Resource
    private FleaUserRepository userRepository;
    @Override
    public ResponseResult addCollection(CollectionDto collectionDto) {
////        if (goodsRepository.findById(collectionDto.getGoodsId()).get() == null &&
////        userRepository.findById(collectionDto.getUserId()).get() == null){
////            return ResponseResult.failure(ResultCode.RESULT_CODE_DATA_NONE);
////        }
//        System.out.println(goodsRepository.findById(collectionDto.getGoodsId()).get() == null);
        FleaCollection fleaCollection = FleaCollection.builder()
                .fleaGoods(goodsRepository.findById(collectionDto.getGoodsId()).get())
                .fleaUser(userRepository.findById(collectionDto.getUserId()).get())
                .createTime(Timestamp.valueOf(LocalDateTime.now()))
                .isDeleted(false)
                .build();
        collectionRepository.save(fleaCollection);
        return ResponseResult.success("添加成功！");
    }

    @Override
    public ResponseResult getCollection() {
        return ResponseResult.success(collectionRepository.getCollection());
    }

    @Override
    public ResponseResult logicalDel(CancelCollectionDto collectionDto) {
        return ResponseResult.success(collectionRepository.logicalDel(collectionDto));
    }
}
