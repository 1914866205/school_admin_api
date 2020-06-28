package com.soft1851.smart.campus.mapper;

import com.soft1851.smart.campus.model.entity.AppVersion;
import org.apache.ibatis.annotations.Select;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;

/**
 * @author Tao
 */
public interface AppVersionMapper {
    /**
     * 根据时间查询APP版本数据
     * @param startTime
     * @param endTime
     * @param currentPage
     * @param pageSize
     * @return
     * @throws SQLException
     */
    @Select("SELECT * FROM app_version WHERE gmt_create BETWEEN " +
            "#{startTime} AND #{endTime} " +
            "ORDER BY gmt_create DESC " +
            "LIMIT ${pageSize*(currentPage-1)},#{pageSize}")
    List<AppVersion> getAppVersionByTime(Timestamp startTime, Timestamp endTime, Integer currentPage, Integer pageSize) throws SQLException;

}
