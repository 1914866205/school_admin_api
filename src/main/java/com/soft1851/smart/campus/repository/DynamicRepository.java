package com.soft1851.smart.campus.repository;

import com.soft1851.smart.campus.model.entity.Dynamic;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author Yujie_Zhao
 * @ClassName DynamicRepository
 * @Description 动态
 * @Date 2020/6/12  15:36
 * @Version 1.0
 **/
public interface DynamicRepository extends JpaRepository<Dynamic ,String> {

    /**
     *
     * @param isDelete
     * @return
     */
    List<Dynamic> findAllByIsDeleted(Boolean isDelete);

    @Query(value = "select m " +
            "FROM Dynamic m " +
            "where  m.isDeleted = false ")
    Page<Dynamic> findAllByIsDeleted(Pageable pageable);

    /**
     * 根据动态id查询存在
     * @param id
     * @param isDelete
     * @return
     */
    Dynamic findDynamicByPkDynamicIdAndIsDeleted(String id,Boolean isDelete);

    /**
     * 批量修改
     * @param ids
     * @return int
     */
    @Modifying
    @Transactional(rollbackFor = RuntimeException.class)
    @Query("update Dynamic t set t.isDeleted = true where t.pkDynamicId in (?1)")
    int updateIsDelete(List<String> ids);
}
