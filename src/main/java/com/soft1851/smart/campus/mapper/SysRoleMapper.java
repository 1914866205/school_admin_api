package com.soft1851.smart.campus.mapper;

import org.apache.ibatis.annotations.Select;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

/**
 * @author Tao
 */
public interface SysRoleMapper {
    /**
     * 查询所有系统角色
     * @return
     * @throws SQLException
     */
    @Select("SELECT s.pk_role_id,s.role_name FROM sys_role s WHERE is_deleted = false")
    List<Map> getAllSysRole() throws SQLException;
}
