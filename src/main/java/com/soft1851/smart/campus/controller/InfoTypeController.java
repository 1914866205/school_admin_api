package com.soft1851.smart.campus.controller;

import com.soft1851.smart.campus.constant.ResponseResult;
import com.soft1851.smart.campus.model.dto.BatchDeletionDto;
import com.soft1851.smart.campus.model.dto.PageDto;
import com.soft1851.smart.campus.model.dto.SingleParam;
import com.soft1851.smart.campus.model.entity.InfoType;
import com.soft1851.smart.campus.service.InfoTypeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @author Yujie_Zhao
 * @ClassName InfoTypeController
 * @Description 资讯分类Controller层
 * @Date 2020/6/3  8:52
 * @Version 1.0
 **/
@RestController
@Slf4j
@RequestMapping(value = "/infoType")
@Api(value = "InfoTypeController",tags = {"资讯分类接口"})
public class InfoTypeController {
    @Resource
    private InfoTypeService infoTypeService;

    /**
     * 分页查询所有资讯分类
     * @param pageDto
     * @return
     */
    @ApiOperation(value = "分页查询所有资讯分类",notes = "")
    @PostMapping(value = "/all")
    ResponseResult getAllInfoType(@RequestBody PageDto pageDto){
        return infoTypeService.getAllInfoType(pageDto);
    }

    /**
     * 添加资讯分类
     * @param infoType
     * @return
     */
    @ApiOperation(value = "添加资讯分类",notes = "")
    @PostMapping(value = "/insert")
    public ResponseResult insertInfoType(@RequestBody InfoType infoType){
        return infoTypeService.insertInfoType(infoType);
    }

    /**
     * 删除资讯分类
     * @param singleParam
     * @return
     */
    @ApiOperation(value = "删除资讯分类",notes = "")
    @PostMapping(value = "/deletion/id")
    public ResponseResult deleteInfoType(@RequestBody SingleParam singleParam){
        return infoTypeService.deleteInfoType(singleParam.getPkId());
    }

    /**
     * 批量删除咨询
     * @return
     */
    @ApiOperation(value = "批量删除咨询分类",notes = "")
    @PostMapping(value = "/deletionBath/ids")
    public ResponseResult deletedBatch(@RequestBody BatchDeletionDto batchDeletionDto){
        return infoTypeService.deletedBatch(batchDeletionDto.getIds());
    }

    /**
     * 修改资讯分类
     * @param infoType
     * @return
     */
    @ApiOperation(value = "修改资讯分类",notes = "")
    @PostMapping(value = "/modification")
    public ResponseResult updateInfoType(@RequestBody InfoType infoType){
        return infoTypeService.updateInfoType(infoType);
    }

}
