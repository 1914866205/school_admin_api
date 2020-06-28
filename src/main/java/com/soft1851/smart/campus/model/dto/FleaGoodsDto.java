package com.soft1851.smart.campus.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author yhChen
 * @Description 修改商品信息Dto
 * @Date 2020/6/9
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class FleaGoodsDto {
    /**
     * 商品id
     */
    private Long pkFleaGoodsId;
    /**
     * 商品名
     */
    private String goodsName;
    /**
     * 商品描述
     */
    private String goodsDescription;
    /**
     * 商品图片
     */
    private String goodsImgUrl;
    /**
     * 商品价格
     */
    private Double goodsPrice;
    /**
     * 商品标签
     */
    private String goodsMark;
    /**
     * 商品类型id
     */
    private Long pkFleaTypeId;
    /**
     * 商品发布人id
     */
    private Long pkFleaUserId;
}
