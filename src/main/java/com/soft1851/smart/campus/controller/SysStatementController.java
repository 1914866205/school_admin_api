package com.soft1851.smart.campus.controller;

import com.soft1851.smart.campus.constant.ResponseResult;
import com.soft1851.smart.campus.model.dto.*;
import com.soft1851.smart.campus.model.entity.SysStatement;
import com.soft1851.smart.campus.service.SysStatementService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author Tao
 * @version 1.0
 * @ClassName SysStatementController
 * @Description TODO
 * @date 2020-06-02 13:46
 **/
@RestController
@RequestMapping(value = "/statement")
@Api(value = "SysStatementController", tags = "声明管理接口")
public class SysStatementController {
    @Resource
    private SysStatementService sysStatementService;

    /**
     * 分页查询所有声明
     * @param pageDto
     * @return
     */
    @ApiOperation(value = "分页查询所有声明",notes = "")
    @PostMapping(value = "/all")
    ResponseResult findAllStatement(@RequestBody PageDto pageDto){
        return sysStatementService.findAllStatement(pageDto);
    }

    /**
     * 新增声明
     * @param sysStatement
     * @return
     */
    @ApiOperation(value = "新增声明",notes = "")
    @PostMapping(value = "/increase")
    public ResponseResult increaseSysStatement(@RequestBody SysStatement sysStatement){
        return sysStatementService.increaseSysStatement(sysStatement);
    }

    /**
     * 修改声明
     * @param updateSysStatementDto
     * @return
     */
    @ApiOperation(value = "修改声明",notes = "")
    @PostMapping(value = "/modification")
    public ResponseResult modificationSysStatement(@RequestBody UpdateSysStatementDto updateSysStatementDto){
        return sysStatementService.modificationSysStatement(updateSysStatementDto);
    }


//    /**
//     * 删除声明
//     * @param id
//     * @return
//     */
//    @DeleteMapping(value = "/deletion/{id}")
//    public ResponseResult deletedSysStatement(@PathVariable Long id){
//        return sysStatementService.deletionSysStatement(id);
//    }
//
//
//    /**
//     * 批量删除声明
//     * @param ids
//     * @return
//     */
//    @DeleteMapping(value = "/deletionBath/{ids}")
//    public ResponseResult deletedBatch(@PathVariable String ids){
//        return sysStatementService.deletedBatch(ids);
//    }


    /**
     * 逻辑删除声明
     * @param singleParam
     * @return
     */
    @ApiOperation(value = "逻辑删除声明",notes = "")
    @PostMapping(value = "/deletion")
    public ResponseResult deleteSysStatement(@RequestBody SingleParam singleParam){
        return sysStatementService.deleteSysStatement(singleParam.getPkId());
    }

    /**
     * 批量逻辑删除声明
     * @param batchDeletionDto
     * @return
     */
    @ApiOperation(value = "批量逻辑删除声明",notes = "")
    @PostMapping(value = "/deletionBath")
    public ResponseResult deletedBatch(@RequestBody BatchDeletionDto batchDeletionDto){
        return sysStatementService.deleteBatchByPkStatementId(batchDeletionDto.getIds());
    }



    /**
     * 时间范围内的查询
     * @param timeBorrowPageDto
     * @return
     */
    @PostMapping(value = "/page")
    @ApiOperation(value = "时间范围内的查询声明",notes = "")
    public ResponseResult getSysStatementsByTime(@RequestBody TimeBorrowPageDto timeBorrowPageDto) {
        return sysStatementService.getSysStatementsByTime(timeBorrowPageDto);
    }

}
