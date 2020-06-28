package com.soft1851.smart.campus.errends.repository;

import com.soft1851.smart.campus.errends.domain.entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author wl
 * @ClassNameTransactionRepository
 * @Description TODO
 * @Date 2020/6/9
 * @Version 1.0
 */
public interface TransactionRepository extends JpaRepository<Transaction,String> {
}
