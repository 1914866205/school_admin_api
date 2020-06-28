package com.soft1851.smart.campus.controller;

import com.soft1851.smart.campus.service.SysMenuService;
import com.soft1851.smart.campus.utils.TreeNode;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * @Description TODO
 * @Author wf
 * @Date 2020/6/14
 * @Version 1.0
 */
@RestController
@RequestMapping(value = "menu")
public class SysMenuController {
    @Resource
    private SysMenuService sysMenuService;

    @PostMapping("/list")
    public List<Map<String, Object>> selectAllMenus() {
        return sysMenuService.selectAllMenus();
    }
}
