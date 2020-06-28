package com.soft1851.smart.campus.service;

import com.soft1851.smart.campus.constant.ResponseResult;
import com.soft1851.smart.campus.model.dto.PageDto;
import com.soft1851.smart.campus.model.entity.InfoType;

/**
 * @author Yujie_Zhao
 * @ClassName InfoTypeService
 * @Description 分类service
 * @Date 2020/6/2  10:13
 * @Version 1.0
 **/
public interface InfoTypeService {

    /**
     * 分页查询所有资讯分类数据
     * @param pageDto
     * @return
     */
    ResponseResult getAllInfoType(PageDto pageDto);


    /**
     * 单个添加
     * @param infoType
     * @return
     */
    ResponseResult insertInfoType(InfoType infoType);


    /**
     * 单个删除分类
     * @param id
     * @return
     */
    ResponseResult deleteInfoType(Long id);

    /**
     * 批量删除咨询
     * @param ids
     * @return
     */
    ResponseResult deletedBatch(String ids);


    /**
     * 修改分类
     * @param infoType
     * @return
     */
    ResponseResult updateInfoType(InfoType infoType);

}
