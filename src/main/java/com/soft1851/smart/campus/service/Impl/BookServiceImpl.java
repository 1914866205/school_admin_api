package com.soft1851.smart.campus.service.Impl;

import com.soft1851.smart.campus.constant.ResponseResult;
import com.soft1851.smart.campus.constant.ResultCode;
import com.soft1851.smart.campus.exception.CustomException;
import com.soft1851.smart.campus.model.dto.PageDto;
import com.soft1851.smart.campus.model.dto.SysBookDto;
import com.soft1851.smart.campus.model.entity.SysBook;
import com.soft1851.smart.campus.repository.BookRepository;
import com.soft1851.smart.campus.service.BookService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * @Description TODO
 * @Author 涛涛
 * @Date 2020/6/1 8:16
 * @Version 1.0
 **/
@Slf4j
@Service
public class BookServiceImpl implements BookService {
    @Resource
    private BookRepository bookRepository;

    /**
     * 添加一种图书
     *
     * @param sysBookDto
     * @return
     */
    @Override
    public ResponseResult increaseSysBook( SysBookDto sysBookDto) {
        log.info("sysBookDto>>>>>>>>>,{}", sysBookDto);
        SysBook sysBook = SysBook.builder()
                .author(sysBookDto.getAuthor())
                .cover(sysBookDto.getCover())
                .type(sysBookDto.getType())
                .description(sysBookDto.getDescription())
                .bookName(sysBookDto.getBookName())
                .bookNumber(sysBookDto.getBookNumber())
                .bookResidueNumber(sysBookDto.getBookNumber())
                .gmtCreate(Timestamp.valueOf(LocalDateTime.now()))
                .gmtModified(Timestamp.valueOf(LocalDateTime.now()))
                .status(false)
                .isDeleted(false)
                .build();
        System.out.println(sysBook);
        bookRepository.save(sysBook);
        return ResponseResult.success(ResultCode.SUCCESS);
    }

    /**
     * 逻辑删除一本书
     * @param pkBookId
     * @return
     */
    @Override
    public ResponseResult deleteBook(Long pkBookId) {
        //根据id查询角色数据是否存在 ，若存在进行删除，不存则返回 数据有误
        SysBook sysBook = bookRepository.findByPkBookId(pkBookId);
        if (sysBook != null) {
            if (sysBook.getBookNumber().equals(sysBook.getBookResidueNumber())){
                bookRepository.deleteByPkBookId(pkBookId);
                return ResponseResult.success("删除成功");
            }else {
                throw new CustomException("存在未归还书籍,无法进行删除", ResultCode.DATABASE_ERROR);
            }
        } else {
            return ResponseResult.failure(ResultCode.RESULT_CODE_DATA_NONE);
        }
    }

    /**
     * 批量删除图书种类
     *
     * @param idsArray
     * @return
     */
    @Override
    public ResponseResult deletedBatchSysBook(String idsArray) {
        String[] array = idsArray.split("\\,");
        System.out.println("idsArray"+idsArray);
        System.out.println("Array"+array);
        List<Long> ids = new ArrayList<>();
        for (String id : array) {
            ids.add(Long.valueOf(id));
        }
        log.info("需要删除的图书种类id>>>>>>>>>,{}", idsArray);
        int i = bookRepository.deleteBatchBook(ids);
        System.out.println("删除" + i + "种");
        log.info("删除" + i + "种");
        return ResponseResult.success();
    }

    @Override
    public ResponseResult findAllByPage(PageDto pageDto) {
        //分页要减一
        Pageable pageable = PageRequest.of(
                pageDto.getCurrentPage() ,
                pageDto.getPageSize());
        Page<SysBook> sysBooks = bookRepository.findAll(pageable);
        System.out.println("*********************");
        System.out.println(sysBooks);
        System.out.println("*********************");
        return ResponseResult.success(sysBooks);
    }

    @Override
    public ResponseResult updatedBook(SysBookDto sysBookDto) {
        SysBook oldBook = bookRepository.getOne(sysBookDto.getId());
        // 当前库存=新总库存-旧总库存+旧当前库存
        int bookResidueNumber = sysBookDto.getBookNumber() - oldBook.getBookNumber() + oldBook.getBookResidueNumber();
        SysBook newBook = SysBook.builder()
                .pkBookId(oldBook.getPkBookId())
                .author(sysBookDto.getAuthor())
                .cover(sysBookDto.getCover())
                .type(sysBookDto.getType())
                .description(sysBookDto.getDescription())
                .status(sysBookDto.getStatus())
                .bookName(sysBookDto.getBookName())
                .bookNumber(sysBookDto.getBookNumber())
                .bookResidueNumber(bookResidueNumber)
                .gmtModified(Timestamp.valueOf(LocalDateTime.now()))
                .gmtCreate(oldBook.getGmtCreate())
                .isDeleted(oldBook.getIsDeleted())
                .build();
         bookRepository.delete(oldBook);
        return ResponseResult.success(bookRepository.save(newBook));
    }

    @Override
    public ResponseResult getAllSysBook(PageDto pageDto) {
        Pageable pageable = PageRequest.of(
                pageDto.getCurrentPage(),
                pageDto.getPageSize(),
                Sort.Direction.ASC,
                "pk_book_id");
        Page<SysBook> sysBooks = bookRepository.getAllSysBook(pageable);
        return ResponseResult.success(sysBooks.getContent());
    }
}