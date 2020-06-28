package com.soft1851.smart.campus.errends.service.serviceImpl;

import com.soft1851.smart.campus.errends.domain.vo.OrderVo;
import com.soft1851.smart.campus.errends.service.TransactionService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class TransactionServiceImplTest {
@Resource
private TransactionService transactionService;
    @Test
    void getOrderByFifteenDays() {
        List<OrderVo> orderByFifteenDays = transactionService.getOrderByFifteenDays();
        System.out.println(orderByFifteenDays);
    }
}