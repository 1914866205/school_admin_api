package com.soft1851.smart.campus.service;

import com.soft1851.smart.campus.constant.ResponseResult;
import com.soft1851.smart.campus.model.dto.PageDto;
import com.soft1851.smart.campus.model.dto.TimeBorrowPageDto;
import com.soft1851.smart.campus.model.dto.UpdateSysStatementDto;
import com.soft1851.smart.campus.model.entity.SysStatement;

/**
 * @author Tao
 */
public interface SysStatementService {
    /**
     * 分页查询所有声明数据
     * @param pageDto
     * @return
     */
    ResponseResult findAllStatement(PageDto pageDto);

    /**
     * 新增声明
     * @param sysStatement
     * @return
     */
    ResponseResult increaseSysStatement(SysStatement sysStatement);

    /**
     * 修改声明数据
     * @param updateStatement
     * @return
     */
    ResponseResult modificationSysStatement(UpdateSysStatementDto updateStatement);

    /**
     * 删除声明数据根绝id
     * @param sysPkSysStatement
     * @return
     */
    ResponseResult deletionSysStatement(Long sysPkSysStatement);

    /**
     * 批量删除声明数据
     * @param ids
     * @return
     */
    ResponseResult deletedBatch(String ids);

    /**
     * 逻辑删除
     * @param sysPkSysStatement
     * @return
     */
    ResponseResult deleteSysStatement(Long sysPkSysStatement);

    /**
     * 批量逻辑删除数据
     * @param ids
     * @return
     */
    ResponseResult deleteBatchByPkStatementId(String ids);

    /**
     * 时间范围内的时间
     * @param timeBorrowPageDto
     * @return
     */
    ResponseResult getSysStatementsByTime(TimeBorrowPageDto timeBorrowPageDto);
}
