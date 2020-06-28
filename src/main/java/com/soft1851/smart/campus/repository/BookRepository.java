package com.soft1851.smart.campus.repository;

import com.soft1851.smart.campus.model.entity.SysBook;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @Description 图书
 * @Author 涛涛
 * @Date 2020/6/1 8:11
 * @Version 1.0
 **/
public interface BookRepository extends JpaRepository<SysBook, Long> {
    /**
     * 根据id查询咨询
     * @param id
     * @return
     */
    SysBook findByPkBookId(Long id);
    /**
     * 根据书名查出对应的pk_book_id
     * @param bookName
     * @return
     */
    @Query(value = "SELECT COUNT(pk_book_id) from sys_book where book_name=?1", nativeQuery = true)
    int findBookNumberByBookName(String bookName);

    /**
     * 根据书名修改数量
     * @param bookName
     * @param bookNumber
     * @return
     */
    @Transactional
    @Modifying
    @Query(value = "update sys_book set book_number=?2 where book_name=?1", nativeQuery = true)
    int setBookNumberByBookName(String bookName,int bookNumber);

    /***
     * 修改
     * @param bookName
     * @param bookResidueNumber
     * @return
     */
    @Transactional
    @Modifying
    @Query(value = "update sys_book set book_residue_number=?2 where book_name=?1", nativeQuery = true)
    int setBookResidueByBookName(String bookName, int bookResidueNumber);

    /**
     * 批量逻辑删除书籍种类
     *
     * @return
     */
    @Modifying
    @Transactional(timeout = 10,rollbackFor = RuntimeException.class)
    @Query("update SysBook v set v.isDeleted = true where v.pkBookId in ?1")
    int deleteBatchBook(List<Long> ids);
    /**
     * 根据id逻辑删除
     * @param pkBookId
     */
    @Modifying
    @LastModifiedBy
    @Transactional(rollbackFor = RuntimeException.class)
    @Query(value = "update sys_book set is_deleted = true where pk_book_id = ?1",nativeQuery = true)
    void  deleteByPkBookId(Long pkBookId);

    /**
     * 分页查询未被逻辑查询删除的一卡通信息数据
     * @return
     */
    @Query(value = "select * from sys_book where is_deleted =false ",nativeQuery = true)
    Page<SysBook> getAllSysBook(Pageable pageable);



}
