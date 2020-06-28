package com.soft1851.smart.campus.errends.service.serviceImpl;

import com.soft1851.smart.campus.errends.domain.vo.OrderVo;
import com.soft1851.smart.campus.errends.mapper.TransactionMapper;
import com.soft1851.smart.campus.errends.service.TransactionService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * @author wl
 * @ClassNameTransactionServiceImpl
 * @Description TODO
 * @Date 2020/6/26
 * @Version 1.0
 */
@Service
@Transactional(rollbackFor = RuntimeException.class)
public class TransactionServiceImpl implements TransactionService {
    @Resource
    private TransactionMapper transactionMapper;


    @Override
    public List<OrderVo> getOrderByFifteenDays() {
        List<OrderVo> dayOrder = new ArrayList<>();

        for (int i = 1; i <= 15; i++) {
            Integer order = transactionMapper.getDayOrder(i);
            LocalDate localDate = LocalDate.now().minusDays(i);
            OrderVo orderVo =OrderVo.builder().orderCount(order).time(localDate).build();
            dayOrder.add(orderVo);
        }
        return dayOrder;

    }
}
