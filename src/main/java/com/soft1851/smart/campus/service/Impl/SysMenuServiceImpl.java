package com.soft1851.smart.campus.service.Impl;

import com.soft1851.smart.campus.mapper.SysMenuMapper;
import com.soft1851.smart.campus.model.entity.SysMenu;
import com.soft1851.smart.campus.repository.SysMenuRepository;
import com.soft1851.smart.campus.service.SysMenuService;
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
 * @Date 2020/6/14
 * @Version 1.0
 */
@Service
public class SysMenuServiceImpl implements SysMenuService {
    @Resource
    private SysMenuMapper sysMenuMapper;

    @Override
    public List<Map<String, Object>> selectAllMenus() {
        return sysMenuMapper.getParentMenu();
    }
}
