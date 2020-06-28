
package com.soft1851.smart.campus.model.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

/**
 * @author Tao
 * @version 1.0
 * @ClassName UserAccountVo
 * @Description TODO
 * @date 2020-06-14 13:35
 **/
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserAccountVo {
    private String pkUserAccountId;
    private String gender;
    private Timestamp gmtCreate;
    private Boolean isDeleted;
    private String jobNumber;
    private String phoneNumber;
    private Boolean status;
    private String userName;
    private String name;
}

