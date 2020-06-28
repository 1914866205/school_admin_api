package com.soft1851.smart.campus.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.soft1851.smart.campus.model.entity.SysFeedback;
import org.apache.ibatis.annotations.Select;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;

/**
 * @author Tao
 */
public interface SysFeedbackMapper extends BaseMapper<SysFeedback> {
    /**
     * 根据时间查询反馈
     * @param startTime
     * @param endTime
     * @param currentPage
     * @param pageSize
     * @return
     * @throws SQLException
     */
    @Select("SELECT * FROM sys_feedback WHERE gmt_create BETWEEN " +
            "#{startTime} AND #{endTime} " +
            "ORDER BY gmt_create DESC " +
            "LIMIT ${pageSize*(currentPage-1)},#{pageSize}")
    List<SysFeedback> getSysFeedbackByTime(Timestamp startTime, Timestamp endTime, Integer currentPage, Integer pageSize) throws SQLException;

}
