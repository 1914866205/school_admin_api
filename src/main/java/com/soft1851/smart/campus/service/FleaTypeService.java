package com.soft1851.smart.campus.service;

import com.soft1851.smart.campus.constant.ResponseResult;
import com.soft1851.smart.campus.model.dto.FleaTypeIncreasedDto;
import com.soft1851.smart.campus.model.dto.TypeDto;
import com.soft1851.smart.campus.model.entity.FleaType;

import java.util.Map;

/**
 * @author 倪涛涛
 * @version 1.0.0
 * @ClassName FleaGoodsService.java
 * @Description TODO
 * @createTime 2020年06月09日 13:58:00
 */
public interface FleaTypeService {
    /**
     * 查询所有的类型
     * @return
     */
    Map<String,Object>  findAllType();


    /** 根据分类id查询商品
     * @param typeDto  TypeDto
     * @return
     */
    ResponseResult getGoodsByType(TypeDto typeDto);

    ResponseResult typeDeletedById(Long pkId);

    ResponseResult typeModify(FleaType fleaType);

    ResponseResult typeIncreased(FleaTypeIncreasedDto fleaTypeIncreasedDto);
}
