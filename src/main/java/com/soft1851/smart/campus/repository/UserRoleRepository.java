package com.soft1851.smart.campus.repository;

import com.soft1851.smart.campus.model.entity.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Tao
 * @version 1.0
 * @ClassName UserRoleRepository
 * @Description TODO
 * @date 2020-06-01 14:08
 **/
public interface UserRoleRepository extends JpaRepository<UserRole, Long> {
    /**
     * 根据id删除
     * @param sysUserId
     */
    @Transactional(rollbackFor = RuntimeException.class)
    @Modifying
    @Query("delete from UserRole where sysUserId = ?1")
    void deleteBySysUserId(String sysUserId);

    /**
     * 根据用户id查询数据
     * @param sysUserId
     * @return
     */
    UserRole findUserRoleBySysUserId(String sysUserId);

    /**
     * 根据用户id修改用户角色
     * @param sysUserId
     */
    @Transactional(rollbackFor = RuntimeException.class)
    @Modifying
    @Query("UPDATE UserRole SET roleId = ?1 WHERE sysUserId = ?2")
    int updateBySysUserId(long roleId, String sysUserId);
}
