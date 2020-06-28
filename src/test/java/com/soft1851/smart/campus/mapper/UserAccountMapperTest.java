package com.soft1851.smart.campus.mapper;

import com.soft1851.smart.campus.model.dto.PageDto;
import com.soft1851.smart.campus.model.entity.UserAccount;
import com.soft1851.smart.campus.model.vo.StudentVo;
import com.soft1851.smart.campus.model.vo.TeacherVo;
import com.soft1851.smart.campus.model.vo.UserAccountVo;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.sql.SQLException;
import java.util.List;

@SpringBootTest
class UserAccountMapperTest {

    @Resource
    private UserAccountMapper userAccountMapper;
    @Test
    void getUserAccountVo() {
        PageDto pageDto = PageDto.builder()
                .currentPage(1)
                .pageSize(3)
                .build();
        List<UserAccountVo> userAccountVos = userAccountMapper.getTeacherUserAccountVo(pageDto);
        System.out.println(userAccountVos);
    }

    @Test
    void getUserAccountByJobNumber() {
        UserAccount userAccount = userAccountMapper.getUserAccountByJobNumber("1802333101");
        System.out.println(userAccount);
    }


    @Test
    void getAllTeacher() {
        List<TeacherVo> teacherVos =  userAccountMapper.getAllTeacher();
        System.out.println(teacherVos);
    }

    @Test
    void getAllUndistributedStudents() {
        PageDto pageDto = PageDto.builder()
                .currentPage(1)
                .pageSize(2)
                .build();
        List<StudentVo> studentVos = userAccountMapper.getAllUndistributedStudents(pageDto);
        System.out.println(studentVos);
    }

    @Test
    void findStudentLike() throws SQLException {
        List<StudentVo> studentVos = userAccountMapper.findStudentLike("1802333");
        System.out.println(studentVos);
    }

    @Test
    void findJobNumberByClazzId() throws SQLException{
        List<String> stringList = userAccountMapper.findJobNumberByClazzId((long)10);
        System.out.println(stringList);
    }

    @Test
    void getAllStudentInfo() {
        PageDto dto = PageDto.builder()
                .currentPage(1)
                .pageSize(100)
                .build();
        System.out.println(userAccountMapper.getUserAccountVo(dto));
    }
}