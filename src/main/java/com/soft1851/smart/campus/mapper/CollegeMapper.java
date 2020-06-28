package com.soft1851.smart.campus.mapper;

import com.soft1851.smart.campus.model.vo.CollegeVo;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author Tao
 */
public interface CollegeMapper {
    /**
     * 查找所有教师数据
     * @return
     */
    @Select("SELECT t.pk_college_id,t.college_name FROM college t WHERE t.is_deleted = false ")
    List<CollegeVo> getAllCollege();
}
