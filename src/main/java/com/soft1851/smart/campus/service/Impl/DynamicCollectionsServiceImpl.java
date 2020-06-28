package com.soft1851.smart.campus.service.Impl;

import com.soft1851.smart.campus.constant.ResponseResult;
import com.soft1851.smart.campus.constant.ResultCode;
import com.soft1851.smart.campus.model.dto.DynamicCollectionsDto;
import com.soft1851.smart.campus.model.dto.PageDto;
import com.soft1851.smart.campus.model.entity.Collections;
import com.soft1851.smart.campus.repository.CollectionsRepository;
import com.soft1851.smart.campus.service.DynamicCollectionsService;
import com.soft1851.smart.campus.utils.SnowFlake;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Yujie_Zhao
 * @ClassName CollectionsServiceImpl
 * @Description 动态的收藏
 * @Date 2020/6/15  14:06
 * @Version 1.0
 **/
@Service
public class DynamicCollectionsServiceImpl implements DynamicCollectionsService {
    @Resource
    private CollectionsRepository collectionsRepository;

    @Override
    public ResponseResult insertCollections(DynamicCollectionsDto dynamicCollectionsDto) {
        Collections collections = Collections.builder()
                .pkCollectionId(String.valueOf(new SnowFlake(1, 3).nextId()))
                .isDeleted(true)
                .userId(dynamicCollectionsDto.getUserId())
                .dynamicId(dynamicCollectionsDto.getDynamicId())
                .build();
        return ResponseResult.success(collectionsRepository.save(collections));
    }

    @Override
    public ResponseResult findAllCollections(PageDto pageDto) {
        Pageable pageable = PageRequest.of(
                pageDto.getCurrentPage(),
                pageDto.getPageSize(),
                Sort.Direction.DESC,
                "gmtCreate");
        Page<Collections> collectionsPage = collectionsRepository.findAll(pageable);
        return ResponseResult.success(collectionsPage.getContent());
    }

    /**
     * 删除单个
     * @param id
     * @return
     */
    @Override
    public ResponseResult deleteCollections(String id) {
        //根据id查询角色数据是否存在 ，若存在进行删除，不存则返回 数据有误
        Collections collections = collectionsRepository.findCollectionsByPkCollectionIdAndIsDeleted(id,false);
        if (collections != null) {
            collections.setIsDeleted(true);
            collectionsRepository.saveAndFlush(collections);
            return ResponseResult.success("删除成功");
        } else {
            return ResponseResult.failure(ResultCode.RESULT_CODE_DATA_NONE);
        }
    }

    /**
     * 批量删除
     * @param ids
     * @return
     */
    @Override
    public ResponseResult deletedBatch(String ids) {
        //判断是否有数据
        if (ids.length() != 0) {
            //将接收到的ids字符串，使用逗号分割
            String[] idArr = ids.split(",");
            List<String> idsList = new ArrayList<String>();
            //遍历所有id存入到list
            java.util.Collections.addAll(idsList, idArr);
            int a = collectionsRepository.updateIsDelete(idsList);
            if(a>0){
                return ResponseResult.success("删除成功");
            }else {
                return ResponseResult.success(ResultCode.DATABASE_ERROR);
            }
        } else {
            return ResponseResult.failure(ResultCode.PARAM_IS_BLANK);
        }
    }

    /**
     *
     * @param collections
     * @return
     */
    @Override
    public ResponseResult updateCollections(Collections collections) {
        return null;
    }
}
