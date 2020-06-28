package com.soft1851.smart.campus.repository;

import com.soft1851.smart.campus.model.entity.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author xunmi
 * @ClassName ScheduleRepository
 * @Description 课程表相关操作的类
 * @Date 2020/5/29
 * @Version 1.0
 **/
public interface ScheduleRepository extends JpaRepository<Schedule, Long> {

    /**
     * 通过指定 学期 班级 周次 查找出课表 id
     *
     * @param semesterId
     * @param clazzId
     * @param week
     * @return
     */
    @Query(value = "SELECT pk_school_timetable_id FROM schedule " +
            "WHERE semester_id = ?1 AND clazz_id = ?2 AND week = ?3 ", nativeQuery = true)
    Long getId(Long semesterId, Long clazzId, Integer week);

    /**
     * 根据id修改班级和学期
     * @return
     */
    @Modifying
    @Transactional(rollbackFor = RuntimeException.class)
    @Query(value = "UPDATE Schedule SET clazzId=?1, semesterId=?2 " +
            "WHERE pkSchoolTimetableId=?3 ")
    int updateScheduleById(long clazzId, long semesterId, long id);
}
