package com.soft1851.smart.campus.service;

import com.soft1851.smart.campus.constant.ResponseResult;
import com.soft1851.smart.campus.model.entity.SysSubject;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Description TODO
 * @Author wf
 * @Date 2020/6/21
 * @Version 1.0
 */
@Service
public interface SysSubjectService {

    /**
     * 获取所有的课程
     * @return
     */
    List<SysSubject> selectAll();

    /**
     * 模糊查询学科
     * @param keywords
     * @return
     */
    ResponseResult getSubjectLike(String keywords);

    /**
     * 获取所有学科（用于下拉框）
     * @return
     */
    ResponseResult getAllSysSubjectVo();

}
