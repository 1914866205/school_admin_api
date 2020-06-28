package com.soft1851.smart.campus.mapper;

import com.soft1851.smart.campus.model.entity.SysBorrow;
import org.apache.ibatis.annotations.Select;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;

/**
 * @author Tao
 */
public interface SysBorrowMapper {
    /**
     * 时间范围内的时间
     * @param startTime
     * @param endTime
     * @param currentPage
     * @param pageSize
     * @return
     * @throws SQLException
     */
    @Select("SELECT * FROM sys_borrow WHERE gmt_create BETWEEN " +
            "#{startTime} AND #{endTime} " +
            "ORDER BY pk_borrow_id ASC " +
            "LIMIT ${pageSize*(currentPage-1)},#{pageSize}")
    List<SysBorrow> getSysBorrowsByTime(Timestamp startTime,Timestamp endTime,Integer currentPage,Integer pageSize) throws SQLException;
}
