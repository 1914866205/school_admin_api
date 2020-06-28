package com.soft1851.smart.campus.service.Impl;

import com.soft1851.smart.campus.constant.ResultCode;
import com.soft1851.smart.campus.exception.CustomException;
import com.soft1851.smart.campus.mapper.*;
import com.soft1851.smart.campus.model.dto.CourseDto;
import com.soft1851.smart.campus.model.dto.PageDto;
import com.soft1851.smart.campus.model.entity.Clazz;
import com.soft1851.smart.campus.model.entity.Schedule;
import com.soft1851.smart.campus.model.entity.SysCourse;
import com.soft1851.smart.campus.model.entity.UserAccount;
import com.soft1851.smart.campus.repository.*;
import com.soft1851.smart.campus.service.SysCourseService;
import com.soft1851.smart.campus.utils.DateUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.security.auth.Subject;
import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

/**
 * @author xunmi
 * @ClassName SysCourse
 * @Description TODO
 * @Date 2020/6/1
 * @Version 1.0
 **/
@Service
public class SysCourseServiceImpl implements SysCourseService {

    @Resource
    private SysCourseRepository sysCourseRepository;
    @Resource
    private ScheduleRepository scheduleRepository;
    @Resource
    private RoomRepository roomRepository;
    @Resource
    private SysCourseMapper sysCourseMapper;
    @Resource
    private ScheduleMapper scheduleMapper;
    @Resource
    private RoomMapper roomMapper;
    @Resource
    private ClazzRepository clazzRepository;
    @Resource
    private UserAccountMapper userAccountMapper;
    @Resource
    private SysSubjectRepository sysSubjectRepository;

    @Override
    public void increase(SysCourse sysCourse) {
        Timestamp timestamp = DateUtil.getTimestamp();
        if (sysCourse.getGmtCreate() == null) {
            sysCourse.setGmtCreate(timestamp);
        }
        if (sysCourse.getGmtModified() == null) {
            sysCourse.setGmtModified(timestamp);
        }
        sysCourse.setIsDeleted(false);
        sysCourseRepository.saveAndFlush(sysCourse);
    }

    @Override
    public List<Map<String, Object>> getAllCourses(PageDto pageDto) {
        List<Map<String, Object>> courses = sysCourseMapper.getAllSysCourse(pageDto.getCurrentPage(), pageDto.getPageSize());
        courses.forEach(course -> {
            Map<String, Object> semester = scheduleMapper.getScheduleSemesterById(Long.parseLong(course.get("schedule_id").toString()));
            String subject = sysSubjectRepository.getSubjectName(Long.parseLong(course.get("subject_id").toString()));
            System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
            System.out.println(semester.toString());
            Clazz clazz = clazzRepository.getOne(Long.parseLong(semester.get("clazz_id").toString()));
            Map<String, Object> tower = roomMapper.getRoomTowerByRoomId(Long.parseLong(course.get("room_id").toString()));
            course.put("subjectName", subject);
            course.put("semesterName", semester.get("name"));
            course.put("clazz", clazz.getName());
            course.put("clazz_id", semester.get("clazz_id"));
            course.put("semester_id", semester.get("semester_id"));
            course.put("towerName", tower.get("name").toString());
            course.put("roomName", course.get("name"));
        });
        return courses;
    }

    @Override
    public int updateCourseById(CourseDto courseDto) {
        int result = 0;
       try {
           int n = roomRepository.updateRoomTowerIdById(courseDto.getRoomId(), courseDto.getTowerId());
           int m = scheduleRepository.updateScheduleById(courseDto.getClazz(), courseDto.getSemesterName(), courseDto.getOldScheduleId());
           SysCourse course = SysCourse.builder()
                   .pkCourseId(courseDto.getPkId())
                   .roomId(courseDto.getRoomId())
                   .scheduleId(courseDto.getSemesterName())
                   .subjectId(courseDto.getOldSubjectId())
                   .time(courseDto.getTime())
                   .weekDay(courseDto.getWeekDay())
                   .weekDuration(courseDto.getWeeks())
                   .userJobNumber(courseDto.getJobNumber())
                   .build();
           result = sysCourseRepository.updateCourseById(course);
       }catch (Exception e){
           throw new CustomException("课程修改异常", ResultCode.DATA_UPDATE_ERROR);
       }
       return result;
    }

    @Override
    public int updateCourseIsDeletedById(long pkId) {
        int n = sysCourseRepository.updateCourseIsDeletedById(pkId);
        return n;
    }
}
