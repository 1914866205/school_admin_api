package com.soft1851.smart.campus.errends.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.soft1851.smart.campus.errends.domain.dto.DeliveryOrderDto;
import com.soft1851.smart.campus.errends.domain.entity.DeliveryOrder;
import com.soft1851.smart.campus.errends.domain.vo.DeliveryOderInformationVo;

import java.util.List;

/**
 * @author wl
 * @ClassNamedeliveryOrderMapper
 * @Description 跑腿运输订单
 * @Date 2020/6/9
 * @Version 1.0
 */
public interface DeliveryOrderMapper extends BaseMapper<DeliveryOrder> {
    List<DeliveryOderInformationVo> getByOrderIdOrFounderName(DeliveryOrderDto deliverDto);
}
