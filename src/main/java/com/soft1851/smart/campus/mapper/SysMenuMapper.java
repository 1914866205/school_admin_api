package com.soft1851.smart.campus.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.soft1851.smart.campus.model.entity.SysMenu;

import java.util.List;
import java.util.Map;

/**
 * @Description TODO
 * @Author wf
 * @Date 2020/6/14
 * @Version 1.0
 */
public interface SysMenuMapper extends BaseMapper<SysMenu> {
    /**
     * 根据用户角色查询该用户的顶级权限
     * @return
     */
    List<Map<String, Object>> getParentMenu();

    /**
     * 根据角色id与父类id查询子类权限
     * @param parentId
     * @return
     */
    List<Map<String, Object>> getChildMenu(int parentId);
}
