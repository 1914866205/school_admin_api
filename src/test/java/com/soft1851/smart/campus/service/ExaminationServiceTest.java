package com.soft1851.smart.campus.service;

import com.soft1851.smart.campus.constant.ResponseResult;
import com.soft1851.smart.campus.model.vo.InsertExamVo;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

@SpringBootTest
class ExaminationServiceTest {

    @Resource
    private ExaminationService examinationService;

    @Test
    void increaseInfo() {
        String startTime = "2020-06-28 06:30:00";

        String finishTime = "2020-06-28 08:40:00";

        InsertExamVo examination = InsertExamVo.builder()
                .semester("2012-2013学年第一学期")
                .subjectId((long)19)
                .teacherName("贾利娟")
                .teacherId("13")
                .type("线上考试")
                .startTime(startTime)
                .finishTime(finishTime)
                .area("教四404")
                .score(150)
                .clazzId((long)2)
                .build();
        ResponseResult responseResult = examinationService.increaseInfo(examination);
        System.out.println(responseResult);
    }

}