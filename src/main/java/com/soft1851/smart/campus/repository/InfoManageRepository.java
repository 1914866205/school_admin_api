package com.soft1851.smart.campus.repository;

import com.soft1851.smart.campus.model.entity.InfoManage;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author Yujie_Zhao 田震修改
 * @ClassName InfoManageRepository
 * @Description TODO
 * @Date 2020/6/1  10:21
 * @Version 1.0
 **/
public interface InfoManageRepository extends JpaRepository<InfoManage, Long> {


    /**
     * 根据id查询咨询
     * @param id
     * @return
     */
    InfoManage findByPkInfoManageId(Long id);

    /**
     * 根据id逻辑删除
     * @param pkInfoManageId
     */
    @Modifying
    @LastModifiedBy
    @Transactional(rollbackFor = RuntimeException.class)
    @Query(value = "update info_manage set is_deleted = true where pk_info_manage_id = ?1",nativeQuery = true)
    void  deleteByPkInfoManageId(Long pkInfoManageId);

    /**
     * 批量逻辑删除
     * @param ids
     */
    @Modifying
    @Transactional(timeout = 10,rollbackFor = RuntimeException.class)
    @Query("update InfoManage v set v.isDeleted = true where v.pkInfoManageId in ?1")
    void deleteBatch(List<Long> ids);

    /**
     * 改变置顶状态
     * @param pkInfoManageId
     * @param isTop
     */
    @Modifying
    @LastModifiedBy
    @Transactional(rollbackFor = RuntimeException.class)
    @Query(value = "update InfoManage set isTop = ?2 where pkInfoManageId = ?1")
    void changeIsTop(Long pkInfoManageId,Boolean isTop);

    /**
     * 分页查询所有资讯
     * @param pageable
     * @return
     */
    @Query(value = "select * from first_smart_campus.info_manage where is_deleted = false",nativeQuery = true)
    Page<InfoManage> getAllInfoManage(Pageable pageable);



}
