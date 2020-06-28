package com.soft1851.smart.campus.repository;

import com.soft1851.smart.campus.model.dto.PageDto;
import com.soft1851.smart.campus.model.dto.UpdateExaminationDto;
import com.soft1851.smart.campus.model.entity.Examination;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author xunmi
 * @ClassName ExaminationRepository
 * @Description 考务管理相关操作的类
 * @Date 2020/5/31
 * @Version 1.0
 **/
public interface ExaminationRepository extends JpaRepository<Examination, Long> {

    /**
     * 查询出所有与考务的基本信息
     *
     * @param pageDto
     * @return
     */
    @Query(value = "SELECT exa.pk_examination_id, exa.semester, exa.teacher_name, exa.start_time, \n" +
            "exa.area, exa.score, exa.type, exa.gmt_create, sub.name as subject_name, cl.name as clazz_name\n" +
            "FROM examination exa\n" +
            "LEFT JOIN sys_subject sub\n" +
            "ON exa.subject_id = sub.pk_subject_id\n" +
            "LEFT JOIN clazz cl\n" +
            "ON exa.clazz_id = cl.pk_clazz_id\n" +
            "ORDER BY exa.pk_examination_id\n" +
            "LIMIT :#{#pageDto.pageSize} OFFSET :#{#pageDto.currentPage}", nativeQuery = true)
    List<Object> selectAll(@Param("pageDto") PageDto pageDto);


    /**
     * 批量修改考务数据信息
     * @param updateExaminationDto
     * @param ids
     */
    @Transactional(rollbackFor = RuntimeException.class)
    @Modifying
    @Query(value = "update first_smart_campus.examination e set e.area=:#{#updateExaminationDto.area}," +
            "e.start_time=:#{#updateExaminationDto.startTime}," +
            "e.finish_time=:#{#updateExaminationDto.finishTime}," +
            "e.subject_id=:#{#updateExaminationDto.subjectId}," +
            "e.teacher_name=:#{#updateExaminationDto.teacherName}," +
            "e.teacher_id=:#{#updateExaminationDto.teacherId}," +
            "e.type=:#{#updateExaminationDto.type} " +
            "where e.pk_examination_id in ?1",nativeQuery = true)
    void updateStudentExamination(List<Long> ids,@Param("updateExaminationDto") UpdateExaminationDto updateExaminationDto);


    /**
     * 根据学期和学科和班级查询考务数据
     * @param semester
     * @param subjectId
     * @param clazzId
     * @return
     */
    @Query(value = "select pk_examination_id from first_smart_campus.examination where semester=?1 and subject_id=?2 and clazz_id=?3 and is_deleted=false",nativeQuery = true)
    List<Long> findExaminationsBySemesterAndSubjectId(String semester,Long subjectId,long clazzId);


    /**
     * 删除单个的教务数据
     * @param ids
     */
    @Transactional(rollbackFor = RuntimeException.class)
    @Modifying
    @Query(value = "update first_smart_campus.examination e set e.is_deleted=true where e.pk_examination_id in ?1",nativeQuery = true)
    void deletedExamination(List<Long> ids);
}
