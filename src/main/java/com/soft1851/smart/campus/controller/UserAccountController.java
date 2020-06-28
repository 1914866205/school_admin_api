package com.soft1851.smart.campus.controller;

import com.soft1851.smart.campus.constant.ResponseResult;
import com.soft1851.smart.campus.model.dto.BatchDeletionDto;
import com.soft1851.smart.campus.model.dto.DoubleFieldDto;
import com.soft1851.smart.campus.model.dto.PageDto;
import com.soft1851.smart.campus.model.dto.QueryDto;
import com.soft1851.smart.campus.model.entity.UserAccount;
import com.soft1851.smart.campus.service.UserAccountService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * @author Yujie_Zhao
 * @ClassName UserAccountController
 * @Description 用户账号管理Controller层
 * @Date 2020/6/4  10:17
 * @Version 1.0
 **/
@RestController
@Slf4j
    @RequestMapping(value = "/userAccount")
@Api(value = "UserAccountController",tags = {"用户账号接口"})
public class UserAccountController {
;
    @Resource
    private UserAccountService userAccountService;

    /**
     * 分页查询所有账号
     * @return
     */
    @ApiOperation(value = "分页查询所有账号",notes = "")
    @PostMapping(value = "/all")
    public ResponseResult findInfoType(@RequestBody PageDto pageDto){
        System.out.println(pageDto);
        return userAccountService.findAllUserAccount(pageDto);
    }


    /**
     * 删除用户账号
     * @param
     * @return
     */
    @ApiOperation(value = "删除用户账号",notes = "")
    @PostMapping(value = "/deletion")
    public ResponseResult deleteInfoType(@RequestBody QueryDto queryDto){
        return userAccountService.deleteUserAccount(queryDto.getFiled1());
    }

    /**
     * 批量删除用户账号
     * @return
     */
    @ApiOperation(value = "批量删除用户账号",notes = "")
    @PostMapping(value = "/deletionBath")
    public ResponseResult deletedBatch(@RequestBody BatchDeletionDto batchDeletionDto){
        return userAccountService.deletedBatch(batchDeletionDto.getIds());
    }

    /**
     * 修改用户信息
     * @param userAccount
     * @return
     */
    @ApiOperation(value = "修改用户信息",notes = "")
    @PostMapping(value = "/modification")
    public ResponseResult updateInfoType(@RequestBody UserAccount userAccount){
       return userAccountService.updateUserAccount(userAccount);
    }


    /**
     * 获取所有学生数据信息
     * @param pageDto
     * @return
     */
    @ApiOperation(value = "获取所有学生数据信息",notes = "")
    @PostMapping(value = "/student")
    public ResponseResult getAllStudent(@RequestBody PageDto pageDto){
        return userAccountService.getAllStudent(pageDto);
    }

    /**
     * 获取所有教师数据信息
     * @param pageDto
     * @return
     */
    @ApiOperation(value = "获取所有教师数据信息",notes = "")
    @PostMapping(value = "/teacher")
    public ResponseResult getAllTeacher(@RequestBody PageDto pageDto){
        return userAccountService.getAllTeacher(pageDto);
    }

    /**
     * 获取所有教师数据信息
     * @return
     */
    @ApiOperation(value = "获取教师分配班主任",notes = "")
    @PostMapping(value = "/headmaster")
    public ResponseResult getAllTeacherMessage(){
        return userAccountService.getAllTeacherMessage();
    }


    /**
     * 新增用户数据信息
     * @param userAccount
     * @return
     */
    @ApiOperation(value = "新增学生数据信息",notes = "")
    @PostMapping(value = "/insert")
    public ResponseResult insertUserAccount(@RequestBody UserAccount userAccount){
        System.out.println("参数>>>>>>>>>>>>>>>>>>>>>>>");
        System.out.println(userAccount);
        return userAccountService.insertUserAccount(userAccount);
    }

    /**
     * 修改用户状态
     * @param queryDto
     * @return
     */
    @ApiOperation(value = "修改用户状态",notes = "")
    @PostMapping(value = "/status")
    public ResponseResult updateStatusById(@RequestBody QueryDto queryDto){
        return userAccountService.updateStatusById(queryDto.getFiled1(),queryDto.getStatus());
    }


    /**
     * 查询未被分配的学生
     * @param pageDto
     * @return
     */
    @ApiOperation(value = "查询未被分配的学生",notes = "")
    @PostMapping(value = "/undistributed")
    public ResponseResult getAllUndistributedStudents(@RequestBody PageDto pageDto){
        return userAccountService.getAllUndistributedStudent(pageDto);
    }

    /**
     * 模糊查询学生数据
     * @param batchDeletionDto
     * @return
     */
    @ApiOperation(value = "模糊查询学生数据",notes = "ids为keywords关键字")
    @PostMapping(value = "/student/like")
    public ResponseResult findStudentLike(@RequestBody BatchDeletionDto batchDeletionDto){
        return userAccountService.findStudentLike(batchDeletionDto.getIds());
    }

    @ApiOperation(value = "查询所有学生", notes = "无参")
    @PostMapping(value = "/student/list")
    public List<Map<String, Object>> getAllStudents() {
        return userAccountService.getAllStudents();
    }

    @PostMapping(value = "/modification/id")
    public ResponseResult updateClazzId(@RequestBody DoubleFieldDto doubleFieldDto) {
        System.out.println("分配学生参数:>>>>>>>>>>>>>>" + doubleFieldDto);
        userAccountService.updateClazzIdById(doubleFieldDto);
        return ResponseResult.success();
    }

    /**
     * 根据班课id查询班课学生信息
     * @param queryDto
     * @return
     */
    @PostMapping(value = "/list/clazzId")
    public List<Map<String, Object>> getUserAccountByClazzId(@RequestBody QueryDto queryDto) {
        return userAccountService.getUserAccountByClazzId(Long.parseLong(queryDto.getField().toString()));
    }
    /**
     * 模糊查询教师数据
     * @param batchDeletionDto
     * @return
     **/
    @ApiOperation(value = "模糊查询教师数据",notes = "ids为keywords关键字")
    @PostMapping(value = "/teacher/like")
    public ResponseResult findTeacherLike(@RequestBody BatchDeletionDto batchDeletionDto){
        return userAccountService.findTeacherLike(batchDeletionDto.getIds());
    }

    @ApiOperation(value = "导出学生信息数据")
    @PostMapping(value = "export/student")
    public ResponseResult exportStudentInfo() {
        userAccountService.exportStudentInfo();
        return ResponseResult.success();
    }

    @ApiOperation(value = "导出教师信息数据")
    @PostMapping(value = "export/teacher")
    public ResponseResult exportTeacherInfo() {
        userAccountService.exportTeacherInfo();
        return ResponseResult.success();
    }
}
