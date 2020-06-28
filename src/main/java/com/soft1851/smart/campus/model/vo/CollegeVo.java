package com.soft1851.smart.campus.model.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Tao
 * @version 1.0
 * @ClassName CollegeVo
 * @Description TODO
 * @date 2020-06-17 10:43
 **/
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CollegeVo {
    private Long pkCollegeId;
    private String collegeName;
}
