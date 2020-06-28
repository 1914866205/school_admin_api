package com.soft1851.smart.campus.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @Author yhChen
 * @Description
 * @Date 2020/6/12
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class FleaRewardBatchIdDto {
    private List<Long> id;
}
