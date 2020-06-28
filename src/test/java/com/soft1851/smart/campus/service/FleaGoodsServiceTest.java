package com.soft1851.smart.campus.service;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @Author yhChen
 * @Description
 * @Date 2020/6/24
 */
@Slf4j
@SpringBootTest
class FleaGoodsServiceTest {
    @Resource
    private FleaGoodsService fleaGoodsService;

    @Test
    void findTopFiveMark() {
        System.out.println(fleaGoodsService.findTopFiveMark());
    }
}