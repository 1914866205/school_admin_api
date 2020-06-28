package com.soft1851.smart.campus.repository;

import com.soft1851.smart.campus.model.entity.InfoType;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class InfoTypeRepositoryTest {

    @Resource
    private InfoTypeRepository infoTypeRepository ;
    @Test
    void findByPkInfoTypeId() {
        InfoType infoType = infoTypeRepository.findByPkInfoTypeId((long)24);
        System.out.println(infoType);
    }
}