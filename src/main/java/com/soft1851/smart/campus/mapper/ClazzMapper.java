package com.soft1851.smart.campus.mapper;

import com.soft1851.smart.campus.model.vo.ClazzVo;
import org.apache.ibatis.annotations.Select;

import java.sql.SQLException;
import java.util.List;

/**
 * @author Tao
 */
public interface ClazzMapper {
    /**
     * 模糊查询班级
     *
     * @param keyword
     * @return
     * @throws SQLException
     */
    @Select({"<script>",
            "SELECT pk_clazz_id,name FROM clazz ",
            "WHERE 1=1 ",
            "<when test='keywords!=null'> ",
            "AND name LIKE CONCAT('%',#{keywords},'%') ",
            "OR grade LIKE CONCAT('%',#{keywords},'%') ",
            "AND is_deleted = false ",
            "</when> ",
            "</script>"})
    List<ClazzVo> findClazzLike(String keyword) throws SQLException;

    /**
     * 查找所有班级数据（用于下拉框）
     *
     * @return
     */
    @Select("SELECT pk_clazz_id,name FROM clazz WHERE is_deleted = false ")
    List<ClazzVo> getAllClazz();

}
