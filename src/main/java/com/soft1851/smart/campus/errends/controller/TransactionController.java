package com.soft1851.smart.campus.errends.controller;

import com.soft1851.smart.campus.errends.domain.vo.OrderVo;
import com.soft1851.smart.campus.errends.service.TransactionService;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author wl
 * @ClassNameTransactionController
 * @Description TODO
 * @Date 2020/6/26
 * @Version 1.0
 */
@RestController
@RequestMapping(value = "errends")
@Api(value = "deliveryOrder", tags = "订单管理")
public class TransactionController {
    @Resource
    private TransactionService transactionService;

    @PostMapping(value = "/getFifteenOrder")
    public List<OrderVo> getFifteenOrder() {

        return transactionService.getOrderByFifteenDays();
    }
}
