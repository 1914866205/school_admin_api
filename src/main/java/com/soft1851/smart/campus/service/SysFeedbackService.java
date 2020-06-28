package com.soft1851.smart.campus.service;

import com.soft1851.smart.campus.constant.ResponseResult;
import com.soft1851.smart.campus.model.dto.PageDto;
import com.soft1851.smart.campus.model.dto.TimeBorrowPageDto;
import com.soft1851.smart.campus.model.dto.UpdateSysFeedbackDto;
import com.soft1851.smart.campus.model.entity.SysFeedback;

/**
 * @author Tao
 */
public interface SysFeedbackService {
    /**
     * 分页查询所有反馈数据
     * @param pageDto
     * @return
     */
    ResponseResult findAllSysFeedback(PageDto pageDto);

    /**
     * 新增反馈
     * @param sysFeedback
     * @return
     */
    ResponseResult increaseSysFeedback(SysFeedback sysFeedback);

    /**
     * 修改反馈数据
     * @param updateSysFeedbackDto
     * @return
     */
    ResponseResult modificationSysFeedback(UpdateSysFeedbackDto updateSysFeedbackDto);

    /**
     * 根据id删除反馈数据
     * @param pkFeedbackId
     * @return
     */
    ResponseResult deletionSysFeedback(Long pkFeedbackId);

    /**
     * 批量删除声明数据
     * @param ids
     * @return
     */
    ResponseResult deletedBatch(String ids);

    /**
     * 逻辑删除
     * @param pkFeedbackId
     * @return
     */
    ResponseResult deleteSysFeedback(Long pkFeedbackId);

    /**
     * 批量删除声明数据
     * @param ids
     * @return
     */
    ResponseResult deleteBatchByPkFeedbackId(String ids);


    /**
     * 时间范围内的时间
     * @param timeBorrowPageDto
     * @return
     */
    ResponseResult getSysFeedbackByTime(TimeBorrowPageDto timeBorrowPageDto);

    /**
     * 导出反馈数据
     */
    void exportData();
}
