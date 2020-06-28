package com.soft1851.smart.campus.repository;

import com.soft1851.smart.campus.model.entity.SysUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;


/**
 * @Description TODO
 * @Author wf
 * @Date 2020/5/19
 * @Version 1.0
 */
public interface SysUserRepository extends JpaRepository<SysUser, Long> {

    /**
     * 根据用户手机号查询用户信息
     *
     * @param phoneNumber
     * @return
     */
    SysUser getSysUserBySysUserPhoneNumber(String phoneNumber);


    /**
     * 根据用户id查询数据信息
     * @param pkUserId
     * @return
     */
    @Query(value = "SELECT ur.sys_user_id,ur.role_id,sr.role_name,su.sys_user_name,su.sys_user_phone_number,su.is_enabled,su.gmt_create,su.sys_user_avatar " +
            "FROM first_smart_campus.user_role ur " +
            "LEFT JOIN first_smart_campus.sys_role sr " +
            "ON ur.role_id = sr.pk_role_id " +
            "LEFT JOIN first_smart_campus.sys_user su " +
            "ON ur.sys_user_id = su.pk_user_id " +
            "WHERE su.pk_user_id=?1",nativeQuery = true)
    Map selectAdminById(String pkUserId);


    /**
     * 根据主键id删除数据
     * @param pkUserId
     */
    @Transactional(rollbackFor = RuntimeException.class)
    @Modifying
    @Query("delete from SysUser where pkUserId = ?1")
    void deleteByPkUserId(String pkUserId);

    /**
     * 根据用户id查询系统用户数据
     * @param pkUserId
     * @return
     */
    SysUser findSysUserByPkUserId(String pkUserId);

    /**
     * 重置密码自动为123456
     * @param pkUserId
     * @return
     */
    @Transactional
    @Modifying
    @Query(value = "update first_smart_campus.sys_user u set u.sys_password=123456 where u.pk_user_id=?1",nativeQuery = true)
    int setPasswordByPkUserId(String pkUserId);

    /**
     * 修改用户是否被激活
     * @param isEnabled
     * @param userId
     * @return
     */
    @Modifying
    @Transactional
    @Query(value = "UPDATE SysUser SET isEnabled = ?1 WHERE pkUserId = ?2")
    int updateIsEnabledById (boolean isEnabled, String userId);

    /**
     * 逻辑单个删除用户
     * @param isDeleted
     * @param phoneNumber
     * @return
     */
    @Modifying
    @Transactional
    @Query(value = "UPDATE SysUser SET isDeleted = ?1 WHERE sysUserPhoneNumber = ?2")
    int updateIsDeletedByPhoneNumber (boolean isDeleted, String phoneNumber);

    /**
     *查询所有未删除数据
     * @return
     */
    List<SysUser> getSysUserByIsDeleted(boolean isDeleted);

    /**
     * 修改用户信息
     * @param sysUser
     * @return
     */
    @Transactional(rollbackFor = RuntimeException.class)
    @Modifying
    @Query("UPDATE SysUser SET sysUserName = :#{#sysUser.sysUserName}, " +
            "sysUserPhoneNumber=:#{#sysUser.sysUserPhoneNumber}, " +
            "isEnabled = :#{#sysUser.isEnabled} " +
            "WHERE pkUserId = :#{#sysUser.pkUserId}")
    int updateSysUserInfo(@Param("sysUser") SysUser sysUser);
}
