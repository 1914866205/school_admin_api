package com.soft1851.smart.campus.service;

import com.soft1851.smart.campus.utils.TreeNode;

import java.util.List;

/**
 * @Description TODO
 * @Author wf
 * @Date 2020/5/30
 * @Version 1.0
 */
public interface RoleService {

    /**
     * 获取角色权限
     * @param roleId
     * @return
     */
    List<TreeNode> getRoleMenuByRoleId(long roleId);
}
