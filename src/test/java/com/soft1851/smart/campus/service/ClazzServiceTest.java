package com.soft1851.smart.campus.service;

import com.soft1851.smart.campus.constant.ResponseResult;
import com.soft1851.smart.campus.model.dto.PageDto;
import com.soft1851.smart.campus.model.entity.Clazz;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

@SpringBootTest
class ClazzServiceTest {

    @Resource
    private ClazzService clazzService;

    @Test
    void findAllClazz() {
        PageDto pageDto = PageDto.builder().currentPage(0).pageSize(4).build();
        ResponseResult responseResult = clazzService.findAllClazz(pageDto);
        System.out.println(responseResult);
    }

    @Test
    void increaseClazz() {
        Clazz clazz = Clazz.builder()
                .clazzHeadmaster("1018***888")
                .collegeId((long) 1)
                .grade("18级")
                .name("软件1888").build();
        clazzService.increaseClazz(clazz);

    }

    @Test
    void increaseStudentToClazz() {
        String ids = "21,22";
        ResponseResult responseResult = clazzService.increaseStudentToClazz((long) 2, ids);
        System.out.println(responseResult);
    }

    @Test
    void deletedClazz() {
        ResponseResult responseResult = clazzService.deletedClazz((long) 7);
        System.out.println(responseResult);
    }

    @Test
    void deleteBatchByClazzId() {
        String ids = "10,11";
        ResponseResult responseResult = clazzService.deleteBatchByClazzId(ids);
        System.out.println(responseResult);
    }

    @Test
    void getAllClazzs() {
        ResponseResult responseResult = clazzService.getAllClazzs("软件");
        System.out.println(responseResult);
    }

    @Test
    void getAllClazz() {
       ResponseResult clazzVos = clazzService.getAllClazz();
        System.out.println(clazzVos);
    }
}