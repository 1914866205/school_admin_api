package com.soft1851.smart.campus.model.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @Author yhChen
 * @Description
 * @Date 2020/6/9
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class FleaGoodsVo {
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
     * 商品发布时间
     */
    private Date goodsCreateTime;
    /**
     * 商品类型id
     */
    private Long pkFleaTypeId;
    /**
     * 分类名称
     */
    private String typeName;
    /**
     * 商品发布人id
     */
    private Long pkFleaUserId;
    /**
     * 商品发布人昵称
     */
    private String nickname;
    /**
     * 商品发布人名字
     */
    private String username;
    /**
     * 判断标签
     */
    private Boolean isDeleted;
}
