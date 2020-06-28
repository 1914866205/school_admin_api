package com.soft1851.smart.campus.service;

import com.soft1851.smart.campus.utils.TreeNode;

import java.util.List;
import java.util.Map;

/**
 * @Description TODO
 * @Author wf
 * @Date 2020/6/14
 * @Version 1.0
 */
public interface SysMenuService {

    /**
     * 获取所有权限
     * @return
     */
    List<Map<String, Object>> selectAllMenus();
}
