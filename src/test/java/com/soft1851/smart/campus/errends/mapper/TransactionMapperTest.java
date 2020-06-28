package com.soft1851.smart.campus.errends.mapper;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

@SpringBootTest

public class TransactionMapperTest {
@Resource
private TransactionMapper transactionMapper;
    @Test
    public void getDayOrder() {
        System.out.println(transactionMapper.getDayOrder(1));

    }
}