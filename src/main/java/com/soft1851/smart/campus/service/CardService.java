package com.soft1851.smart.campus.service;


import com.soft1851.smart.campus.constant.ResponseResult;
import com.soft1851.smart.campus.model.dto.PageDto;
import com.soft1851.smart.campus.model.entity.SysCard;

import java.util.List;

/**
 * @ClassName CardService
 * @Description TODO
 * @Author 田震
 * @Date 2020/5/26
 **/
public interface CardService {
    /**
     * 分页查询一卡通数据
     * @param pageDto
     * @return
     */
    ResponseResult findAllByPage(PageDto pageDto);

    /**
     * 批量增加一卡通信息
     * @return
     * @param sysCards
     */
    ResponseResult insertAll(List<SysCard> sysCards);

    /**
     * 删除一卡通信息
     * @return
     */
    ResponseResult deleteCard(Long pkCardId);

    /**
     * 批量删除一卡通
     * @param ids
     * @return
     */
    ResponseResult deletedBatch(String ids);

    /**
     * 修改一卡通信息
     * @param sysCard
     * @return
     */
    ResponseResult updateCard(SysCard sysCard);

    /**
     * 单个增加一卡通信息
     * @param sysCard
     * @return
     */
    ResponseResult insert(SysCard sysCard);
    /**
     * 校园卡激活
     * @param pkCardId
     * @param Status
     * @return
     */
    ResponseResult updateStatus(Long pkCardId,Boolean Status);


    /**
     * 分页查询未被逻辑查询删除的一卡通信息数据
     * @param pageDto
     * @return
     */
    ResponseResult getAllSysCard(PageDto pageDto);

}