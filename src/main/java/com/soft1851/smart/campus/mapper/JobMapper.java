package com.soft1851.smart.campus.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.soft1851.smart.campus.model.dto.JobPageDto;
import com.soft1851.smart.campus.model.entity.Job;
import com.soft1851.smart.campus.model.vo.JobVo;

import java.util.List;

/**
 * @author Su
 * @className JobMapper
 * @Description TODO
 * @Date 2020/6/17 9:46
 * @Version 1.0
 **/
public interface JobMapper extends BaseMapper<Job> {


    List<JobVo> jobList(JobPageDto jobPageDto);
}
