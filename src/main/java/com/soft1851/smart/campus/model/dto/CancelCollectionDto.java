package com.soft1851.smart.campus.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 描述:
 *
 * @author：Guorc
 * @create 2020-06-10 11:27
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CancelCollectionDto {
    /**
     * 用户ID
     */
    private Long userId;
    /**
     * 商品ID
     */
    private Long goodsId;
}
