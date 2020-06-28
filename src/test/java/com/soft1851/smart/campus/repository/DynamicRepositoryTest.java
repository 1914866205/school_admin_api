package com.soft1851.smart.campus.repository;

import com.soft1851.smart.campus.model.entity.Dynamic;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@SpringBootTest
class DynamicRepositoryTest {
    @Resource
    private DynamicRepository dynamicRepository;

    @Test
    void findDynamicByPkDynamicIdAndIsDeleted() {
    }

    @Test
    void updateIsDelete() {
        List<String> ids = new ArrayList<>();
//        Long max = Long.MAX_VALUE;

        String max1 = "1271346795213361152";
        String max2 = "1271709711276052480";
        String max3 = "1271709712223965184";
        ids.add(max1);
        ids.add(max2);
        ids.add(max3);
        System.out.println(ids);
        int a = dynamicRepository.updateIsDelete(ids);
        System.out.println(a);
        //Long类型最大只有19位
//        Long maxL = Long.MAX_VALUE;
//
//        String max2 = "1271346795213361152";
//        System.out.println(max2);
//        System.out.println(maxL);
////		System.out.println(Long.parseLong(max2));//抛异常
//
//        BigInteger maxInt = new BigInteger(max2);
//        System.out.println(maxInt);

    }

    @Test
    void findAllByIsDeleted() {
        Pageable pageable = PageRequest.of(
                1,
                3,
                Sort.Direction.DESC,
                "pkDynamicId");
        Page<Dynamic> sysBooks = dynamicRepository.findAllByIsDeleted(pageable);
        System.out.println(sysBooks.getContent());
    }
}