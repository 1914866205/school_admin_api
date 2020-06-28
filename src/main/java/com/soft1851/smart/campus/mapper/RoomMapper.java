package com.soft1851.smart.campus.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.soft1851.smart.campus.model.entity.Room;
import org.apache.ibatis.annotations.Select;

import java.util.Map;

/**
 * @Description TODO
 * @Author wf
 * @Date 2020/6/18
 * @Version 1.0
 */
public interface RoomMapper extends BaseMapper<Room> {

    @Select(value = "SELECT t.name FROM room r " +
            "LEFT JOIN tower t " +
            "ON t.pk_tower_id = r.tower_id " +
            "WHERE r.id = #{roomId} ")
    Map<String, Object> getRoomTowerByRoomId(long roomId);
}
