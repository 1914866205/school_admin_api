package com.soft1851.smart.campus.errends.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.soft1851.smart.campus.errends.domain.entity.Transaction;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author wl
 * @ClassNameTransactionMapper
 * @Description 交易模块
 * @Date 2020/6/9
 * @Version 1.0
 */
public interface TransactionMapper extends BaseMapper<Transaction> {
    /**
     * 获取每天的订单量 一共十五天
     * @param day
     * @return
     */
    @Select("SELECT COUNT(*) as orderCount FROM `transaction` WHERE DATEDIFF(transaction_end,NOW())=-#{day}")
   Integer getDayOrder(Integer day);
}
