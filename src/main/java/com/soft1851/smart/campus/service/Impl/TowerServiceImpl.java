package com.soft1851.smart.campus.service.Impl;

import com.soft1851.smart.campus.constant.ResponseResult;
import com.soft1851.smart.campus.mapper.TowerAndUnitMapper;
import com.soft1851.smart.campus.mapper.TowerMapper;
import com.soft1851.smart.campus.model.entity.Tower;
import com.soft1851.smart.campus.model.entity.TowerUnit;
import com.soft1851.smart.campus.repository.TowerRepository;
import com.soft1851.smart.campus.service.TowerService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * @Description TODO
 * @Author wf
 * @Date 2020/6/1
 * @Version 1.0
 */
@Service
public class TowerServiceImpl implements TowerService {
    @Resource
    private TowerRepository towerRepository;
    @Resource
    private TowerMapper towerMapper;
    @Resource
    private TowerAndUnitMapper towerAndUnitMapper;

    @Override
    public List<Tower> findAll() {
        return towerRepository.getTowerByIsDeletedOrderByGmtCreateDesc(false);
    }

    @Override
    public List<Map<String, Object>> findAllByType() {
        List<Tower> towers = towerRepository.findAll();
        List<Map<String, Object>> towerTypes = new LinkedList<>();
        Map<String, Object> map = new LinkedHashMap<>();
        Map<String, Object> map1 = new LinkedHashMap<>();
        map.put("name", "教学楼");
        map1.put("name", "宿舍楼");
        towerTypes.add(map);
        towerTypes.add(map1);
        List<Tower> teachTowers = new LinkedList<>();
        List<Tower> roomTowers = new LinkedList<>();
        towers.forEach(tower -> {
            if(tower.getType() == 1){
                roomTowers.add(tower);
            }else {
                teachTowers.add(tower);
            }
        });
        towerTypes.get(0).put("childTowers", teachTowers);
        towerTypes.get(1).put("childTowers", roomTowers);
        return towerTypes;
    }

    @Override
    public List<Map<String, Object>> getAllUnitByTowerId() {
        List<Map<String, Object>> towers = towerMapper.getAllTowers();
        towers.forEach(tower -> {
            List<TowerUnit> maps = towerAndUnitMapper.getAllUnitByTowerId(Long.parseLong(tower.get("pk_tower_id").toString()));
            tower.put("childUnit", maps);
        });
        return towers;
    }

    @Override
    public void insertTower(Tower tower) {
        tower.setGmtCreate(Timestamp.valueOf(LocalDateTime.now()));
        tower.setIsDeleted(false);
        towerRepository.save(tower);
    }

    @Override
    public void deleteTowerById(long towerId) {
        towerRepository.updateTowerIsDeletedById(towerId);
    }

    @Override
    public void updateTowerByTowerId(Tower tower) {
        towerRepository.updateTowerByTowerId(tower);
    }

    @Override
    public List<Map<String, Object>> getAllTowerRooms() {
        return towerMapper.getTeachTowersByType();
    }

}
