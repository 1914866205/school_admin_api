package com.soft1851.smart.campus.service.Impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.soft1851.smart.campus.mapper.JobMapper;
import com.soft1851.smart.campus.model.dto.JobAddDto;
import com.soft1851.smart.campus.model.dto.JobPageDto;
import com.soft1851.smart.campus.model.entity.Job;
import com.soft1851.smart.campus.model.vo.JobVo;
import com.soft1851.smart.campus.service.JobService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

/**
 * @author su
 * @className JobServiceImpl
 * @Description TODO
 * @Date 2020/6/17
 * @Version 1.0
 **/
@Service
public class JobServiceImpl extends ServiceImpl<JobMapper, Job> implements JobService {

    @Resource
    private JobMapper jobMapper;

    @Override
    public List<JobVo> jobList(JobPageDto jobPageDto) {
        jobPageDto.setCurrentPage((jobPageDto.getCurrentPage()-1)*jobPageDto.getPageSize());
        return jobMapper.jobList(jobPageDto);
    }

    @Override
    public int insertJob(JobAddDto jobAddDto) {
        Job job = Job.builder()
                .name(jobAddDto.getName())
                .description(jobAddDto.getDescription())
                .boss(jobAddDto.getBoss())
                .bossPhone(jobAddDto.getBossPhone())
                .bossAvatar(jobAddDto.getBossAvatar())
                .companyId(jobAddDto.getCompanyId())
                .workplace(jobAddDto.getWorkplace())
                .workingTime(jobAddDto.getWorkingTime())
                .pay(new BigDecimal(0))
                .min(jobAddDto.getMin())
                .max(jobAddDto.getMax())
                .experience(jobAddDto.getExperience())
                .diploma(jobAddDto.getDiploma())
                .jobTypeId(jobAddDto.getJobTypeId())
                .number(0)
                .resumes("")
                .isDeleted(false)
                .gmtCreate(Timestamp.valueOf(LocalDateTime.now()))
                .gmtModified(Timestamp.valueOf(LocalDateTime.now()))
                .build();
        return jobMapper.insert(job);
    }

    @Override
    public int updateJob(JobAddDto jobAddDto) {
        Job job = new Job();
        UpdateWrapper<Job> wrapper = new UpdateWrapper<>();
        wrapper.set("name", jobAddDto.getName())
                .set("description", jobAddDto.getDescription())
                .set("company_id", jobAddDto.getCompanyId())
                .set("workplace", jobAddDto.getWorkplace())
                .set("working_time", jobAddDto.getWorkingTime())
                .set("min", jobAddDto.getMin())
                .set("max", jobAddDto.getMax())
                .set("experience", jobAddDto.getExperience())
                .set("diploma", jobAddDto.getDiploma())
                .set("job_type_id", jobAddDto.getJobTypeId())
                .set("gmt_modified", Timestamp.valueOf(LocalDateTime.now()))
                .eq("pk_job_id", jobAddDto.getId());
        return jobMapper.update(job, wrapper);
    }
}
