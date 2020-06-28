package com.soft1851.smart.campus.controller;


import com.soft1851.smart.campus.constant.ResponseResult;
import com.soft1851.smart.campus.model.dto.BatchDeletionDto;
import com.soft1851.smart.campus.model.dto.PageDto;
import com.soft1851.smart.campus.model.dto.QueryDto;
import com.soft1851.smart.campus.model.entity.SysCard;
import com.soft1851.smart.campus.service.CardService;
import com.soft1851.smart.campus.service.OrderService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @ClassName CardController
 * @Description TODO
 * @Author 田震
 * @Date 2020/5/26
 **/
@Slf4j
@RestController
@Api(tags = "校园卡管理接口")
public class CardController {
    @Resource
    private CardService service;
    @Resource
    private OrderService orderService;

    /**
     * 查询所有消息
     * @param pageDto
     * @return
     */
    @PostMapping("/card/list")
    ResponseResult findAllByPage(@RequestBody PageDto pageDto){
        return service.getAllSysCard(pageDto);
    }


    /**
     * 修改一卡通信息
     * @param sysCard
     * @return
     */
    @PostMapping("/card/modification")
    ResponseResult updateCard (@RequestBody SysCard sysCard){
        return service.updateCard(sysCard);
    }

    /**
     * 删除一卡通信息
     * @param queryDto
     * @return
     */
    @PostMapping("/card/id")
    ResponseResult deleteCard(@RequestBody QueryDto queryDto){
        return service.deleteCard(Long.parseLong(queryDto.getField().toString()));
    }

    /**
     * 单个增加一卡通信息
     * @param sysCard
     * @return
     */
    @PostMapping("/card/increase")
    ResponseResult saveCard(@RequestBody SysCard sysCard){
        return service.insert(sysCard);
    }
    /**
     * 查询清单明细
     * @param queryDto
     * @return
     */
    @PostMapping("/card/consume")
    ResponseResult findAllByJobNumber(@RequestBody QueryDto queryDto){
        return orderService.findALLByJobNumer(queryDto.getField().toString());
    }
    /**
     * 一卡通激活
     * @param queryDto
     * @return
     */
    @PostMapping("card/statuschange")
    ResponseResult updateStatus(@RequestBody QueryDto queryDto){
        return service.updateStatus(Long.parseLong(queryDto.getField().toString()), queryDto.getStatus());
    }
    /**
     * 批量一卡通修改
     * @return List<card>
     */
    @ApiOperation(value = "批量删除一卡通信息",notes = "")
    @PostMapping(value = "card/deletionBath")
    public ResponseResult deletedBatch(@RequestBody BatchDeletionDto batchDeletionDto){
        return service.deletedBatch(batchDeletionDto.getIds());
    }
}