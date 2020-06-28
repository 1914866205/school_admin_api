package com.soft1851.smart.campus.controller;

import com.soft1851.smart.campus.constant.ResponseResult;
import com.soft1851.smart.campus.model.dto.IntSingleParam;
import com.soft1851.smart.campus.model.entity.Tower;
import com.soft1851.smart.campus.service.TowerService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * @Description TODO
 * @Author wf
 * @Date 2020/6/2
 * @Version 1.0
 */
@RestController
@RequestMapping(value = "/tower")
@Api(value = "TowerController", tags = "楼栋用户管理接口")
public class TowerController {
    @Resource
    private TowerService towerService;

    @ApiOperation(value = "获取所有楼栋信息")
    @PostMapping(value = "/list")
    public List<Tower> selectAll() {
        return towerService.findAll();
    }

    @ApiOperation(value = "分类获取所有楼栋信息")
    @PostMapping(value = "/list/type")
    public List<Map<String, Object>> getAllByType() {
        return towerService.findAllByType();
    }

    @ApiOperation(value = "查询所有楼栋的单元信息")
    @PostMapping(value = "/units/list")
    public List<Map<String, Object>> getUnitsByTowerId() {
        return towerService.getAllUnitByTowerId();
    }

    @PostMapping("/increase")
    public ResponseResult insert(@RequestBody Tower tower) {
        towerService.insertTower(tower);
        return ResponseResult.success();
    }

    @PostMapping("/single")
    public ResponseResult updateByTowerId(@RequestBody Tower tower) {
        towerService.updateTowerByTowerId(tower);
        return ResponseResult.success();
    }

    @PostMapping("/id")
    public ResponseResult deleteByTowerId(@RequestBody IntSingleParam intSingleParam) {
        towerService.deleteTowerById(intSingleParam.getId());
        return ResponseResult.success();
    }

    @ApiOperation(value = "查询所有教学楼栋及其房间信息")
    @PostMapping(value = "/list/teach")
    public List<Map<String, Object>> getAllTowers() {
        return towerService.getAllTowerRooms();
    }


}
