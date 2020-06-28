package com.soft1851.smart.campus.mapper;

import com.soft1851.smart.campus.model.vo.SysSubjectVo;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.sql.SQLException;
import java.util.List;

@SpringBootTest
class SysSubjectMapperTest {
    @Resource
    private SysSubjectMapper sysSubjectMapper;

    @Test
    void findSubjectLike() throws SQLException {
        List<SysSubjectVo> mapList = sysSubjectMapper.findSubjectLike("大学生");
        System.out.println(mapList);
    }

    @Test
    void findAllSubject() throws SQLException{
        List<SysSubjectVo> sysSubjectVos = sysSubjectMapper.findAllSubject();
        System.out.println(sysSubjectVos.size());
    }
}