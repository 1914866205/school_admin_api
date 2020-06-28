package com.soft1851.smart.campus.controller;

import com.soft1851.smart.campus.constant.ResponseResult;
import com.soft1851.smart.campus.model.dto.*;
import com.soft1851.smart.campus.service.ReportLossService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @ClassName ReportLossController
 * @Description TODO
 * @Author 田震
 * @Date 2020/6/1
 **/
@Slf4j
@RestController
@Api(value = "ReportLossController",tags = {"挂失接口"})
public class ReportLossController {
    @Resource
    private ReportLossService reportLossService;
    /**
     * 分页查询所有挂失消息
     * @param pageDto
     * @return
     */
    @PostMapping("/loss/all")
    ResponseResult findAllByPage(@RequestBody PageDto pageDto){
        return reportLossService.getAllReportLoss(pageDto);
    }

    /**
     * 申请挂失
     * @param queryDto
     * @return
     */
    @PostMapping("/loss/statuschange")
    ResponseResult updateLossStatus(@RequestBody QueryDto queryDto){
        return  reportLossService.updateLossStatus(Long.parseLong(queryDto.getField().toString()),
                queryDto.getStatus());
    }

    /**
     * 删除挂失信息
     * @param
     * @return
     */
    @PostMapping("/loss/deletion")
    ResponseResult deleteReportLoss(@RequestBody FleaRewardIdDto fleaRewardIdDto){
        return reportLossService.deleteReportLoss(fleaRewardIdDto.getFleaRewardId());
    }

    /**
     * 批量删除挂失
     * @return List<ReportLoss>
     */
    @ApiOperation(value = "批量删除挂失信息",notes = "")
    @PostMapping(value = "/deletionBath")
    public ResponseResult deletedBatch(@RequestBody BatchDeletionDto batchDeletionDto){
        return reportLossService.deletedBatch(batchDeletionDto.getIds());
    }
    /**
     * 申请挂失
     * @param bookBatchDeleteDto
     * @return
     */
    @ApiOperation(value = "申请挂失信息",notes = "")
    @PostMapping(value = "/increase")
    public ResponseResult adminInsertReportLoss(@RequestBody BookBatchDeleteDto bookBatchDeleteDto){
        return reportLossService.adminInsertReportLoss(bookBatchDeleteDto.getIds());
    }

}