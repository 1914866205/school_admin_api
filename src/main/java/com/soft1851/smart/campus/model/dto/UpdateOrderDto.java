package com.soft1851.smart.campus.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Tao
 * @version 1.0
 * @ClassName UpdateOrderDto
 * @Description TODO
 * @date 2020-06-15 15:51
 **/
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UpdateOrderDto {

    /**
     * 订单id
     */
    private  Long pkOrderId;

    /**
     * 金额
     */
    private  Double orderMoney;


    /**
     * 缴费描述
     */
    private  String description;



}
