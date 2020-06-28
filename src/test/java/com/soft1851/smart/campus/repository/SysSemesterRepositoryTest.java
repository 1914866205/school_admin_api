package com.soft1851.smart.campus.repository;

import com.soft1851.smart.campus.model.entity.SysSemester;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class SysSemesterRepositoryTest {

    @Resource
    private SysSemesterRepository sysSemesterRepository;
    @Test
    void getSysSemesterByIsDeleted() {
        List<SysSemester> sysSemesters = sysSemesterRepository.getSysSemesterByIsDeleted(false);
        System.out.println(sysSemesters);
    }
}