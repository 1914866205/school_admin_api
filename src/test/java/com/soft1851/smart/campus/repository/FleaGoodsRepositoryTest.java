package com.soft1851.smart.campus.repository;

import com.soft1851.smart.campus.model.dto.PageDto;
import com.soft1851.smart.campus.model.entity.FleaGoods;
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
class FleaGoodsRepositoryTest {
    @Resource
    private FleaGoodsRepository fleaGoodsRepository;

    @Test
    void getAllGoodsByTime() {
        PageDto pageDto = new PageDto();
        pageDto.setPageSize(3);
        pageDto.setCurrentPage(1);
        Pageable pageable = PageRequest.of(pageDto.getCurrentPage() - 1, pageDto.getPageSize(), Sort.Direction.DESC, "goodsCreateTime");
        System.out.println(fleaGoodsRepository.getAllGoodsByTime(pageable));
    }
}