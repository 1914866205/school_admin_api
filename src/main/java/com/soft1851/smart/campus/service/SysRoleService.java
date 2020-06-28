package com.soft1851.smart.campus.service;

import com.soft1851.smart.campus.constant.ResponseResult;
import com.soft1851.smart.campus.model.dto.PageDto;
import com.soft1851.smart.campus.model.dto.UpdateSysRoleDto;
import com.soft1851.smart.campus.model.entity.SysRole;

/**
 * @author Tao
 */
public interface SysRoleService {


    /**
     * 分页查询所有角色
     * @return
     */
    ResponseResult findAllSysRoleByPage();

    /**
     * 单个删除角色数据
     * @param id
     * @return
     */
    ResponseResult deletedSysRole(Long id);

    /**
     * 批量删除角色数据
     * @param ids
     * @return
     */
    ResponseResult deletedBatch(String ids);



    /**
     * 新增角色数据
     * @param sysRole
     * @return
     */
    ResponseResult increaseSysRole(SysRole sysRole);

    /**
     * 拖拽排序后，修改排序id
     * @param sortList
     * @return
     */
    ResponseResult updateSysRoleSort(String sortList);

    /**
     * 分页查询所有系统角色去除被逻辑删除的
     * @param pageDto
     * @return
     */
    ResponseResult getAllSysRole(PageDto pageDto);

    /**
     * 修改系统角色信息
     * @param updateSysRoleDto
     * @return
     */
    ResponseResult updateSysRole(UpdateSysRoleDto updateSysRoleDto);

    /**
     * 查询所有系统角色
     * @return
     */
    ResponseResult findAllSysRole();

    /**
     * 删除角色权限
     * @param id
     * @return
     */
    int deleteRoleMenu(String id, long roleId);
}
