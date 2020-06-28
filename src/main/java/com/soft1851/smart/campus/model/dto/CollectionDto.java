package com.soft1851.smart.campus.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 描述:
 * 添加收藏传参类
 * @author：Guorc
 * @create 2020-06-10 8:13
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CollectionDto {
    private Long goodsId;
    private Long userId;

}
