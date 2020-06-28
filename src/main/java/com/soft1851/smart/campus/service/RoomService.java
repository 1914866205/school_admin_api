package com.soft1851.smart.campus.service;

import com.soft1851.smart.campus.model.entity.Room;
import com.soft1851.smart.campus.model.vo.TowerVo;

import java.util.List;
import java.util.Map;

/**
 * @Description TODO
 * @Author wf
 * @Date 2020/6/3
 * @Version 1.0
 */
public interface RoomService {

    /**
     * 查询所有房间信息
     * @return
     */
    List<TowerVo> selectAll();

    /**
     * 新增房间信息
     * @param room
     */
    void insertRoom(Room room);

    /**
     * 根据id删除房间信息
     * @param id
     */
    void deleteRoomById(long id);

    /**
     * 修改房间信息
     * @param room
     */
    void update(Room room);
}
