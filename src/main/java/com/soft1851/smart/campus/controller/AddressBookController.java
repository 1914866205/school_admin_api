package com.soft1851.smart.campus.controller;

import com.soft1851.smart.campus.model.dto.QueryDto;
import com.soft1851.smart.campus.model.entity.AddressBook;
import com.soft1851.smart.campus.service.AddressBookService;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Description TODO
 * @Author wf
 * @Date 2020/6/2
 * @Version 1.0
 */
@RestController
@RequestMapping(value = "addressBook")
@Api(value = "AddressBookController", tags = "通讯录管理接口")
public class AddressBookController {
    @Resource
    private AddressBookService addressBookService;

    @PostMapping(value = "/list/userId")
    public List<AddressBook> getAddressByUserId(@RequestBody QueryDto queryDto) {
        return addressBookService.getAddressBookByUserId(queryDto.getField().toString());
    }
}
