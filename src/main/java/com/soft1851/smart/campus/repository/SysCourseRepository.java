package com.soft1851.smart.campus.repository;

import com.soft1851.smart.campus.model.entity.SysCourse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author xunmi
 * @ClassName SysCourseRepository
 * @Description 课表中的课程相关操作的类
 * @Date 2020/5/29
 * @Version 1.0
 **/
public interface SysCourseRepository extends JpaRepository<SysCourse, Long> {

    /**
     * 通过课表 id 查找出课表的信息
     *
     * @param scheduleId
     * @return 科目id   教师工号   教室id   星期几上课   上课时间段
     * 问题：@Query(value = "SELECT subject_id, user_job_number, room_id, week_day, time " +
     * "FROM sys_course " +
     * "WHERE schedule_id = ?1", nativeQuery = true)
     */
    @Query(value = "SELECT * " +
            "FROM sys_course " +
            "WHERE schedule_id = ?1", nativeQuery = true)
    List<SysCourse> getInfoOfId(Long scheduleId);


//    /**
//     * 新增课表
//     *
//     * @param sysCourse
//     * @return
//     */
//    @Transactional
//    @Query(value = "INSERT INTO sys_course(schedule_id, subject_id, user_job_number, room_id, week_day, " +
//            "time, gmt_create, gmt_modified, is_deleted, week_duration) " +
//            "VALUE (#{#sysCourse.scheduleId}, #{#sysCourse.subjectId}, #{#sysCourse.userJobNumber}, " +
//            "#{#sysCourse.roomId}, #{#sysCourse.weekDay}, #{#sysCourse.time},#{#sysCourse.gmtCreate}," +
//            "#{#sysCourse.gmtModified},#{#sysCourse.isDeleted},#{#sysCourse.weekDuration})",
//            nativeQuery = true)
//    void insertCourse(SysCourse sysCourse);

    @Modifying
    @Transactional(rollbackFor = RuntimeException.class)
    @Query(value = "UPDATE SysCourse SET roomId=:#{#sysCourse.roomId}, " +
            "time=:#{#sysCourse.time}, userJobNumber=:#{#sysCourse.userJobNumber}, " +
            "weekDay=:#{#sysCourse.weekDay},weekDuration=:#{#sysCourse.weekDuration} " +
            "WHERE pkCourseId=:#{#sysCourse.pkCourseId} ")
    int updateCourseById(@Param("sysCourse") SysCourse sysCourse);

    /**
     * 逻辑删除
     * @param sysCourse
     * @return
     */
    @Modifying
    @Transactional(rollbackFor = RuntimeException.class)
    @Query(value = "UPDATE SysCourse SET isDeleted=true " +
            "WHERE pkCourseId=?1 ")
    int updateCourseIsDeletedById(long pkId);
}
