package com.soft1851.smart.campus.service;

import com.soft1851.smart.campus.constant.ResponseResult;
import com.soft1851.smart.campus.model.dto.DoubleFieldDto;
import com.soft1851.smart.campus.model.dto.PageDto;
import com.soft1851.smart.campus.model.entity.UserAccount;
import com.soft1851.smart.campus.model.vo.UserAccountVo;

import java.util.List;
import java.util.Map;

/**
 * @author Yujie_Zhao
 * @ClassName UserAccountService
 * @Description 账号Service
 * @Date 2020/6/3  16:47
 * @Version 1.0
 **/
public interface  UserAccountService {

    /**
     * 新增用户数据
     *
     * @param userAccount
     * @return
     */
    ResponseResult insertUserAccount(UserAccount userAccount);

    /**
     * 查询所有账号
     *
     * @return
     */
    ResponseResult findAllUserAccount(PageDto pageDto);


    /**
     * 单个删除账号逻辑删除
     *
     * @param id
     * @return
     */
    ResponseResult deleteUserAccount(String id);

    /**
     * 批量删除账号
     *
     * @param ids
     * @return
     */
    ResponseResult deletedBatch(String ids);

    /**
     * 修改账号
     * @param userAccount
     * @return
     */
    ResponseResult updateUserAccount(UserAccount userAccount);

    /**
     * 获取所有学生数据
     *
     * @param pageDto
     * @return
     */
    ResponseResult getAllStudent(PageDto pageDto);
    /**
     * 获取所有教师数据
     *
     * @param pageDto
     * @return
     */
    ResponseResult getAllTeacher(PageDto pageDto);

    /**
     * 根据id修改用户信息
     * @return
     */
    int updateUserAccountById(UserAccount userAccount);

    /**
     * 修改用户的状态
     * @param pkUserAccountId
     * @param status
     * @return
     */
    ResponseResult updateStatusById(String pkUserAccountId,Boolean status);

    /**
     * 获取所有老师数据
     * @return
     */
    ResponseResult getAllTeacherMessage();

    /**
     * 查询未被分配的学生
     * @param pageDto
     * @return
     */
    ResponseResult getAllUndistributedStudent(PageDto pageDto);

    /**
     * 通过学号或者学生姓名查询学生信息
     * @param keywords
     * @return
     */
    ResponseResult findStudentLike(String keywords);

    /**
     * 查询所有未分配的学生信息
     * @return
     */
    List<Map<String, Object>> getAllStudents();

    /**
     * 修改账户信息
     * @param doubleFieldDto
     * @return
     */
    int updateClazzIdById(DoubleFieldDto doubleFieldDto);

    /**
     * 根据班课id查询用户信息
     * @return
     */
    List<Map<String, Object>> getUserAccountByClazzId(long clazzId);

    /**
     * 通过教工号或者教师姓名查询教师信息
     * @param keywords
     * @return
     */
    ResponseResult findTeacherLike(String keywords);

    /**
     * 导出教师信息
     */
    void exportTeacherInfo();

    /**
     * 导出学生信息
     */
    void exportStudentInfo();
}
