package com.soft1851.smart.campus.errends.service;

import com.soft1851.smart.campus.constant.ResponseResult;
import com.soft1851.smart.campus.errends.domain.dto.DeliveryOrderDto;
import com.soft1851.smart.campus.errends.domain.dto.FinshOrderDto;

/**
 * @author wl
 * @ClassNameDeliveryOderService
 * @Description TODO
 * @Date 2020/6/17
 * @Version 1.0
 */
public interface DeliveryOrderService {
    /**
     * 获取各种订单
     * @param finshOrderDto
     * @return
     */
    ResponseResult getOrder(FinshOrderDto finshOrderDto);

    /**
     * 根据订单ID，姓名模糊查询
     * @param deliveryOrderDto
     * @return
     */
    ResponseResult getOrderByFoundIdOrFounderName(DeliveryOrderDto deliveryOrderDto);
}
