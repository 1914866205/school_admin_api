package com.soft1851.smart.campus.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.soft1851.smart.campus.model.dto.JobAddDto;
import com.soft1851.smart.campus.model.dto.JobPageDto;
import com.soft1851.smart.campus.model.entity.Job;
import com.soft1851.smart.campus.model.vo.JobVo;

import java.util.List;

/**
 * @author Su
 * @className JobService
 * @Description TODO
 * @Date 2020/6/17 10:37
 * @Version 1.0
 **/
public interface JobService extends IService<Job> {

    List<JobVo> jobList(JobPageDto jobPageDto);

    int insertJob(JobAddDto jobAddDto);

    int updateJob(JobAddDto jobAddDto);
}
