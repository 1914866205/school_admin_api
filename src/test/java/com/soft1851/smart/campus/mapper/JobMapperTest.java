package com.soft1851.smart.campus.mapper;

import com.soft1851.smart.campus.model.dto.JobPageDto;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class JobMapperTest {

    @Resource
    private JobMapper jobMapper;
    @Test
    void jobList() {
        JobPageDto jobPageDto = JobPageDto.builder()
                .field("max")
                .pageSize(10)
                .currentPage(1)
                .build();
        jobMapper.jobList(jobPageDto).forEach(System.out::println);
    }
}