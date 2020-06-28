package com.soft1851.smart.campus.controller;

import com.soft1851.smart.campus.constant.ResponseResult;
import com.soft1851.smart.campus.model.dto.UpdateNewExaminationDto;
import com.soft1851.smart.campus.model.vo.InsertExamVo;
import com.soft1851.smart.campus.service.ExaminationService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author xunmi
 * @ClassName ExaminationController
 * @Description 考务控制器
 * @Date 2020/5/31
 * @Version 1.0
 **/
@RestController
@RequestMapping("/examination")
@Api(value = "ExaminationController" ,tags = "考务管理接口")
public class ExaminationController {

    @Resource
    private ExaminationService examinationService;

    /**
     * 修改考务
     * @param updateNewExaminationDto
     */
    @ApiOperation(value = "修改考务",notes = "")
    @PostMapping("/modification")
    public void updateInfo(@RequestBody UpdateNewExaminationDto updateNewExaminationDto) {
        examinationService.updateInfo(updateNewExaminationDto);
    }

    /**
     * 新增考务
     * @param insertExamVo
     * @return
     */
    @ApiOperation(value = "新增考务",notes = "")
    @PostMapping(value = "/increase")
    public ResponseResult increaseSysFeedback(@RequestBody InsertExamVo insertExamVo){
        return examinationService.increaseInfo(insertExamVo);
    }

    /**
     * 查询所有考务
     * @return
     */
    @ApiOperation(value = "查询所有考务",notes = "")
    @PostMapping(value = "/all")
    public ResponseResult selectAllExamination(){
        return examinationService.selectAllExamination();
    }

    /**
     * 删除单条考务
     * @return
     */
    @ApiOperation(value = "删除单条考务",notes = "")
    @PostMapping(value = "/deletion")
    public ResponseResult deletedExamination(@RequestBody UpdateNewExaminationDto updateNewExaminationDto){
        return examinationService.deletedExamination(updateNewExaminationDto);
    }

    /**
     * 查询某个教务下面的学生数据
     * @return
     */
    @ApiOperation(value = "查询某个教务下面的学生数据",notes = "请求参数：班级id、科目id、学期")
    @PostMapping(value = "/student")
    public ResponseResult selectStudentInExamination(@RequestBody UpdateNewExaminationDto updateNewExaminationDto){
        return examinationService.selectStudentInExamination(updateNewExaminationDto);
    }



}
