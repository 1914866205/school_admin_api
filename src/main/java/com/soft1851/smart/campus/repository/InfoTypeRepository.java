package com.soft1851.smart.campus.repository;

import com.soft1851.smart.campus.model.entity.InfoType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author Yujie_Zhao
 * @ClassName InfoTypeRepository
 * @Description TODO
 * @Date 2020/6/2  10:10
 * @Version 1.0
 **/

public interface InfoTypeRepository extends JpaRepository<InfoType, Long> {


    /**
     * 分页查询所有声明数据
     * @param pageable
     * @return
     */
    @Query(value = "select * from first_smart_campus.info_type where is_deleted = false",nativeQuery = true)
    Page<InfoType> getAllInfoType(Pageable pageable);

    /**
     * 按照降序，查询排序ID最大数据
     * @return
     */
    InfoType findTopByOrderBySortDesc();
    /**
     * 根据id查咨询
     * @param id
     * @return
     */
    @Query(value = "SELECT * FROM info_type WHERE pk_info_type_id=?1 AND is_deleted=false",nativeQuery = true)
    InfoType findByPkInfoTypeId(Long id);

    /**
     * 单个逻辑删除
     * @param pkInfoTypeId
     */
    @Modifying
    @Transactional(rollbackFor = RuntimeException.class)
    @Query(value = "UPDATE first_smart_campus.info_type SET is_deleted = true WHERE pk_info_type_id =?1",nativeQuery = true)
    void deleteInfoType(Long pkInfoTypeId);

    /**
     * 批量逻辑删除
     * @param ids
     * @return
     */
    @Modifying
    @Transactional(rollbackFor = RuntimeException.class)
    @Query(value = "update first_smart_campus.info_type s set s.is_deleted = true where s.pk_info_type_id in ?1",nativeQuery = true)
    int deleteBatchInfoType(List<Long> ids);
}
