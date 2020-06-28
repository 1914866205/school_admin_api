package com.soft1851.smart.campus.repository;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @Description TODO
 * @Author wf
 * @Date 2020/6/19
 * @Version 1.0
 */
@SpringBootTest
class UserAccountRepositoryTest {
    @Resource
    private UserAccountRepository userAccountRepository;

    @Test
    void updateClazzIdById() {
        List<String> list = new ArrayList<>();
        list.add("11");
        list.add("39");
        userAccountRepository.updateClazzIdById(1, list);
    }

    @Test
    void findAll() {
        System.out.println(userAccountRepository.findAll());
    }
}