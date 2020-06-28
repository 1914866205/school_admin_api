package com.soft1851.smart.campus.service;

import com.soft1851.smart.campus.model.entity.UserAccount;
import org.junit.jupiter.api.Test;

import javax.annotation.Resource;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @Description TODO
 * @Author wf
 * @Date 2020/6/22
 * @Version 1.0
 */
@Resource
class UserAccountServiceTest {
    @Resource
    private UserAccountService userAccountService;

    @Test
    void exportTeacherInfo() {
        userAccountService.exportTeacherInfo();
    }

    @Test
    void insertUserAccount() {
        UserAccount userAccount = UserAccount.builder()
                .role("1")
                .clazzId((long)10)
                .address("江苏省-南京市-栖霞区")
                .phoneNumber("13270003526")
                .jobNumber("1802313117")
                .userName("陶永新6.22")
                .gender("男")
                .build();
        userAccountService.insertUserAccount(userAccount);
    }
}