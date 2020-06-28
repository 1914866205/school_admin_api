package com.soft1851.smart.campus.service;

import com.soft1851.smart.campus.model.entity.AddressBook;

import java.util.List;

/**
 * @Description TODO
 * @Author wf
 * @Date 2020/6/2
 * @Version 1.0
 */
public interface AddressBookService {

    /**
     * 根据用户id获取通讯录信息
     * @param userId
     * @return
     */
    List<AddressBook> getAddressBookByUserId(String userId);
}
