package com.soft1851.smart.campus.controller;

import com.soft1851.smart.campus.constant.ResponseResult;
import com.soft1851.smart.campus.model.dto.*;
import com.soft1851.smart.campus.model.entity.SysRole;
import com.soft1851.smart.campus.service.RoleMenuService;
import com.soft1851.smart.campus.service.RoleService;
import com.soft1851.smart.campus.service.SysRoleService;
import com.soft1851.smart.campus.service.UserRoleService;
import com.soft1851.smart.campus.utils.TreeNode;
import io.swagger.annotations.Api;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author Tao
 * @version 1.0
 * @ClassName SysRoleController
 * @Description TODO
 * @date 2020-05-31 15:13
 **/
@RestController
@RequestMapping("/role")
@Api(value = "SysRoleController", tags = "角色管理接口")
public class SysRoleController {
    @Resource
    private SysRoleService sysRoleService;
    @Resource
    private RoleService roleService;
    @Resource
    private UserRoleService userRoleService;
    @Resource
    private RoleMenuService roleMenuService;

    /**
     * 分页查询所有
     * @return
     */
    @PostMapping(value = "/all")
    public ResponseResult findAllSysRoleByPage(){
        return sysRoleService.findAllSysRoleByPage();
    }

    /**
     * 单个删除角色数据
     * @param queryDto
     * @return
     */
    @PostMapping(value = "/deletion/id")
    public ResponseResult deletedSysRole(@RequestBody QueryDto queryDto){
        return sysRoleService.deletedSysRole(Long.parseLong(queryDto.getField().toString()));
    }

    /**
     * 批量删除角色数据
     * @param batchDeletionDto
     * @return
     */
    @PostMapping(value = "/deletionBath/ids")
    public ResponseResult deletedBatch(@RequestBody BatchDeletionDto batchDeletionDto){
        return sysRoleService.deletedBatch(batchDeletionDto.getIds());
    }

//    /**
//     * 修改角色信息
//     * @param sysRole
//     * @return
//     */
//    @PutMapping(value = "/modification")
//    public ResponseResult updateSysRole(@RequestBody SysRole sysRole){
//        return sysRoleService.updateSysRole(sysRole);
//    }

    /**
     * 修改角色信息(NEW)
     * @param updateSysRoleDto
     * @return
     */
    @PostMapping(value = "/modification")
    public ResponseResult updateSysRole(@RequestBody UpdateSysRoleDto updateSysRoleDto){
        return sysRoleService.updateSysRole(updateSysRoleDto);
    }



    /**
     * 新增角色信息
     * @param sysRole
     * @return
     */
    @PostMapping(value = "/increase")
    public ResponseResult increaseSysRole(@RequestBody SysRole sysRole){
        return sysRoleService.increaseSysRole(sysRole);
    }

    /**
     * 修改排序id
     * @param sortList
     * @return
     */
    @PutMapping("/modification/sort")
    public ResponseResult modificationSort(@Param("sortList") String sortList){
        return sysRoleService.updateSysRoleSort(sortList);
    }


    /**
     * 分页查询所有系统角色（去除逻辑删除）
     * @param pageDto
     * @return
     */
    @PostMapping(value = "/all/new")
    public ResponseResult getAllSysRole(@RequestBody PageDto pageDto){
        return sysRoleService.getAllSysRole(pageDto);
    }

    /**
     * 获取所有角色数据
     * @return
     */
    @PostMapping(value = "/all/noPage")
    public ResponseResult getAllSysRole(){
        return sysRoleService.findAllSysRole();
    }

    /**
     * 获取角色的权限
     * @return
     */
    @PostMapping(value = "/menus")
    public List<TreeNode> getMenusByRoleId(@RequestBody QueryDto queryDto) {
        return roleService.getRoleMenuByRoleId(Long.parseLong(queryDto.getField().toString()));
    }

    @PostMapping(value = "/assign/menus")
    public ResponseResult assignMenus(@RequestBody DoubleFieldDto doubleFieldDto) {
        System.out.println(doubleFieldDto);
        return userRoleService.insertUserRole(doubleFieldDto);
    }

    @PostMapping(value = "/delection/batch")
    public ResponseResult batchDelete(@RequestBody DoubleFieldDto doubleFieldDto) {
        sysRoleService.deleteRoleMenu(doubleFieldDto.getFirstField(), Long.parseLong(doubleFieldDto.getSecondField()));
        return ResponseResult.success();
    }
}
