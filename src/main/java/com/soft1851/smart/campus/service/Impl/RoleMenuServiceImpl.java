package com.soft1851.smart.campus.service.Impl;

import com.soft1851.smart.campus.repository.SysRoleMenuRepository;
import com.soft1851.smart.campus.service.RoleMenuService;
import com.soft1851.smart.campus.utils.DataTypeChange;
import com.soft1851.smart.campus.utils.TreeBuilder;
import com.soft1851.smart.campus.utils.TreeNode;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @Description TODO
 * @Author wf
 * @Date 2020/5/30
 * @Version 1.0
 */
@Service
public class RoleMenuServiceImpl implements RoleMenuService {
    @Resource
    private SysRoleMenuRepository sysRoleMenuRepository;

    @Override
    public List<Map<String, Object>> getRoleMenuByRoleId(long roleId) {
        List<Map<String, Object>> permissions = new ArrayList<>();
        Object[] objects = sysRoleMenuRepository.getRoleMenuByPkRoleId(roleId);
        permissions = DataTypeChange.objectToMap(objects);
        List<TreeNode> list = new ArrayList<>();
        for (Map<String, Object> menu : permissions) {
            if(menu.get("parentId") == "0"){
                TreeNode treeNode = new TreeNode(
                        Long.parseLong(menu.get("pkMenuId").toString()), (long)0,
                        Integer.parseInt(menu.get("type").toString()),
                        menu.get("name").toString(),
                        menu.get("icon").toString(),
                        menu.get("path").toString(),
                        Integer.parseInt(menu.get("sort").toString())
                );
                list.add(treeNode);
            }else {
                TreeNode treeNode = new TreeNode(
                        Long.parseLong(menu.get("pkMenuId").toString()),
                        Long.parseLong(menu.get("parentId").toString()),
                         Integer.parseInt(menu.get("type").toString()),
                        menu.get("name").toString(),
                        menu.get("icon").toString(),
                        menu.get("path").toString(),
                        Integer.parseInt(menu.get("sort").toString())
                );
                list.add(treeNode);
            }
        }
        List<TreeNode> trees = TreeBuilder.buildTreeByLoop(list);
        return permissions;
    }
}
