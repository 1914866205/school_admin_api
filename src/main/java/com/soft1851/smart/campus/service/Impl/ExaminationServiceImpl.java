package com.soft1851.smart.campus.service.Impl;

import com.soft1851.smart.campus.constant.ResponseResult;
import com.soft1851.smart.campus.constant.ResultCode;
import com.soft1851.smart.campus.mapper.ExaminationMapper;
import com.soft1851.smart.campus.mapper.UserAccountMapper;
import com.soft1851.smart.campus.model.dto.UpdateExaminationDto;
import com.soft1851.smart.campus.model.dto.UpdateNewExaminationDto;
import com.soft1851.smart.campus.model.entity.Examination;
import com.soft1851.smart.campus.model.vo.ExamVo;
import com.soft1851.smart.campus.model.vo.ExaminationStudentVo;
import com.soft1851.smart.campus.model.vo.InsertExamVo;
import com.soft1851.smart.campus.repository.ExaminationRepository;
import com.soft1851.smart.campus.service.ExaminationService;
import com.soft1851.smart.campus.utils.DateUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @author xunmi
 * @ClassName ExaminationServiceImpl
 * @Description TODO
 * @Date 2020/5/31
 * @Version 1.0
 **/
@Service
public class ExaminationServiceImpl implements ExaminationService {

    @Resource
    private ExaminationRepository examinationRepository;
    @Resource
    private UserAccountMapper userAccountMapper;
    @Resource
    private ExaminationMapper examinationMapper;

    /**
     * 修改考务数据：地址/开始时间/结束时间/学科id/监考老师名/监考老师id/类型
     *
     * @param updateNewExaminationDto
     * @return
     */
    @Override
    public ResponseResult updateInfo(UpdateNewExaminationDto updateNewExaminationDto) {
        Timestamp startTime = Timestamp.valueOf(updateNewExaminationDto.getStartTime());
        Timestamp finishTime = Timestamp.valueOf(updateNewExaminationDto.getFinishTime());
        //subjectId分修改之前的和修改后的 旧的subjectId的作用是用来查找需要修改的数据 新的用来修改字段
        UpdateExaminationDto updateExaminationDto = UpdateExaminationDto.builder()
                .area(updateNewExaminationDto.getArea())
                .startTime(startTime)
                .finishTime(finishTime)
                .subjectId(updateNewExaminationDto.getSubjectId())
                .teacherName(updateNewExaminationDto.getTeacherName())
                .teacherId(updateNewExaminationDto.getTeacherId())
                .type(updateNewExaminationDto.getType())
                .build();
        //查询需要修改的考务数据
        List<Long> ids = examinationRepository.findExaminationsBySemesterAndSubjectId(updateNewExaminationDto.getSemester(), updateNewExaminationDto.getOldSubjectId(), updateNewExaminationDto.getClazzId());
        if (ids.size() != 0) {
            //进行批量修改操作
            examinationRepository.updateStudentExamination(ids, updateExaminationDto);
            return ResponseResult.success("修改成功！");
        } else {
            return ResponseResult.failure(ResultCode.RESULT_CODE_DATA_NONE);
        }
    }

    /**
     * 新增考务
     *
     * @param insertExamVo
     * @return
     */
    @Override
    public ResponseResult increaseInfo(InsertExamVo insertExamVo) {
        // 组成考务list
        List<Examination> examinationList = new ArrayList<>();
        // 通过班级id查询所属班级的学生id
        List<String> studentIds = null;
        try {
            studentIds = userAccountMapper.findJobNumberByClazzId(insertExamVo.getClazzId());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        //****************判断此班级是否已经考过此科目*******************************
        List<Examination> numberExamination1 = examinationMapper.selectExaminationByClazzIdAndSubject(insertExamVo.getClazzId(), insertExamVo.getSubjectId(), insertExamVo.getSemester());
        List<Examination> numberExamination2 = examinationMapper.selectExaminationByClazzIdAndSubject1(insertExamVo.getClazzId(), insertExamVo.getSubjectId(), insertExamVo.getSemester());
        boolean a = (numberExamination1.size() == 0) && (numberExamination2.size() == 0);
        System.out.println("**********"+a+"***********");

        //****************判断该班级同时间是否还有其他考试****************************
        //判断该班级同时间段是否存在其他的考试 时间冲突问题
        //1.首先根据班级id和学期id查询出该班级本学期的所有课程
        List<Examination> examinationAllList = examinationMapper.getExaminationBySemesterAndClazzId(insertExamVo.getSemester(), insertExamVo.getClazzId());
        // 过滤重复的 保留每个学科的一条数据
        Set<Examination> examinations = new TreeSet<>((o1, o2) -> o1.getSubjectId().compareTo(o2.getSubjectId()));
        examinations.addAll(examinationAllList);
        // 存储过滤后的数据
        List<Examination> examinationAllList1 = new ArrayList<Examination>(examinations);

        Timestamp startTime = Timestamp.valueOf(insertExamVo.getStartTime());
        Timestamp finishTime = Timestamp.valueOf(insertExamVo.getFinishTime());
        Boolean isClazzInsert = DateUtil.getTimeCompare(startTime, finishTime, examinationAllList1);


        //****************判断同时间该教师是否有其他监考场次****************************
        List<Examination> examinationTeacherList = examinationMapper.getExaminationsByTeacherId(insertExamVo.getTeacherId(), insertExamVo.getSemester());
        //多条件过滤   将收集的结果转换为另一种类型: collectingAndThen  根据班级id和学科id
        List<Examination> examinationTeacherList1 = examinationTeacherList.stream().collect(
                Collectors.collectingAndThen(Collectors.toCollection(() -> new TreeSet<>(
                        Comparator.comparing(o -> o.getSubjectId() + ";" + o.getClazzId())
                )), ArrayList::new)
        );
        Boolean isTeacherInsert = DateUtil.getTimeCompare(startTime, finishTime, examinationTeacherList1);

        //1.判断该班级是否考过此科目
        if ((numberExamination1.size() == 0) && (numberExamination2.size() == 0)) {
            // 判断该班级同时间是否还有其他考试
            if (isClazzInsert) {
                if (isTeacherInsert) {
                    //遍历学生id
                    assert studentIds != null;
                    for (String studentId : studentIds) {
                        Examination examination1 = Examination.builder()
                                //查询所有学期下拉框选择学期
                                .semester(insertExamVo.getSemester())
                                //查询所有学科下拉框选择科目（模糊查询）
                                .subjectId(insertExamVo.getSubjectId())
                                //查询所有老师下拉框选择老师（模糊查询）
                                .teacherName(insertExamVo.getTeacherName())
                                .teacherId(insertExamVo.getTeacherId())
                                .type(insertExamVo.getType())
                                //考试开始时间
                                .startTime(startTime)
                                //考试结束时间
                                .finishTime(finishTime)
                                //查询楼栋、房间名
                                .area(insertExamVo.getArea())
                                //考试总分
                                .score(insertExamVo.getScore())
                                //下拉框查询所有班级（模糊查询）
                                .clazzId(insertExamVo.getClazzId())
                                .isJoin(false)
                                .jobNumber(studentId)
                                .gmtCreate(Timestamp.valueOf(LocalDateTime.now()))
                                .gmtModified(Timestamp.valueOf(LocalDateTime.now()))
                                .isDeleted(false)
                                .build();
                        examinationList.add(examination1);
                    }
                    examinationRepository.saveAll(examinationList);
                    return ResponseResult.success("新增考务成功");
                } else {
                    return ResponseResult.failure(ResultCode.TEACHER_EXAMINATION_REPETITION);
                }

            } else {
                return ResponseResult.failure(ResultCode.STUDENT_EXAMINATION_REPETITION);
            }

        } else {
            return ResponseResult.failure(ResultCode.EXAMINATION_REPETITION);
        }

    }

    /**
     * 查询所有教务数据
     *
     * @return
     */
    @Override
    public ResponseResult selectAllExamination() {
        List<ExamVo> examinationList = examinationMapper.getAllExamination();
        List<ExamVo> examinationList1 = examinationList.stream().collect(
                Collectors.collectingAndThen(Collectors.toCollection(() -> new TreeSet<>(
                        Comparator.comparing(o -> o.getSubjectId() + ";" + o.getClazzId() + ";" + o.getSemester())
                )), ArrayList::new)
        );
        return ResponseResult.success(examinationList1);
    }


    /**
     * 删除单条教务数据(包含所有考务内的学生数据全部删除）
     *
     * @param updateNewExaminationDto
     * @return
     */
    @Override
    public ResponseResult deletedExamination(UpdateNewExaminationDto updateNewExaminationDto) {
        List<Long> examinationList = examinationRepository.findExaminationsBySemesterAndSubjectId(updateNewExaminationDto.getSemester(), updateNewExaminationDto.getSubjectId(), updateNewExaminationDto.getClazzId());
        if (examinationList.size() != 0) {
            examinationRepository.deletedExamination(examinationList);
            return ResponseResult.success("成功删除教务");
        } else {
            return ResponseResult.failure(ResultCode.RESULT_CODE_DATA_NONE);
        }
    }

    /**
     * 查询某个教务下面的学生数据
     * @param updateNewExaminationDto
     * @return
     */
    @Override
    public ResponseResult selectStudentInExamination(UpdateNewExaminationDto updateNewExaminationDto) {
        List<ExaminationStudentVo> examinationStudentVos = examinationMapper.getAllExaminationStudents(updateNewExaminationDto.getSubjectId(),updateNewExaminationDto.getClazzId(),updateNewExaminationDto.getSemester());
        return ResponseResult.success(examinationStudentVos);
    }


}
