package com.soft1851.smart.campus.repository;

import com.soft1851.smart.campus.model.entity.SysBorrow;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.List;

/**
 * @Description TODO
 * @Author 涛涛
 * @Date 2020/6/3 12:58
 * @Version 1.0
 **/
public interface SysBorrowRepository extends JpaRepository<SysBorrow, Long> {
    /**
     * 查询范围内的借阅记录
     * @param startTime
     * @param endTime
     * @return
     */
    List<SysBorrow> getSysBorrowsByGmtCreateBetween(Timestamp startTime, Timestamp endTime);

    /**
     * 修改还书状态
     * @param pkBorrowId
     * @param gmtReturn
     */
    @Modifying
    @Transactional(rollbackFor = RuntimeException.class)
    @Query(value = "update first_smart_campus.sys_borrow b set b.is_returned=true,b.gmt_return=?2 where b.pk_borrow_id=?1",nativeQuery = true)
    void setIsReturn(Long pkBorrowId,Timestamp gmtReturn);

    /**
     * 根据id查找借阅数据
     * @param id
     * @return
     */
    @Query(value = "SELECT * FROM first_smart_campus.sys_borrow WHERE pk_borrow_id=?1 AND is_deleted=false",nativeQuery = true)
    SysBorrow findByPkBorrowId(Long id);


    /**
     * 单个逻辑删除
     * @param pkBorrowId
     */
    @Modifying
    @Transactional(rollbackFor = RuntimeException.class)
    @Query(value = "UPDATE first_smart_campus.sys_borrow SET is_deleted = true WHERE pk_borrow_id = ?1",nativeQuery = true)
    void deleteSysBorrow(Long pkBorrowId);

    /**
     * 批量逻辑删除
     * @param ids
     * @return
     */
    @Modifying
    @Transactional(rollbackFor = RuntimeException.class)
    @Query(value = "update first_smart_campus.sys_borrow s set s.is_deleted = true where s.pk_borrow_id in ?1",nativeQuery = true)
    int deleteBatchByPkBorrowIds(List<Long> ids);
}
