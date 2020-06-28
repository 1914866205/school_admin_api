package com.soft1851.smart.campus.service.Impl;

import com.soft1851.smart.campus.constant.ResponseResult;
import com.soft1851.smart.campus.mapper.SysSubjectMapper;
import com.soft1851.smart.campus.model.entity.SysSubject;
import com.soft1851.smart.campus.model.vo.SysSubjectVo;
import com.soft1851.smart.campus.repository.SysSubjectRepository;
import com.soft1851.smart.campus.service.SysSubjectService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.sql.SQLException;
import java.util.List;

/**
 * @Description TODO
 * @Author wf
 * @Date 2020/6/21
 * @Version 1.0
 */
@Service
public class SysSubjectServiceImpl implements SysSubjectService {
    @Resource
    private SysSubjectRepository sysSubjectRepository;
    @Resource
    private SysSubjectMapper sysSubjectMapper;

    @Override
    public List<SysSubject> selectAll() {
        return sysSubjectRepository.findAll();
    }

    @Override
    public ResponseResult getSubjectLike(String keywords) {
        List<SysSubjectVo> mapList = null;
        try {
            mapList = sysSubjectMapper.findSubjectLike(keywords);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ResponseResult.success(mapList);
    }

    @Override
    public ResponseResult getAllSysSubjectVo() {
        List<SysSubjectVo> mapList = null;
        try {
            mapList = sysSubjectMapper.findAllSubject();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ResponseResult.success(mapList);
    }
}
