package com.soft1851.smart.campus.repository;

import com.soft1851.smart.campus.model.entity.SysRoleMenu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @Description TODO
 * @Author wf
 * @Date 2020/5/30
 * @Version 1.0
 */
public interface SysRoleMenuRepository extends JpaRepository<SysRoleMenu, Long> {

    /**
     * 查询指定角色的父类权限
     * @param roleId
     * @return
     */
    @Query("SELECT u FROM SysRoleMenu a " +
            "LEFT JOIN SysRole b " +
            "ON a.pkRoleId = b.pkRoleId " +
            "LEFT JOIN SysMenu u " +
            "ON a.menuId = u.pkMenuId " +
            "WHERE a.pkRoleId = ?1 ")
    Object[] getRoleMenuByPkRoleId(long roleId);

    /**
     * 查询指定角色的子类权限
     * @param parentId
     * @param roleId
     * @return
     */
    @Query("SELECT u FROM SysRoleMenu a, SysRole b, SysMenu u " +
            "WHERE a.pkRoleId = b.pkRoleId " +
            "AND a.menuId = u.pkMenuId " +
            "AND a.pkRoleId = ?2 AND u.parentId = ?1")
    Object[] getRoleChildMenuByPkRoleId(long parentId, long roleId);

    /**
     * 批量删除（逻辑）
     * @param ids
     * @return
     */
    @Modifying
    @Transactional(rollbackFor = RuntimeException.class)
    @Query(value = "DELETE FROM SysRoleMenu where pkRoleMenuId in (?1)")
    int deleteBatchByPkRoleId(List<Long> ids);

    /**
     * 查询角色权限
     * @param roleId
     * @param menuId
     * @return
     */
    @Query(value = "SELECT sr.pkRoleMenuId FROM SysRoleMenu sr WHERE sr.pkRoleId = ?1 AND sr.menuId = ?2")
    Long getRoleMenuId(long roleId, long menuId);
}
