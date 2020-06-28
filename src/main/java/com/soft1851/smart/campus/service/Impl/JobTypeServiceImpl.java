package com.soft1851.smart.campus.service.Impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.soft1851.smart.campus.mapper.JobTypeMapper;
import com.soft1851.smart.campus.model.dto.JobDto;
import com.soft1851.smart.campus.model.entity.JobType;
import com.soft1851.smart.campus.service.JobTypeService;
import org.springframework.jdbc.object.UpdatableSqlQuery;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

/**
 * @author su
 * @className JobTypeServiceImpl
 * @Description TODO
 * @Date 2020/6/17
 * @Version 1.0
 **/
@Service
public class JobTypeServiceImpl extends ServiceImpl<JobTypeMapper, JobType> implements JobTypeService {


    @Resource
    private JobTypeMapper jobTypeMapper;

    @Override
    public List<JobType> typeList() {
        QueryWrapper<JobType> wrapper = new QueryWrapper<>();
        wrapper.select("pk_job_type_id","name","parent_id","is_deleted", "gmt_create", "gmt_modified");
        return jobTypeMapper.selectList(wrapper);
    }

    @Override
    public int add(JobDto jobDto) {
        JobType jobType =JobType.builder()
                .name(jobDto.getField())
                .parentId(0L)
                .isDeleted(false)
                .gmtCreate(Timestamp.valueOf(LocalDateTime.now()))
                .gmtModified(Timestamp.valueOf(LocalDateTime.now()))
                .build();
        return jobTypeMapper.insert(jobType);
    }

    @Override
    public int update(JobDto jobDto) {
        JobType jobType = new JobType();
        UpdateWrapper<JobType> wrapper = new UpdateWrapper<>();
        wrapper.set("name", jobDto.getField())
                .eq("pk_job_type_id", jobDto.getId());
        return jobTypeMapper.update(jobType, wrapper);
    }

}