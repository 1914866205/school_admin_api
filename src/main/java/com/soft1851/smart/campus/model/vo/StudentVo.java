package com.soft1851.smart.campus.model.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Tao
 * @version 1.0
 * @ClassName StudentVO
 * @Description TODO
 * @date 2020-06-17 11:15
 **/
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class StudentVo {
    private String pkUserAccountId;
    private String jobNumber;
    private String userName;
}
