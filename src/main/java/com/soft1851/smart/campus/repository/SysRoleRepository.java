package com.soft1851.smart.campus.repository;

import com.soft1851.smart.campus.model.dto.UpdateSysRoleDto;
import com.soft1851.smart.campus.model.entity.SysRole;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author Tao
 */
public interface SysRoleRepository extends JpaRepository<SysRole, Long> {
    /**
     * 查询所有角色,省去被逻辑删除的数据
     * @return
     */
    @Query("select r from SysRole r where r.isDeleted=false order by r.sort asc ")
    List<SysRole> findAllRole();

    /**
     * 根据id查角色信息
     * @param id
     * @return
     */
    SysRole findByPkRoleId(Long id);

    /**
     * 批量删除
     * @param ids
     */
    @Modifying
    @Transactional(timeout = 10,rollbackFor = RuntimeException.class)
    @Query("delete from SysRole s where s.pkRoleId in (?1)")
    void deleteBatch(List<Long> ids);

    /**
     * 按照降序，查询排序ID最大数据
     * @return
     */
    SysRole findTopByOrderBySortDesc();


    /**
     * 修改排序Id
     * @param id
     * @param sort
     * @return
     */
    @Modifying
    @LastModifiedBy
    @Transactional(rollbackFor = RuntimeException.class)
    @Query(value = "update first_smart_campus.sys_role set sort = ?2 where pk_role_id = ?1",nativeQuery = true)
    int updateSysRole(Long id,Integer sort);


    /**
     * 逻辑删除
     * @param pkRoleId
     * @return
     */
    @Transactional
    @Modifying
    @Query(value = "update first_smart_campus.sys_role r set r.is_deleted=true where r.pk_role_id=?1",nativeQuery = true)
    int setIsDeletedByPkRoleId(Long pkRoleId);

    /**
     * 批量删除（逻辑）
     * @param ids
     * @return
     */
    @Modifying
    @Transactional(rollbackFor = RuntimeException.class)
    @Query(value = "update first_smart_campus.sys_role r set r.is_deleted = true where r.pk_role_id in ?1",nativeQuery = true)
    int deleteBatchByPkRoleId(List<Long> ids);

    /**
     * 分页查询未被逻辑删除的系统角色数据
     * @param pageable
     * @return
     */
    @Query(value = "select * from first_smart_campus.sys_role where is_deleted = false",nativeQuery = true)
    Page<SysRole> getAllSysRole(Pageable pageable);

    /**
     * 修改系统角色数据
     * @param updateSysRoleDto
     */
    @Transactional(rollbackFor = RuntimeException.class)
    @Modifying
    @Query(value = "UPDATE SysRole SET roleName=:#{#updateSysRoleDto.roleName}, " +
            "roleDescription=:#{#updateSysRoleDto.roleDescription} " +
            "WHERE pkRoleId =:#{#updateSysRoleDto.pkRoleId}")
    void updateSysRole(@Param("updateSysRoleDto") UpdateSysRoleDto updateSysRoleDto);
}
