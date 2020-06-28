package com.soft1851.smart.campus.controller;

import com.soft1851.smart.campus.constant.ResponseResult;
import com.soft1851.smart.campus.model.dto.*;
import com.soft1851.smart.campus.model.entity.AppVersion;
import com.soft1851.smart.campus.service.AppVersionService;
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
 * @ClassName AppVersionController
 * @Description TODO
 * @date 2020-06-05 9:15
 **/
@RestController
@RequestMapping("/app")
@Api(value = "AppVersionController", tags = "App版本管理接口")
public class AppVersionController {
    @Resource
    private AppVersionService appVersionService;

    /**
     * 分页查询所有声明
     *
     * @param pageDto
     * @return
     */
    @ApiOperation(value = "分页查询所有APP版本",notes = "")
    @PostMapping(value = "/all")
    ResponseResult findAllAppVersion(@RequestBody PageDto pageDto) {
        return appVersionService.findAllAppVersion(pageDto);
    }

    /**
     * 修改版本数据信息
     *
     * @param updateAppVersionDto
     * @return
     */
    @ApiOperation(value = "修改版本数据信息",notes = "")
    @PostMapping(value = "/modification")
    public ResponseResult updateAppVersion(@RequestBody UpdateAppVersionDto updateAppVersionDto) {
        System.out.println(updateAppVersionDto);
        return appVersionService.modificationAppVersion(updateAppVersionDto);
    }

    /**
     * 新增版本信息
     *
     * @param appVersion
     * @return
     */
    @ApiOperation(value = "新增版本信息",notes = "")
    @PostMapping(value = "/increase")
    public ResponseResult increaseAppVersion(@RequestBody AppVersion appVersion) {
        return appVersionService.increaseAppVersion(appVersion);
    }

    /**
     * 逻辑删除版本信息
     *
     * @param singleParam
     * @return
     */
    @ApiOperation(value = "逻辑删除版本信息",notes = "")
    @PostMapping(value = "/deletion")
    public ResponseResult deleteAppVersion(@RequestBody SingleParam singleParam) {
        return appVersionService.deletionAppVersion(singleParam.getPkId());
    }

    /**
     * 批量逻辑删除反馈
     *
     * @param batchDeletionDto
     * @return
     */
    @ApiOperation(value = "批量逻辑删除反馈",notes = "")
    @PostMapping(value = "/deletionBath")
    public ResponseResult deletedBatch(@RequestBody BatchDeletionDto batchDeletionDto) {
        return appVersionService.deletedBatch(batchDeletionDto.getIds());
    }

    /**
     * 时间范围内的查询
     * @param timeBorrowPageDto
     * @return
     */
    @PostMapping(value = "/page")
    @ApiOperation(value = "时间范围内的查询App版本号",notes = "")
    public ResponseResult getAppVersionsByTime(@RequestBody TimeBorrowPageDto timeBorrowPageDto) {
        return appVersionService.getAppVersionsByTime(timeBorrowPageDto);
    }
}
