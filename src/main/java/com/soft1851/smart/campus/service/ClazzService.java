package com.soft1851.smart.campus.service;

import com.soft1851.smart.campus.constant.ResponseResult;
import com.soft1851.smart.campus.model.dto.PageDto;
import com.soft1851.smart.campus.model.dto.UpdateClazzDto;
import com.soft1851.smart.campus.model.entity.Clazz;

/**
 * @author xunmi
 * @ClassName ClazzService
 * @Description 班级业务逻辑层
 * @Date 2020/6/1
 * @Version 1.0
 **/
public interface ClazzService {

    /**
     * 分页查询所有反馈数据
     * @param pageDto
     * @return
     */
    ResponseResult findAllClazz(PageDto pageDto);

    /**
     * 新增班级
     * @param clazz
     * @return
     */
    ResponseResult increaseClazz(Clazz clazz);

    /**
     * 向一个班级中批量添加
     * @param pkClazzId
     * @param ids
     * @return
     */
    ResponseResult increaseStudentToClazz(Long pkClazzId,String ids);

    /**
     * 逻辑删除班级
     * @param clazzId
     * @return
     */
    ResponseResult deletedClazz(Long clazzId);


    /**
     * 修改班级数据
     * @param updateClazzDto
     * @return
     */
    ResponseResult updateClazz(UpdateClazzDto updateClazzDto);

    /**
     * 批量删除班级数据
     * @param ids
     * @return
     */
    ResponseResult deleteBatchByClazzId(String ids);

    /**
     * 模糊查询班级
     * @param keywords
     * @return
     */
    ResponseResult getAllClazzs(String keywords);


    /**
     * 查询所有班级
     * @return
     */
    ResponseResult getAllClazz();


}
