package com.soft1851.smart.campus.repository;

import com.soft1851.smart.campus.model.dto.UpdateExaminationDto;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@SpringBootTest
class ExaminationRepositoryTest {
    @Resource
    private ExaminationRepository examinationRepository;

    @Test
    void updateStudentExamination() {
        UpdateExaminationDto updateExaminationDto = UpdateExaminationDto.builder()
                .area("教四110")
                .startTime(Timestamp.valueOf(LocalDateTime.now()))
                .finishTime(Timestamp.valueOf(LocalDateTime.now()))
                .subjectId((long)20)
                .teacherName("梁")
                .teacherId("111")
                .type("测试类型")
                .build();
        List<Long> ids = new ArrayList<>();
        ids.add((long) 122);
        ids.add((long) 123);
        examinationRepository.updateStudentExamination(ids,updateExaminationDto);
    }

    @Test
    void findExaminationsBySemesterAndSubjectId() {
        List<Long> examinationList = examinationRepository.findExaminationsBySemesterAndSubjectId("2012-2013学年第一学期",(long)19,2);
        System.out.println(examinationList);

    }

    @Test
    void deletedExamination() {
        List<Long> examinationList = examinationRepository.findExaminationsBySemesterAndSubjectId("2012-2013学年第一学期",(long)20, 2);
        examinationRepository.deletedExamination(examinationList);
    }
}