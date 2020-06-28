package com.soft1851.smart.campus.service.Impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.soft1851.smart.campus.mapper.PartJobMapper;
import com.soft1851.smart.campus.model.dto.JobDto;
import com.soft1851.smart.campus.model.dto.JobPageDto;
import com.soft1851.smart.campus.model.dto.PartJobDto;
import com.soft1851.smart.campus.model.entity.PartJob;
import com.soft1851.smart.campus.service.PartJobService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

/**
 * @author su
 * @className PartJobServiceImpl
 * @Description TODO
 * @Date 2020/6/17
 * @Version 1.0
 **/
@Slf4j
@Service
public class PartJobServiceImpl extends ServiceImpl<PartJobMapper, PartJob> implements PartJobService {

    @Resource
    private PartJobMapper partJobMapper;


    @Override
    public List<PartJob> findList(JobPageDto jobPageDto) {
        log.info(">>>>>>>>>>>>>>>{}",jobPageDto);
        QueryWrapper<PartJob> wrapper =  new QueryWrapper<>();
        wrapper.eq("is_deleted", 0);
        IPage<PartJob> page = new Page<>(jobPageDto.getCurrentPage(), jobPageDto.getPageSize());
        return partJobMapper.selectPage(page, wrapper).getRecords();
    }

    @Override
    public int add(PartJobDto partJobDto) {
        PartJob partJob = PartJob.builder()
                .name(partJobDto.getName())
                .description(partJobDto.getDescription())
                .bossId(partJobDto.getBossId())
                .bossName(partJobDto.getBossName())
                .bossPhone(partJobDto.getBossPhone())
                .bossAvatar(partJobDto.getBossAvatar())
                .workplace(partJobDto.getWorkplace())
                .workingDate(partJobDto.getWorkingDate())
                .workingTime(partJobDto.getWorkingTime())
                .pay(partJobDto.getPay())
                .payType(partJobDto.getPayType())
                .jobType(partJobDto.getJobType())
                .number(partJobDto.getNumber())
                .have(0)
                .need(false)
                .isDeleted(false)
                .gmtCreate(Timestamp.valueOf(LocalDateTime.now()))
                .gmtModified(Timestamp.valueOf(LocalDateTime.now()))
                .build();
        return partJobMapper.insert(partJob);
    }

    @Override
    public int updateJob(PartJobDto partJobDto) {
        PartJob partJob = new PartJob();
        UpdateWrapper<PartJob> wrapper = new UpdateWrapper<>();
        wrapper.set("name",partJobDto.getName())
                .set("description",partJobDto.getDescription())
                .set("workplace", partJobDto.getWorkplace())
                .set("working_date", partJobDto.getWorkingDate())
                .set("working_time", partJobDto.getWorkingTime())
                .set("pay", partJobDto.getPay())
                .set("pay_type", partJobDto.getPayType())
                .set("job_type", partJobDto.getJobType())
                .set("number", partJobDto.getNumber())
                .set("gmt_modified", Timestamp.valueOf(LocalDateTime.now()))
                .eq("pk_part_job_id", partJobDto.getId());
        return partJobMapper.update(partJob, wrapper);
    }


    @Override
    public int delete(JobDto jobDto) {
        PartJob partJob = new PartJob();
        UpdateWrapper<PartJob> wrapper = new UpdateWrapper<>();
        wrapper.set("is_deleted", true)
                .eq("pk_part_job_id", jobDto.getId());
        return partJobMapper.update(partJob, wrapper);
    }



}
