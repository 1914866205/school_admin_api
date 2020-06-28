package com.soft1851.smart.campus.controller;

import com.soft1851.smart.campus.constant.ResponseResult;
import com.soft1851.smart.campus.model.dto.BatchDeletionDto;
import com.soft1851.smart.campus.model.dto.PageDto;
import com.soft1851.smart.campus.model.dto.QueryDto;
import com.soft1851.smart.campus.model.dto.SingleParam;
import com.soft1851.smart.campus.model.entity.InfoManage;
import com.soft1851.smart.campus.model.vo.InfoManageTypeIdVo;
import com.soft1851.smart.campus.service.InfoManageService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @author Yujie_Zhao
 * @ClassName InfoManageController
 * @Description TODO
 * @Date 2020/6/1  10:27
 * @Version 1.0
 **/
@RestController
@Slf4j
@RequestMapping(value = "/info")
@Api(value = "InfoManageController",tags = {"资讯接口"})
public class InfoManageController {

    @Resource
    private InfoManageService infoManageService;

    /**
     * 分页查找所有资讯
     * @return List<InfoManage>
     */
    @ApiOperation(value = "添加咨询",notes = "")
    @PostMapping(value = "/insert")
    public ResponseResult insertInfoManage(@RequestBody InfoManageTypeIdVo infoManageTypeIdVo){
        return infoManageService.insertInfoManage(infoManageTypeIdVo);
    }

    /**
     * 分页查找所有资讯
     * @return List<InfoManage>
     */
    @ApiOperation(value = "分页查找所有资讯",notes = "请求参数为当前页和页面条数，不包含置顶咨询")
    @PostMapping(value = "/all")
    public ResponseResult findAllInfoByPage(@RequestBody PageDto pageDto){
        return infoManageService.findAllInfo(pageDto);
    }

    /**
     * 单个删除咨询
     * @param singleParam
     * @return
     */
    @ApiOperation(value = "单个删除咨询",notes = "")
    @PostMapping(value = "/deletion/id")
    public ResponseResult deletedInfo(@RequestBody SingleParam singleParam){
        return infoManageService.deleteInfoManage(singleParam.getPkId());
    }

    /**
     * 批量删除咨询
     * @return List<InfoManage>
     */
    @ApiOperation(value = "批量删除咨询",notes = "")
    @PostMapping(value = "/deletionBath/ids")
    public ResponseResult deletedBatch(@RequestBody BatchDeletionDto batchDeletionDto){
        return infoManageService.deletedBatch(batchDeletionDto.getIds());
    }

    /**
     * 修改资讯
     * @param infoManage
     * @return
     */
    @ApiOperation(value = "修改资讯",notes = "给全部字段")
    @PostMapping(value = "/modification")
    public ResponseResult updateInfoManage(@RequestBody InfoManage infoManage){
        return infoManageService.updateInfoManage(infoManage);
    }

    /**
     *
     * @param queryDto
     * @return
     */
    @ApiOperation(value = "修改资讯置顶状态",notes = "请求参数id/isTop")
    @PostMapping(value = "/isTop")
    public ResponseResult changeInfoMange(@RequestBody QueryDto queryDto){
        return infoManageService.changeInfoMange(queryDto);
    }
}
