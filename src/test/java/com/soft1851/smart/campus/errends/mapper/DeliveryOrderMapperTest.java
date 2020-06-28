package com.soft1851.smart.campus.errends.mapper;

import com.soft1851.smart.campus.errends.domain.dto.DeliveryOrderDto;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

@SpringBootTest
class DeliveryOrderMapperTest {
    @Resource
    private DeliveryOrderMapper deliveryOrderMapper;

    @Test
    void getOrder(){
        DeliveryOrderDto deliveryOrderDto = DeliveryOrderDto.builder()
                .founderId("王")
                .currentPage(0)
                .size(2)
                .build();
        System.out.println(deliveryOrderDto);
        System.out.println(deliveryOrderMapper.getByOrderIdOrFounderName(deliveryOrderDto));
    }
}