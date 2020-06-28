package com.soft1851.smart.campus.service;

import com.soft1851.smart.campus.constant.ResponseResult;
import com.soft1851.smart.campus.model.dto.FleaOrderBatchIdDto;
import com.soft1851.smart.campus.model.dto.FleaOrderDto;
import com.soft1851.smart.campus.model.dto.PageDto;

/**
 * @author 倪涛涛
 * @version 1.0.0
 * @ClassName FleaGoodsService.java
 * @Description TODO
 * @createTime 2020年06月09日 13:58:00
 */
public interface FleaOrderService {

    /**
     * 逻辑删除订单
     *
     * @param fleaOrderDto FleaOrderDto
     * @return ResponseResult
     */
    ResponseResult logicalDel(FleaOrderDto fleaOrderDto);

    /**
     * 批量逻辑删除
     *
     * @param fleaOrderBatchIdDto FleaOrderBatchIdDto
     * @return ResponseResult
     */
    ResponseResult batchLogicalDel(FleaOrderBatchIdDto fleaOrderBatchIdDto);

    /**
     * 分页查询所有
     *
     * @param pageDto PageDto
     * @return ResponseResult
     */
    ResponseResult findAll(PageDto pageDto);
}
