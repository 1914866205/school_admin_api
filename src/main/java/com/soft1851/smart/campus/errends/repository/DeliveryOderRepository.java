package com.soft1851.smart.campus.errends.repository;

import com.soft1851.smart.campus.errends.domain.dto.DeliveryOrderDto;
import com.soft1851.smart.campus.errends.domain.entity.DeliveryOrder;
import com.soft1851.smart.campus.errends.domain.vo.DeliveryOderInformationVo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * @author wl
 * @ClassNameDeliveryOderRepository
 * @Description TODO
 * @Date 2020/6/9
 * @Version 1.0
 */
public interface DeliveryOderRepository extends JpaRepository<DeliveryOrder,String> {


//    @Query("select new com.soft1851.smart.campus.errends.domain.vo.DeliveryOderInformationVo(d.commodityId,d.founderId,d.founderName,d.founderPhonenumber,d.originAddress,d.destination,c.type,c.priceRang,d.oderCreateTime,d.deliveryTime,d.founderName)" +
//            "from DeliveryOrder d " +
//            "left join d.commodity c")
//    List<DeliveryOderInformationVo> getOrderByOrderIdOrFoundName(@Param("deliveryOrderDto") DeliveryOrderDto deliveryOrderDto);
}
