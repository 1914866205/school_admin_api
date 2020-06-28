package com.soft1851.smart.campus.repository;

import com.soft1851.smart.campus.model.dto.PageDto;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import javax.annotation.Resource;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @Author yhChen
 * @Description
 * @Date 2020/6/25
 */
@SpringBootTest
@Slf4j
class FleaCommentRepositoryTest {
    @Resource
    private FleaCommentRepository fleaCommentRepository;

    @Test
    void selectAll() {
        PageDto pageDto = new PageDto();
        pageDto.setPageSize(3);
        pageDto.setCurrentPage(1);
        Pageable pageable = PageRequest.of(pageDto.getCurrentPage() - 1, pageDto.getPageSize());
        System.out.println(fleaCommentRepository.selectAll(pageable));
    }
}