package com.soft1851.smart.campus.repository;

import com.soft1851.smart.campus.model.entity.Collections;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author Yujie_Zhao
 * @ClassName CollectionsRepository
 * @Description 动态的收藏
 * @Date 2020/6/15  14:02
 * @Version 1.0
 **/
public interface CollectionsRepository extends JpaRepository<Collections,String> {
    /**
     * 查找存在的收藏
     * @param id
     * @param isDelete
     * @return
     */
    Collections findCollectionsByPkCollectionIdAndIsDeleted(String id,Boolean isDelete);

    /**
     * 批量修改
     * @param ids
     * @return int
     */
    @Modifying
    @Transactional(rollbackFor = RuntimeException.class)
    @Query("update Collections t set t.isDeleted = true where t.pkCollectionId in (?1)")
    int updateIsDelete(List<String> ids);
}
