package com.soft1851.smart.campus.repository;

import com.soft1851.smart.campus.model.dto.UpdateSysStatementDto;
import com.soft1851.smart.campus.model.entity.SysStatement;
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
public interface SysStatementRepository extends JpaRepository<SysStatement, Long> {
    /**
     * 根据id查找声明
     * @param id
     * @return
     */
    SysStatement findSysStatementByPkStatementId(Long id);

    /**
     * 根据主键id删除数据
     * @param pkStatementId
     */
    @Transactional(rollbackFor = RuntimeException.class)
    @Modifying
    @Query("delete from SysStatement where pkStatementId = ?1")
    void deleteByPkStatementId(Long pkStatementId);

    /**
     * 批量删除
     * @param ids
     */
    @Modifying
    @Transactional(timeout = 10,rollbackFor = RuntimeException.class)
    @Query("delete from SysStatement s where s.pkStatementId in (?1)")
    void deleteBatch(List<Long> ids);


    /**
     * 修改声明数据信息
     * @param updateSysStatementDto
     */
    @Transactional(rollbackFor = RuntimeException.class)
    @Modifying
    @Query(value = "UPDATE first_smart_campus.sys_statement SET statement_title=:#{#updateSysStatementDto.statementTitle}," +
            "statement_type=:#{#updateSysStatementDto.statementType}," +
            "statement_content=:#{#updateSysStatementDto.statementContent} " +
            "WHERE pk_statement_id=:#{#updateSysStatementDto.pkStatementId}",nativeQuery = true)
    void updateSysStatement(@Param("updateSysStatementDto")UpdateSysStatementDto updateSysStatementDto);

    /**
     * 单个逻辑删除
     * @param pkSysStatementId
     */
    @Modifying
    @Transactional(rollbackFor = RuntimeException.class)
    @Query(value = "UPDATE first_smart_campus.sys_statement SET is_deleted = true WHERE pk_statement_id =?1",nativeQuery = true)
    void deleteSysStatement(Long pkSysStatementId);

    /**
     * 批量逻辑删除
     * @param ids
     * @return
     */
    @Modifying
    @Transactional(rollbackFor = RuntimeException.class)
    @Query(value = "update first_smart_campus.sys_statement s set s.is_deleted = true where s.pk_statement_id in ?1",nativeQuery = true)
    int deleteBatchByPkStatementId(List<Long> ids);


    /**
     * 分页查询所有声明数据
     * @param pageable
     * @return
     */
    @Query(value = "select * from first_smart_campus.sys_statement where is_deleted = false",nativeQuery = true)
    Page<SysStatement> getAllSysStatement(Pageable pageable);
}
