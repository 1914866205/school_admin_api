package com.soft1851.smart.campus.repository;

import com.soft1851.smart.campus.model.entity.SysBorrow;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.sql.Timestamp;
import java.time.LocalDateTime;

@SpringBootTest
class SysBorrowRepositoryTest {

    @Resource
    private SysBorrowRepository sysBorrowRepository;

    @Test
    void setIsDeletedByPkBorrowId() {
        Timestamp t = Timestamp.valueOf(LocalDateTime.now());
        sysBorrowRepository.setIsReturn((long)2,t);
    }

    @Test
    void findByPkInfoTypeId() {
        SysBorrow sysBorrow = sysBorrowRepository.findByPkBorrowId((long)1);
        System.out.println(sysBorrow);
    }
}