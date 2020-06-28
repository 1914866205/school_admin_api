package com.soft1851.smart.campus.repository;

import com.soft1851.smart.campus.model.entity.AddressBook;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * @Description TODO
 * @Author wf
 * @Date 2020/6/2
 * @Version 1.0
 */
public interface AddressBookRepository extends JpaRepository<AddressBook, Long> {

    /**
     * 根据phoneNumber查询通讯录
     * @param userId
     * @return
     */
    @Query("SELECT u, m.address, m.gender FROM AddressBook u, UserAccount m " +
            "WHERE u.userId=?1 AND u.phoneNumber=m.phoneNumber ")
    List<AddressBook> getAddressBookByUserId(String userId);
}
