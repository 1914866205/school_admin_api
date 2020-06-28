package com.soft1851.smart.campus.mapper;

import com.soft1851.smart.campus.model.entity.Examination;
import com.soft1851.smart.campus.model.vo.ExamVo;
import com.soft1851.smart.campus.model.vo.ExaminationStudentVo;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.sql.Timestamp;
import java.util.*;
import java.util.stream.Collectors;

@SpringBootTest
class ExaminationMapperTest {

    @Resource
    private ExaminationMapper examinationMapper;

    @Test
    void selectExaminationByClazzIdAndSubject() {
        List<Examination> examinationList = examinationMapper.selectExaminationByClazzIdAndSubject1((long) 2, (long) 2, "2012-2013学年第一学期");
        System.out.println(examinationList.size());
    }

    @Test
    void selectExaminationByTime() {
        // 根据班级id和学期id查询出该班级本学期所有的课程
        List<Examination> examinationList = examinationMapper.getExaminationBySemesterAndClazzId("2012-2013学年第一学期", (long) 2);
        // 过滤重复的 保留每个学科的一条数据
        Set<Examination> examinations = new TreeSet<>((o1, o2) -> o1.getSubjectId().compareTo(o2.getSubjectId()));
        examinations.addAll(examinationList);
        // 存储过滤后的数据
        List<Examination> examinationList1 = new ArrayList<Examination>(examinations);
//        System.out.println(examinationList1);
//        Boolean isInsert1 = DateUtil.getTimeCompare("2020-06-28 05:20:20","2020-06-28 06:20:20",examinationList1);
//        System.out.println(isInsert1);
//        Boolean isInsert2 = DateUtil.getTimeCompare("2020-06-28 00:20:20","2020-06-28 00:40:20",examinationList1);
//        System.out.println(isInsert2);
//        Boolean isInsert3 = DateUtil.getTimeCompare("2020-06-28 04:20:20","2020-06-28 05:40:20",examinationList1);
//        System.out.println(isInsert3);

    }

    @Test
    void timeTest() {
        Timestamp a = Timestamp.valueOf("2020-06-04 05:56:20");
        Timestamp b = Timestamp.valueOf("2020-06-04 05:56:20");
        if (b.before(a)) {
            System.out.println("b时间比a早");
        } else if (b.after(a)) {
            System.out.println("b时间比a迟");
        } else {
            System.out.println("时间相同");
        }

    }

    @Test
    void getExaminationsByTeacherId() {
        List<Examination> examinationList = examinationMapper.getExaminationsByTeacherId("12", "2012-2013学年第一学期");
        //多条件过滤   将收集的结果转换为另一种类型: collectingAndThen  根据班级id和学科id
        List<Examination> examinationList1 = examinationList.stream().collect(
                Collectors.collectingAndThen(Collectors.toCollection(() -> new TreeSet<>(
                        Comparator.comparing(o -> o.getSubjectId() + ";" + o.getClazzId())
                )), ArrayList::new)
        );
        System.out.println(examinationList1);

    }

    @Test
    void getAllExamination() {
        List<ExamVo> examinationList = examinationMapper.getAllExamination();
        List<ExamVo> examinationList1 = examinationList.stream().collect(
                Collectors.collectingAndThen(Collectors.toCollection(() -> new TreeSet<>(
                        Comparator.comparing(o -> o.getSubjectId() + ";" + o.getClazzId() + ";" + o.getSemester())
                )), ArrayList::new)
        );
        for (ExamVo examination : examinationList1) {
            System.out.println(examination);
        }
    }

    @Test
    void getAllExaminationStudents() {
        List<ExaminationStudentVo> examinationStudentVos = examinationMapper.getAllExaminationStudents((long)2,(long)2,"2012-2013学年第一学期");
        System.out.println(examinationStudentVos);
    }
}
