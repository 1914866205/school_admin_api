package com.soft1851.smart.campus.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.soft1851.smart.campus.model.entity.Tower;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

/**
 * @Description TODO
 * @Author wf
 * @Date 2020/6/16
 * @Version 1.0
 */
public interface TowerMapper extends BaseMapper<Tower> {

    /**
     * 获取所有楼栋信息
     * @return
     */
    @Select(value = "SELECT pk_tower_id, name FROM tower ")
    List<Map<String, Object>> getAllTowers();

    /**
     * 根据楼栋id查询楼栋信息
     * @param towerId
     * @return
     */
    @Select(value = "SELECT name FROM tower WHERE pk_tower_id = #{towerId}")
    Map<String, Object> getTowerById(Long towerId);


    /**
     * 根据类型查询教学楼
     * @return
     */
    List<Map<String, Object>> getTeachTowersByType();

    /**
     * 根据楼栋id查询房间
     * @param towerId
     * @return
     */
    List<Map<String, Object>> getRoomByTowerId(long towerId);


}
