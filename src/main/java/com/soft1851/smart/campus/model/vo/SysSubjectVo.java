package com.soft1851.smart.campus.model.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Tao
 * @version 1.0
 * @ClassName SysSubjectVo
 * @Description TODO
 * @date 2020-06-19 11:37
 **/
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SysSubjectVo {
    private Long pkSubjectId;
    private String name;

}
