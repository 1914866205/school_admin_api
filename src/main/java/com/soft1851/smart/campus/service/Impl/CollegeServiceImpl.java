package com.soft1851.smart.campus.service.Impl;

import com.soft1851.smart.campus.constant.ResponseResult;
import com.soft1851.smart.campus.mapper.CollegeMapper;
import com.soft1851.smart.campus.model.vo.CollegeVo;
import com.soft1851.smart.campus.service.CollegeService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author Tao
 * @version 1.0
 * @ClassName CollegeServiceImpl
 * @Description TODO
 * @date 2020-06-17 10:48
 **/
@Service
public class CollegeServiceImpl implements CollegeService {
    @Resource
    private CollegeMapper collegeMapper;

    @Override
    public ResponseResult getAllCollege() {
        List<CollegeVo> colleges = collegeMapper.getAllCollege();
        return ResponseResult.success(colleges);
    }
}
