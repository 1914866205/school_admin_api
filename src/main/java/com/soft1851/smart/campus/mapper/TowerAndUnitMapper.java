package com.soft1851.smart.campus.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.soft1851.smart.campus.model.entity.TowerAndUnit;
import com.soft1851.smart.campus.model.entity.TowerUnit;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @Description TODO
 * @Author wf
 * @Date 2020/6/16
 * @Version 1.0
 */
public interface TowerAndUnitMapper extends BaseMapper<TowerAndUnit> {

    /**
     * 根据楼栋id查询所有楼栋单元
     * @param towerId
     * @return
     */
    List<TowerUnit> getAllUnitByTowerId(long towerId);
}
