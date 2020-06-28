package com.soft1851.smart.campus.service.Impl;

import com.alibaba.fastjson.JSONArray;
import com.soft1851.smart.campus.model.dto.SchedulesDto;
import com.soft1851.smart.campus.model.entity.Schedule;
import com.soft1851.smart.campus.model.entity.SysCourse;
import com.soft1851.smart.campus.model.vo.CourseVo;
import com.soft1851.smart.campus.model.vo.SchedulesVo;
import com.soft1851.smart.campus.repository.*;
import com.soft1851.smart.campus.service.ScheduleService;
import com.soft1851.smart.campus.utils.DateUtil;
import org.apache.tomcat.jni.Local;
import org.apache.tomcat.jni.Time;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

/**
 * @author xunmi
 * @ClassName ScheduleServiceImpl
 * @Description TODO
 * @Date 2020/5/30
 * @Version 1.0
 **/
@Service
public class ScheduleServiceImpl implements ScheduleService {

    @Resource
    private ScheduleRepository scheduleRepository;

    @Resource
    private SysCourseRepository sysCourseRepository;

    @Resource
    private SysSubjectRepository sysSubjectRepository;

    @Resource
    private RoomRepository roomRepository;

    @Resource
    private UserAccountRepository userAccountRepository;


    @Override
    public List<CourseVo> getScheduleInfo(Long semesterId, Long clazzId, Integer week) {
        List<CourseVo> list = new ArrayList<>(10);
        // 第一步：通过指定的学期、班级、周次查找出课程表的 id
        Long scheduleId = scheduleRepository.getId(semesterId, clazzId, week);
        // 第二步：通过课程表 id 查找出对应课程、老师、教室的 id值
        List<SysCourse> infoOfId = sysCourseRepository.getInfoOfId(scheduleId);
        // 第三步：遍历集合，分别将各类 id 取出并进行查询
        return createVo(infoOfId);
    }

    @Override
    public List<CourseVo> getScheduleInfoById(Long scheduleId) {
        List<SysCourse> infoOfId = sysCourseRepository.getInfoOfId(scheduleId);
        return createVo(infoOfId);
    }

    @Override
    public void increase(SchedulesDto schedulesDto) {
        long clazzId = schedulesDto.getClazzId();
        long semesterId = schedulesDto.getSemesterId();
        long n = 123;
        JSONArray courses = JSONArray.parseArray(schedulesDto.getCourses());
        List<SchedulesVo> coursesList = courses.toJavaList(SchedulesVo.class);
        JSONArray weeks = JSONArray.parseArray(schedulesDto.getWeeks());
        System.out.println(coursesList);
        for (Object week : weeks) {
            long scheduleId = n++;
            Schedule schedule = Schedule.builder()
                    .pkSchoolTimetableId(scheduleId)
                    .gmtCreate(Timestamp.valueOf(LocalDateTime.now()))
                    .gmtModified(Timestamp.valueOf(LocalDateTime.now()))
                    .isDeleted(false)
                    .clazzId(schedulesDto.getClazzId())
                    .name("")
                    .semesterId(schedulesDto.getSemesterId())
                    .week(Integer.parseInt(week.toString()))
                    .build();
            scheduleRepository.save(schedule);
            for (SchedulesVo cours : coursesList) {
                SysCourse course = SysCourse.builder()
                        .gmtCreate(Timestamp.valueOf(LocalDateTime.now()))
                        .gmtModified(Timestamp.valueOf(LocalDateTime.now()))
                        .isDeleted(false)
                        .scheduleId(scheduleId)
                        .time(cours.getTime())
                        .weekDay(cours.getWeekDay())
                        .weekDuration("")
                        .userJobNumber("")
                        .roomId(cours.getRoomId())
                        .weekDuration(String.valueOf(week))
                        .subjectId(cours.getCourseId())
                        .userJobNumber(cours.getTeacherId())
                        .build();
                sysCourseRepository.save(course);
            }
        }
    }

    /**
     * 构造 Vo 对象并返回
     *
     * @param infoOfId
     * @return
     */
    private List<CourseVo> createVo(List<SysCourse> infoOfId) {
        List<CourseVo> list = new ArrayList<>(10);
        // 第三步：遍历集合，分别将各类 id 取出并进行查询
        infoOfId.forEach((item) -> {
            CourseVo course = new CourseVo();
            // 先获取科目信息
            course.setSubjectName(sysSubjectRepository.getSubjectName(item.getSubjectId()));
            // 获取科目背景色
            course.setBackgroundColor(sysSubjectRepository.getSubjectBackgroundColor(item.getSubjectId()));
            // 获取教室信息
            course.setRoomName(roomRepository.getRoomName((long) item.getRoomId()));
            // 获取教师信息
            course.setTeacherName(userAccountRepository.getUserName(item.getUserJobNumber()));
            // 获取节次
            course.setTime(item.getTime());
            // 获取上课的时间
            course.setWeekDay(item.getWeekDay());
            // 获取周次
            course.setWeekDuration(item.getWeekDuration());
            list.add(course);
        });
        return list;
    }
}
