package com.soft1851.smart.campus.controller;

import com.soft1851.smart.campus.constant.ResponseResult;
import com.soft1851.smart.campus.model.dto.*;
import com.soft1851.smart.campus.model.entity.SysFeedback;
import com.soft1851.smart.campus.service.SysFeedbackService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @author Tao
 * @version 1.0
 * @ClassName sysFeedbackController
 * @Description TODO
 * @date 2020-06-02 23:22
 **/
@RestController
@RequestMapping(value = "/feedback")
@Api(value = "SysFeedbackController", tags = "反馈管理接口")
public class SysFeedbackController {
    @Resource
    private SysFeedbackService sysFeedbackService;


    /**
     * 分页查询所有反馈
     * @param pageDto
     * @return
     */
    @ApiOperation(value = "分页查询所有反馈",notes = "")
    @PostMapping(value = "/all")
    ResponseResult findAllSysFeedback(@RequestBody PageDto pageDto){
        return sysFeedbackService.findAllSysFeedback(pageDto);
    }

    /**
     * 新增反馈
     * @param sysFeedback
     * @return
     */
    @ApiOperation(value = "新增反馈",notes = "")
    @PostMapping(value = "/increase")
    public ResponseResult increaseSysFeedback(@RequestBody SysFeedback sysFeedback){
        return sysFeedbackService.increaseSysFeedback(sysFeedback);
    }

    /**
     * 修改反馈
     * @param sysFeedback
     * @return
     */
    @ApiOperation(value = "修改反馈",notes = "")
    @PostMapping(value = "/modification")
    public ResponseResult modificationSysFeedback(@RequestBody UpdateSysFeedbackDto sysFeedback){
        return sysFeedbackService.modificationSysFeedback(sysFeedback);
    }


//    /**
////     * 删除反馈
////     * @param id
////     * @return
////     */
////    @DeleteMapping(value = "/deletion/{id}")
////    public ResponseResult deletedSysFeedback(@PathVariable Long id){
////        return sysFeedbackService.deletionSysFeedback(id);
////    }

    /**
     * 逻辑删除反馈
     * @param singleParam
     * @return
     */
    @ApiOperation(value = "逻辑删除反馈",notes = "")
    @PostMapping(value = "/deletion")
    public ResponseResult deleteSysFeedback(@RequestBody SingleParam singleParam){
        return sysFeedbackService.deleteSysFeedback(singleParam.getPkId());
    }

//    /**
//     * 批量删除反馈
//     * @param ids
//     * @return
//     */
//    @DeleteMapping(value = "/deletionBath/{ids}")
//    public ResponseResult deletedBatch(@PathVariable String ids){
//        return sysFeedbackService.deletedBatch(ids);
//    }


    /**
     * 批量逻辑删除反馈
     * @param batchDeletionDto
     * @return
     */
    @ApiOperation(value = "批量逻辑删除反馈",notes = "")
    @PostMapping(value = "/deletionBath")
    public ResponseResult deletedBatch(@RequestBody BatchDeletionDto batchDeletionDto){
        return sysFeedbackService.deleteBatchByPkFeedbackId(batchDeletionDto.getIds());
    }


    /**
     * 时间范围内的查询
     * @param timeBorrowPageDto
     * @return
     */
    @PostMapping(value = "/page")
    @ApiOperation(value = "时间范围内的查询反馈",notes = "")
    public ResponseResult getSysFeedbackByTime(@RequestBody TimeBorrowPageDto timeBorrowPageDto) {
        System.out.println(timeBorrowPageDto);
        return sysFeedbackService.getSysFeedbackByTime(timeBorrowPageDto);
    }

    /**
     * 导出excel
     */
    @GetMapping(value = "/export")
    public void exportData() {
        sysFeedbackService.exportData();
    }

}
