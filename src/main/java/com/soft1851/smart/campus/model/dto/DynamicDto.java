package com.soft1851.smart.campus.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Yujie_Zhao
 * @ClassName DynamicDto
 * @Description TODO
 * @Date 2020/6/12  15:30
 * @Version 1.0
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DynamicDto {

    private String userId;

    private String content;

    private String type;
}
