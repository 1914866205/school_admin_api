package com.soft1851.smart.campus.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author 倪涛涛
 * @version 1.0.0
 * @ClassName FleaTypeIncreased.java
 * @Description TODO
 * @createTime 2020年06月15日 09:25:00
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class FleaTypeIncreasedDto {
    private Long parentId;
    private String typeCoverUrl;
    private String typeName;
    private String typeUrl;
}
