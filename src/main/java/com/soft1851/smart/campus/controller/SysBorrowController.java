package com.soft1851.smart.campus.controller;

import com.soft1851.smart.campus.constant.ResponseResult;
import com.soft1851.smart.campus.model.dto.*;
import com.soft1851.smart.campus.service.SysBorrowService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @Description TODO
 * @Author 涛涛
 * @Date 2020/6/3 12:56
 * @Version 1.0
 **/
@RestController
@Slf4j
@RequestMapping(value = "/borrow")
@Api(value = "SysBorrowController", tags = "借阅记录管理接口")
public class SysBorrowController {
    @Resource
    private SysBorrowService sysBorrowService;

    @PostMapping("/all")
    ResponseResult findAllByPage(@RequestBody PageDto pageDto) {
        return sysBorrowService.findAllByPage(pageDto);
    }

    /**
     * 根据时间查询
     *
     * @param borrowDto
     * @return
     */
    @PostMapping("/time")
    ResponseResult getBorrowByTime(@RequestBody BorrowDto borrowDto) {
        log.info(">>>>>>>>>>>>>>>" + borrowDto);
        return sysBorrowService.getBorrowByTime(borrowDto);
    }


    /**
     * 添加借阅信息
     *
     * @param borrowInsertDto
     * @return
     */
    @PostMapping("/increase")
    ResponseResult borrowInsert(@RequestBody BorrowInsertDto borrowInsertDto) {
        return sysBorrowService.borrowInsert(borrowInsertDto);
    }


    /**
     * 时间范围内的查询
     *
     * @param timeBorrowPageDto
     * @return
     */
    @PostMapping(value = "/page")
    public ResponseResult getSysBorrowsByTime(@RequestBody TimeBorrowPageDto timeBorrowPageDto) {
        return sysBorrowService.getSysBorrowsByTime(timeBorrowPageDto);
    }


    /**
     * 修改还书状态
     * @param singleParam
     * @return
     */
    @ApiOperation(value = "修改还书状态", notes = "")
    @PostMapping(value = "/modification/return")
    public ResponseResult deleteSysFeedback(@RequestBody SingleParam singleParam) {
        return sysBorrowService.setIsReturn(singleParam.getPkId());
    }


    /**
     * 逻辑删除借阅记录
     * @param singleParam
     * @return
     */
    @ApiOperation(value = "逻辑删除借阅记录",notes = "")
    @PostMapping(value = "/deletion")
    public ResponseResult deleteSysBorrows(@RequestBody SingleParam singleParam){
        return sysBorrowService.deleteSysBorrows(singleParam.getPkId());
    }

    /**
     * 批量逻辑删除借阅记录
     * @param batchDeletionDto
     * @return
     */
    @ApiOperation(value = "批量逻辑删除借阅记录",notes = "")
    @PostMapping(value = "/deletionBath")
    public ResponseResult deletedBatch(@RequestBody BatchDeletionDto batchDeletionDto){
        log.info(">>>>>>>>>>>>>>>" + batchDeletionDto);
        return sysBorrowService.deletedBatch(batchDeletionDto.getIds());
    }
}
