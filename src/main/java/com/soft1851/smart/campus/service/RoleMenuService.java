package com.soft1851.smart.campus.service;

import java.util.List;
import java.util.Map;

/**
 * @Description TODO
 * @Author wf
 * @Date 2020/5/30
 * @Version 1.0
 */
public interface RoleMenuService {

    /**
     * 根据角色id获取角色权限
     * @param roleId
     * @return
     */
    List<Map<String, Object>> getRoleMenuByRoleId(long roleId);
}
