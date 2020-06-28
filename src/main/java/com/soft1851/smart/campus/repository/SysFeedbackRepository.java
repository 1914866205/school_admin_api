package com.soft1851.smart.campus.repository;

import com.soft1851.smart.campus.model.dto.UpdateSysFeedbackDto;
import com.soft1851.smart.campus.model.entity.SysFeedback;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author Tao
 */
public interface SysFeedbackRepository extends JpaRepository<SysFeedback, Long> {
    /**
     * 根据id查询反馈数据
     * @param id
     * @return
     */
    SysFeedback findSysFeedbackByPkFeedbackId(Long id);

    /**
     * 根据主键id删除反馈数据
     * @param pkFeedbackId
     */
    @Transactional(rollbackFor = RuntimeException.class)
    @Modifying
    @Query("delete from SysFeedback where pkFeedbackId = ?1")
    void deleteByPkFeedbackId(Long pkFeedbackId);

    /**
     * 批量删除
     * @param ids
     */
    @Modifying
    @Transactional(timeout = 10,rollbackFor = RuntimeException.class)
    @Query("delete from SysFeedback f where f.pkFeedbackId in (?1)")
    void deleteBatch(List<Long> ids);

    /**
     * 修改反馈信息
     * @param updateSysFeedbackDto
     */
    @Transactional(rollbackFor = RuntimeException.class)
    @Modifying
    @Query(value = "UPDATE first_smart_campus.sys_feedback SET " +
            "is_handled =:#{#updateSysFeedbackDto.isHandled} WHERE pk_feedback_id=:#{#updateSysFeedbackDto.pkFeedbackId}",nativeQuery = true)
    void updateSysFeedback(@Param("updateSysFeedbackDto") UpdateSysFeedbackDto updateSysFeedbackDto);


    /**
     * 逻辑删除
     * @param pkFeedbackId
     */
    @Modifying
    @Transactional(rollbackFor = RuntimeException.class)
    @Query(value = "UPDATE first_smart_campus.sys_feedback SET is_deleted = true WHERE pk_feedback_id=?1",nativeQuery = true)
    void deleteSysFeedback(Long pkFeedbackId);

    /**
     * 批量逻辑删除
     * @param ids
     * @return
     */
    @Modifying
    @Transactional(rollbackFor = RuntimeException.class)
    @Query(value = "update first_smart_campus.sys_feedback f set f.is_deleted = true where f.pk_feedback_id in ?1",nativeQuery = true)
    int deleteBatchByPkFeedbackId(List<Long> ids);

    /**
     * 分页查询所有反馈数据
     * @param pageable
     * @return
     */
    @Query(value = "select * from first_smart_campus.sys_feedback where is_deleted = false",nativeQuery = true)
    Page<SysFeedback> getAllSysFeedback(Pageable pageable);
}
