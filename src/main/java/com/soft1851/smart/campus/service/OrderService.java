package com.soft1851.smart.campus.service;


import com.soft1851.smart.campus.constant.ResponseResult;
import com.soft1851.smart.campus.model.dto.PageDto;
import com.soft1851.smart.campus.model.dto.UpdateOrderDto;

/**
 * @ClassName OrderService
 * @Description TODO
 * @Author 田震
 * @Date 2020/5/29
 **/
public interface OrderService {
    /**
     * 查询清单明细
     * @param jobNumber
     * @return
     */
    ResponseResult findALLByJobNumer(String jobNumber);


    /**
     * 分页查询未被逻辑查询删除的订单信息数据
     * @param pageDto
     * @return
     */
    ResponseResult getAllSysOrder(PageDto pageDto);

    /**
     * 删除订单信息
     * @return
     */
    ResponseResult deleteOrder(Long pkOrderId);

    /**
     * 批量删除订单
     * @param ids
     * @return
     */
    ResponseResult deletedBatch(String ids);

    /**
     * 修改金额和订单描述
     * @param updateOrderDto
     * @return
     */
    ResponseResult updateOrder(UpdateOrderDto updateOrderDto);
}
