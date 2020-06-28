package com.soft1851.smart.campus.model.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @Author yhChen
 * @Description
 * @Date 2020/6/11
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class FleaOrderVo {
    private String pkFleaOrderId;
    private String goodsName;
    private Double goodsPrice;
    private String goodsSeller;
    private String goodsBuyer;
    private Date orderCreateTime;
    private String goodsDescription;
    private String goodsMark;
    private Boolean isDeleted;
}
