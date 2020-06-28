package com.soft1851.smart.campus.model.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Tao
 * @version 1.0
 * @ClassName ClazzVo
 * @Description TODO
 * @date 2020-06-19 14:22
 **/
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ClazzVo {
    private Long pkClazzId;
    private String name;
}
