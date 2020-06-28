package com.soft1851.smart.campus.model.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * 描述:
 *
 * @author：Guorc
 * @create 2020-06-10 10:04
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CollectionVo {
    private String goodsName;
    private String goodsDescription;
    private Double goodsPrice;
    private String goodsImgUrl;
    private String goodsMark;
    private String username;
    private String phoneNumber;
    private Date createTime;
}
