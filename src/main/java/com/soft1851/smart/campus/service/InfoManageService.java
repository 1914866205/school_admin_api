package com.soft1851.smart.campus.service;

import com.soft1851.smart.campus.constant.ResponseResult;
import com.soft1851.smart.campus.model.dto.PageDto;
import com.soft1851.smart.campus.model.dto.QueryDto;
import com.soft1851.smart.campus.model.entity.InfoManage;
import com.soft1851.smart.campus.model.vo.InfoManageTypeIdVo;

/**
 * @author Yujie_Zhao
 * @ClassName InfoManageService
 * @Description 资讯
 * @Date 2020/6/1  10:21
 * @Version 1.0
 **/
public interface InfoManageService {


    /**
     * 单个添加
     * @param infoManageTypeIdVo
     * @return
     */
    ResponseResult insertInfoManage(InfoManageTypeIdVo infoManageTypeIdVo);

    /**
     * 分页查询所有咨讯
     * @param pageDto
     * @return
     */
    ResponseResult findAllInfo(PageDto pageDto);


    /**
     * 单个删除咨询
     * @param id
     * @return
     */
    ResponseResult deleteInfoManage(Long id);

    /**
     * 批量删除咨询
     * @param ids
     * @return
     */
    ResponseResult deletedBatch(String ids);

    /**
     * 修改咨询
     * @param infoManage
     * @return
     */
    ResponseResult updateInfoManage(InfoManage infoManage);

    /**
     * 改变置顶状态
     * @param queryDto
     * @return
     */
    ResponseResult changeInfoMange(QueryDto queryDto);
}
