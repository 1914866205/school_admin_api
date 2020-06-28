package com.soft1851.smart.campus.service;

import com.soft1851.smart.campus.constant.ResponseResult;
import com.soft1851.smart.campus.model.dto.CancelCollectionDto;
import com.soft1851.smart.campus.model.dto.CollectionDto;

/**
 * @author 倪涛涛
 * @version 1.0.0
 * @ClassName FleaGoodsService.java
 * @Description TODO
 * @createTime 2020年06月09日 13:58:00
 */
public interface FleaCollectionService {
    /**
     * 插入收藏
     *
     * @param collectionDto
     * @return
     */
    ResponseResult addCollection(CollectionDto collectionDto);

    /**
     * 获取所有收藏
     *
     * @return
     */
    ResponseResult getCollection();

    /**
     * 根据商品ID以及用户ID逻辑删除商品
     *
     * @param collectionDto
     * @return
     */
    ResponseResult logicalDel(CancelCollectionDto collectionDto);
}

