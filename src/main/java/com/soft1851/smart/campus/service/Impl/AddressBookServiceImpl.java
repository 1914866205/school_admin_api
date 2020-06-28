package com.soft1851.smart.campus.service.Impl;

import com.soft1851.smart.campus.model.entity.AddressBook;
import com.soft1851.smart.campus.repository.AddressBookRepository;
import com.soft1851.smart.campus.service.AddressBookService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Description TODO
 * @Author wf
 * @Date 2020/6/2
 * @Version 1.0
 */
@Service
public class AddressBookServiceImpl implements AddressBookService {
    @Resource
    private AddressBookRepository addressBookRepository;

    @Override
    public List<AddressBook> getAddressBookByUserId(String userId) {
        return addressBookRepository.getAddressBookByUserId(userId);
    }
}
