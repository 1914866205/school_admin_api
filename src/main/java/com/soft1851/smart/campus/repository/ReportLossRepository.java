package com.soft1851.smart.campus.repository;


import com.soft1851.smart.campus.model.entity.ReportLoss;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @ClassName ReportLossRepository
 * @Description TODO
 * @Author 田震
 * @Date 2020/6/1
 **/
public interface ReportLossRepository extends JpaRepository<ReportLoss, Long> {
    /**
     * 申请挂失
     * @param pkReportLossId
     * @param lossStatus
     * @return
     */
    @Modifying
    @LastModifiedBy
    @Transactional(rollbackFor = RuntimeException.class)
    @Query(value = "update report_loss set loss_status = ?2 where pk_report_loss_id = ?1",nativeQuery = true)
    int updateLossStatus(Long pkReportLossId,Boolean lossStatus);

    /**
     * 根据id删除挂失信息
     * @param pkReportLossId
     * @return
     */
    @Modifying
    @LastModifiedBy
    @Transactional(rollbackFor = RuntimeException.class)
    @Query(value = "update report_loss set is_deleted = true where pk_report_loss_id = ?1",nativeQuery = true)
    void deleteBypkReportLossId(Long pkReportLossId);
    /**
     * 批量删除
     * @param ids
     */
    @Modifying
    @Transactional(timeout = 10,rollbackFor = RuntimeException.class)
    @Query("update ReportLoss v set v.isDeleted = true where v.pkReportLossId in ?1")
    void deleteBatch(List<Long> ids);

    /**
     * 分页查询未被逻辑查询删除的挂失信息数据
     * @return
     */
    @Query(value = "select * from report_loss where is_deleted =false ",nativeQuery = true)
    Page<ReportLoss> getAllReportLoss(Pageable pageable);




}