package com.soft1851.smart.campus.controller;


import com.soft1851.smart.campus.constant.ResponseResult;
import com.soft1851.smart.campus.model.dto.*;
import com.soft1851.smart.campus.model.entity.FleaType;
import com.soft1851.smart.campus.service.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author 倪涛涛
 * @version 1.0.0
 * @ClassName FleaController.java
 * @Description TODO
 * @createTime 2020年06月09日 14:37:00
 */
@Slf4j
@RestController
@RequestMapping(value = "/flea/")
@Api(value = "FleaController", tags = {"跳蚤市场接口"})
public class FleaController {
    @Resource
    private FleaGoodsService fleaGoodsService;
    @Resource
    private FleaRewardService fleaRewardService;
    @Resource
    private FleaTypeService fleaTypeService;
    @Resource
    private FleaUserService fleaUserService;
    @Resource
    private FleaOrderService fleaOrderService;
    @Resource
    private FleaCommentService fleaCommentService;

    /**
     * 分页查询所有悬赏
     *
     * @param pageDto PageDto
     * @return Page<FleaReward>
     */
    @PostMapping(value = "reward/all")
    public ResponseResult findAllReward(@RequestBody PageDto pageDto) {
        return fleaRewardService.findAll(pageDto);
    }

    /**
     * 根据id删除单个悬赏
     *
     * @param fleaRewardIdDto FleaRewardIdDto
     * @return ResponseResult
     */
    @PostMapping(value = "reward/deleteOne")
    public ResponseResult deleteOneRewardById(@RequestBody FleaRewardIdDto fleaRewardIdDto) {
        return fleaRewardService.deleteOneById(fleaRewardIdDto);
    }

    /**
     * 批量逻辑删除悬赏
     *
     * @param fleaRewardBatchIdDto FleaRewardBatchIdDto
     * @return ResponseResult
     */
    @PostMapping(value = "reward/batchDelete")
    public ResponseResult batchDeleteRewardById(@RequestBody FleaRewardBatchIdDto fleaRewardBatchIdDto) {
        return fleaRewardService.batchDeleteById(fleaRewardBatchIdDto);
    }

    /**
     * 分页查询所有订单信息
     *
     * @param pageDto PageDto
     * @return ResponseResult
     */
    @PostMapping(value = "order/all")
    public ResponseResult findAllOrder(@RequestBody PageDto pageDto) {
        return fleaOrderService.findAll(pageDto);
    }

    /**
     * 逻辑删除单个订单
     *
     * @param fleaOrderDto FleaOrderDto
     * @return ResponseResult
     */
    @PostMapping(value = "order/logicalDelOne")
    public ResponseResult logicalDelOneOrder(@RequestBody FleaOrderDto fleaOrderDto) {
        return fleaOrderService.logicalDel(fleaOrderDto);
    }

    /**
     * 批量逻辑删除订单
     *
     * @param fleaOrderBatchIdDto FleaOrderBatchIdDto
     * @return ResponseResult
     */
    @PostMapping(value = "order/batchLogicalDel")
    public ResponseResult batchLogicalDelOrder(@RequestBody FleaOrderBatchIdDto fleaOrderBatchIdDto) {
        return fleaOrderService.batchLogicalDel(fleaOrderBatchIdDto);
    }

    /**
     * 分页查询所有用户
     *
     * @param pageDto PageDto
     * @return ResponseResult
     */
    @PostMapping("user/all")
    public ResponseResult findAllUser(@RequestBody PageDto pageDto) {
        return fleaUserService.findAllUser(pageDto);
    }

    @PostMapping("user/find")
    public ResponseResult findUserByContent(@RequestBody FleaSearchDto fleaSearchDto) {
        return fleaUserService.findUserByContent(fleaSearchDto);
    }

    @PostMapping("type/all")
    public ResponseResult findTypeAll() {
        return ResponseResult.success(fleaTypeService.findAllType());
    }

    @PostMapping("type/delete")
    public ResponseResult typeDeleted(@RequestBody SingleParam singleParam) {
        return fleaTypeService.typeDeletedById(singleParam.getPkId());
    }

    @PostMapping("type/modify")
    public ResponseResult typeModify(@RequestBody FleaType fleaType) {
        return fleaTypeService.typeModify(fleaType);
    }

    @PostMapping("/goods/all")
    public ResponseResult getGoods(@RequestBody PageDto pageDto) {
        return fleaGoodsService.getGoodsByTime(pageDto);
    }

    /**
     * @param fleaGoodsDto
     * @return
     */
    @PostMapping(value = "goods/logicalDel")
    public ResponseResult deleteGoodsById(@RequestBody FleaGoodsDto fleaGoodsDto) {
        return fleaGoodsService.logical(fleaGoodsDto.getPkFleaGoodsId());
    }

    @PostMapping(value = "goods/batchLogical")
    public ResponseResult batchLogicalGoods(@RequestBody FleaRewardBatchIdDto fleaRewardBatchIdDto) {
        return fleaGoodsService.batchLogicalDel(fleaRewardBatchIdDto);
    }

    @PostMapping("/comment/del")
    public ResponseResult delOne(@RequestBody FleaCommentDto commentDto) {
        return fleaCommentService.delComment(commentDto);
    }

    @PostMapping("/comment/all")
    public ResponseResult getAll(@RequestBody PageDto pageDto) {
        return fleaCommentService.getAll(pageDto);
    }

    @PostMapping("/comment/batchDel")
    public ResponseResult batchDel(@RequestBody FleaRewardBatchIdDto idDto) {
        return fleaCommentService.batchDel(idDto);
    }

    @PostMapping("type/increased")
    public ResponseResult typeIncreased(@RequestBody FleaTypeIncreasedDto fleaTypeIncreasedDto) {
        return fleaTypeService.typeIncreased(fleaTypeIncreasedDto);
    }

    @PostMapping("dashBorder")
    private ResponseResult sellTypePercent(){
        return fleaGoodsService.dashBorderShow();
    }

    /**
     * findTopFiveMark
     *
     * @return ResponseResult
     */
    @PostMapping(value = "mark/top")
    public ResponseResult findTopFiveMark() {
        return fleaGoodsService.findTopFiveMark();
    }
}
