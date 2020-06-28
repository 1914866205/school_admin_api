package com.soft1851.smart.campus.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author yhChen
 * @Description 下架商品Dto
 * @Date 2020/6/10
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SoldOutGoodDto {

    /**
     * 商品id
     */
    private Long pkFleaGoodsId;
}
