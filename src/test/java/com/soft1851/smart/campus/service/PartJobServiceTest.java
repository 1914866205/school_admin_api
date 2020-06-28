package com.soft1851.smart.campus.service;

import com.soft1851.smart.campus.model.dto.JobDto;
import com.soft1851.smart.campus.model.dto.JobPageDto;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;


@SpringBootTest
class PartJobServiceTest {

    @Resource
    private PartJobService partJobService;

    @Test
    void findList() {
        JobPageDto jobPageDto = JobPageDto
                .builder()
                .currentPage(1)
                .pageSize(2)
                .build();
        partJobService.findList(jobPageDto).forEach(System.out::println);
    }

//    @Test
//    void delete() {
//        JobDto jobDto = JobDto.builder()
//                .id(90L)
//                .toId(0L)
//                .build();
//        System.out.println(partJobService.delete(jobDto));
//    }
}