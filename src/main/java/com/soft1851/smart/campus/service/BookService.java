package com.soft1851.smart.campus.service;

import com.soft1851.smart.campus.constant.ResponseResult;
import com.soft1851.smart.campus.model.dto.PageDto;
import com.soft1851.smart.campus.model.dto.SysBookDto;

/**
 * @Description TODO
 * @Author 涛涛
 * @Date 2020/6/1 8:15
 * @Version 1.0
 **/
public interface BookService {
    /**
     * 添加一种图书
     *
     * @param sysBookDto
     * @return
     */
    ResponseResult increaseSysBook(SysBookDto sysBookDto);

    /**
     * 删除一条数据
     * @param pkBookId
     * @return
     */
    ResponseResult deleteBook(Long pkBookId);
    /**
     * 批量删除图书
     *
     * @param ids
     * @return
     */
    ResponseResult deletedBatchSysBook(String ids);

    /**
     * 发现所有图书
     * @return
     */
    ResponseResult findAllByPage(PageDto pageDto);

    /**
     * 更新图书信息
     * @param sysBookDto
     * @return
     */
    ResponseResult updatedBook(SysBookDto sysBookDto);

    /**
     * 分页查询未被逻辑查询删除的图书信息数据
     * @param pageDto
     * @return
     */
    ResponseResult getAllSysBook(PageDto pageDto);
}