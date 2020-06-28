package com.soft1851.smart.campus.repository;

import com.soft1851.smart.campus.model.entity.SysSemester;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author xunmi
 * @ClassName SemesterRepository
 * @Description 学期相关操作的类
 * @Date 2020/6/1
 * @Version 1.0
 **/
public interface SysSemesterRepository extends JpaRepository<SysSemester, Long> {

    /**
     * 修改学期命和周次数的方法
     *
     * @param sysSemester
     */
    @Transactional(rollbackFor = RuntimeException.class)
    @Modifying
    @Query(value = "UPDATE sys_Semester SET name=:#{#sysSemester.name}," +
            "week_count=:#{#sysSemester.weekCount} WHERE pk_semester_id=:#{#sysSemester.pkSemesterId}",
            nativeQuery = true)
    void updateSemesterById(@Param("sysSemester") SysSemester sysSemester);

    /**
     * 逻辑删除
     *
     * @param sysSemester
     */
    @Transactional(rollbackFor = RuntimeException.class)
    @Modifying
    @Query(value = "UPDATE sys_Semester SET is_deleted=true " +
            "WHERE pk_semester_id=:#{#sysSemester.pkSemesterId}",
            nativeQuery = true)
    void updateIsDeletedById(@Param("sysSemester") SysSemester sysSemester);

    /**
     * 查询所有未删除的学期信息
     * @param isDeleted
     * @return
     */
    List<SysSemester> getSysSemesterByIsDeleted(boolean isDeleted);
}
