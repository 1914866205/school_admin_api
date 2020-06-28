package com.soft1851.smart.campus.repository;

import com.soft1851.smart.campus.model.entity.Tower;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @Description TODO
 * @Author wf
 * @Date 2020/6/1
 * @Version 1.0
 */
public interface TowerRepository extends JpaRepository<Tower, Long> {

    /**
     * 根据id修改信息
     * @param tower
     */
    @Modifying
    @Transactional
    @LastModifiedBy
    @Query(value = "UPDATE Tower SET name=:#{#tower.name}," +
            "latitude=:#{#tower.latitude}, " +
            "longitude=:#{#tower.longitude} " +
            "WHERE pkTowerId=:#{#tower.pkTowerId} ")
    void updateTowerByTowerId(@Param("tower") Tower tower);

    /**
     * 根据id修改信息
     * @param id
     */
    @Modifying
    @Transactional
    @LastModifiedBy
    @Query(value = "UPDATE Tower SET isDeleted=true " +
            "WHERE pkTowerId=?1 ")
    void updateTowerIsDeletedById(long id);

    /**
     * 查询所有未删除的楼栋信息
     * @param isDeleted
     * @return
     */
    List<Tower> getTowerByIsDeletedOrderByGmtCreateDesc(boolean isDeleted);
}
