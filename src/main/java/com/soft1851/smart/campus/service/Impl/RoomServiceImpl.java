package com.soft1851.smart.campus.service.Impl;

import com.soft1851.smart.campus.model.entity.Room;
import com.soft1851.smart.campus.model.vo.TowerVo;
import com.soft1851.smart.campus.repository.RoomRepository;
import com.soft1851.smart.campus.service.RoomService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

/**
 * @Description TODO
 * @Author wf
 * @Date 2020/6/3
 * @Version 1.0
 */
@Service
public class RoomServiceImpl implements RoomService {
    @Resource
    private RoomRepository roomRepository;

    @Override
    public List<TowerVo> selectAll() {
        return roomRepository.selectAll();
    }

    @Override
    public void insertRoom(Room room) {
        room.setGmtCreate(Timestamp.valueOf(LocalDateTime.now()));
        room.setGmtModified(Timestamp.valueOf(LocalDateTime.now()));
        room.setIsDeleted(false);
        room.setElectricityBalance((double) 0);
        roomRepository.save(room);
    }

    @Override
    public void deleteRoomById(long id) {
        roomRepository.updateRoomIsDeletedById(id);
    }

    @Override
    public void update(Room room) {
        roomRepository.updateRoomById(room);
    }
}
