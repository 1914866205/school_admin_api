package com.soft1851.smart.campus.service;

import com.soft1851.smart.campus.constant.ResponseResult;
import com.soft1851.smart.campus.model.dto.DynamicDto;
import com.soft1851.smart.campus.model.dto.PageDto;
import com.soft1851.smart.campus.model.entity.Dynamic;

/**
 * @author Yujie_Zhao
 * @ClassName DynamicService
 * @Description 动态资讯
 * @Date 2020/6/12  15:24
 * @Version 1.0
 **/
public interface DynamicService {

    /**
     * 全部
     * @return
     */
    ResponseResult findAll(Boolean isDelete);

    /**
     * 全部
     * @return
     */
    ResponseResult findDynamicInfo(Boolean isDelete);

    /**
     * 单个添加
     * @param dynamicDto
     * @return
     */
    ResponseResult insertDynamic(DynamicDto dynamicDto);

    /**
     * 查询所有咨讯
     * @return
     */
    ResponseResult findAllDynamic(PageDto pageDto);


    /**
     * 单个删除咨询
     * @param id
     * @return
     */
    ResponseResult deleteDynamic(String id);

    /**
     * 批量删除咨询
     * @param ids
     * @return
     */
    ResponseResult deletedBatch(String ids);

    /**
     * 修改咨询
     * @param dynamic
     * @return
     */
    ResponseResult updateDynamic(Dynamic dynamic);

}
