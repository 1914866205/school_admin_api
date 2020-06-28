package com.soft1851.smart.campus.repository;

import com.soft1851.smart.campus.model.entity.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 * @Description TODO
 * @Author wf
 * @Date 2020/5/31
 * @Version 1.0
 */
public interface SysUserRoleRepository extends JpaRepository<UserRole, Long> {

    /**
     * 根据用户手机号查询角色id
     * @param phoneNumber
     * @return
     */
    @Query("SELECT m.roleId FROM UserRole m, SysUser u " +
            "WHERE m.sysUserId = u.pkUserId " +
            "AND u.sysUserPhoneNumber = ?1")
    long getRoleIdByPhoneNumber(String phoneNumber);

}
