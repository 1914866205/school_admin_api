package com.soft1851.smart.campus.controller;

import com.soft1851.smart.campus.constant.ResponseResult;
import com.soft1851.smart.campus.model.dto.IntSingleParam;
import com.soft1851.smart.campus.model.entity.Room;
import com.soft1851.smart.campus.model.vo.TowerVo;
import com.soft1851.smart.campus.service.RoomService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Description TODO
 * @Author wf
 * @Date 2020/6/3
 * @Version 1.0
 */
@RestController
@RequestMapping(value = "/room")
@Api(value = "RoomController" ,tags = "房间接口")
public class RoomController {
    @Resource
    private RoomService roomService;

    @ApiOperation(value = "查询所有房间信息")
    @PostMapping(value = "/list")
    public List<TowerVo> selectAll() {
        return roomService.selectAll();
    }

    @ApiOperation(value = "新增房间信息")
    @PostMapping
    public ResponseResult insertRoom(@RequestBody Room room) {
        roomService.insertRoom(room);
        return ResponseResult.success();
    }

    @ApiOperation(value = "修改房间信息")
    @PostMapping("/modification/id")
    public ResponseResult updateRoomById(@RequestBody Room room) {
        roomService.update(room);
        return ResponseResult.success();
    }

    @PostMapping(value = "/id")
    public ResponseResult deleteRoomById(@RequestBody IntSingleParam intSingleParam) {
        roomService.deleteRoomById(intSingleParam.getId());
        return ResponseResult.success();
    }
}

