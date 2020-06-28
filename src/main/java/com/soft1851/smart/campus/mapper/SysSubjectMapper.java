package com.soft1851.smart.campus.mapper;

import com.soft1851.smart.campus.model.vo.SysSubjectVo;
import org.apache.ibatis.annotations.Select;

import java.sql.SQLException;
import java.util.List;

/**
 * @author Tao
 */
public interface SysSubjectMapper {

    /**
     * 模糊查询学生（用于添加学生到某个班级中）
     *
     * @param keyword
     * @return
     * @throws SQLException
     */
    @Select({"<script>",
            "SELECT pk_subject_id,name FROM sys_subject ",
            "WHERE 1=1 ",
            "<when test='keywords!=null'> ",
            "AND name LIKE CONCAT('%',#{keywords},'%') ",
            "</when> ",
            "</script>"})
    List<SysSubjectVo> findSubjectLike(String keyword) throws SQLException;

    /**
     * 查询所有科目
     * @return
     * @throws SQLException
     */
    @Select("SELECT pk_subject_id,name FROM sys_subject WHERE is_deleted = false")
    List<SysSubjectVo> findAllSubject() throws SQLException;
}
