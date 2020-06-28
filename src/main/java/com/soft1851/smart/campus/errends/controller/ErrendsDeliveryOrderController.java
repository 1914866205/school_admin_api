package com.soft1851.smart.campus.errends.controller;

import com.soft1851.smart.campus.constant.ResponseResult;
import com.soft1851.smart.campus.errends.domain.dto.DeliveryOrderDto;
import com.soft1851.smart.campus.errends.domain.dto.FinshOrderDto;
import com.soft1851.smart.campus.errends.service.DeliveryOrderService;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author wl
 * @ClassNameErrendsDeliveryOrderController
 * @Description TODO
 * @Date 2020/6/17
 * @Version 1.0
 */
@RestController
@RequestMapping(value="errends")
@Api(value = "deliveryOrder", tags = "订单管理")
public class ErrendsDeliveryOrderController {
    @Resource
    private DeliveryOrderService deliveryOrderService;


    @PostMapping(value = "/serach/differentOrder")
    public ResponseResult getDeliveryOrder(@RequestBody FinshOrderDto finshOrderDto) {
        return deliveryOrderService.getOrder(finshOrderDto);
    }

    @PostMapping("/search/getOrder")
    public ResponseResult getOrder(@RequestBody DeliveryOrderDto orderDto){
        return deliveryOrderService.getOrderByFoundIdOrFounderName(orderDto);
    }
}
