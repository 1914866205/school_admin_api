package com.soft1851.smart.campus.service;

import com.soft1851.smart.campus.constant.ResponseResult;
import com.soft1851.smart.campus.model.dto.DynamicCollectionsDto;
import com.soft1851.smart.campus.model.dto.PageDto;
import com.soft1851.smart.campus.model.entity.Collections;

/**
 * @author Yujie_Zhao
 * @ClassName CollectionsService
 * @Description 动态的收藏
 * @Date 2020/6/15  14:06
 * @Version 1.0
 **/
public interface DynamicCollectionsService {
    /**
     * 单个添加
     * @param dynamicCollectionsDto
     * @return
     */
    ResponseResult insertCollections(DynamicCollectionsDto dynamicCollectionsDto);

    /**
     * 查询所有咨讯
     * @return
     */
    ResponseResult findAllCollections(PageDto pageDto);


    /**
     * 单个删除咨询
     * @param id
     * @return
     */
    ResponseResult deleteCollections(String id);

    /**
     * 批量删除咨询
     * @param ids
     * @return
     */
    ResponseResult deletedBatch(String ids);

    /**
     * 修改咨询
     * @param collections
     * @return
     */
    ResponseResult updateCollections(Collections collections);
}
