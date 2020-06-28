package com.soft1851.smart.campus.service;

import com.soft1851.smart.campus.model.dto.CompanyDto;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CompanyServiceTest {

    @Resource
    private CompanyService companyService;
    @Test
    void update() {
        CompanyDto companyDto = CompanyDto.builder()
                .id(30L)
                .name("fdg")
                .tag("fdd")
                .logo("dfg")
                .workers(122)
                .type("sf")
                .description("dfgadfg")
                .workingTime("fadf")
                .workingStatus("dsfdfad")
                .jobNumbers(2)
                .vacation("sdffa")
                .address("sfsd")
                .longitude("")
                .latitude("")
                .build();
        System.out.println(companyService.update(companyDto));
    }
}