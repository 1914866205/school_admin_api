package com.soft1851.smart.campus.mapper;

import com.soft1851.smart.campus.model.vo.ClazzVo;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.List;

@SpringBootTest
class ClazzMapperTest {
    @Resource
    private ClazzMapper clazzMapper;

    @Test
    void getAllClazz() {
        List<ClazzVo> clazzes = clazzMapper.getAllClazz();
        System.out.println(clazzes.size());
    }
}