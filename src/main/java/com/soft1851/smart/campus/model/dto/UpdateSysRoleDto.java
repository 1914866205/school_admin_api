package com.soft1851.smart.campus.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Tao
 * @version 1.0
 * @ClassName UpdateSysRoleDto
 * @Description TODO
 * @date 2020-06-11 11:27
 **/
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UpdateSysRoleDto {
    private Long pkRoleId;
    private String roleName;
    private String roleDescription;
}
