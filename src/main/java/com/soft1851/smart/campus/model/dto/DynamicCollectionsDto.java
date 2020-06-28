package com.soft1851.smart.campus.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Yujie_Zhao
 * @ClassName CollectionsDto
 * @Description TODO
 * @Date 2020/6/15  14:08
 * @Version 1.0
 **/
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DynamicCollectionsDto {

    private String dynamicId;
    private String userId;

}
