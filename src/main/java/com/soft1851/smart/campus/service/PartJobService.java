package com.soft1851.smart.campus.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.soft1851.smart.campus.model.dto.JobDto;
import com.soft1851.smart.campus.model.dto.JobPageDto;
import com.soft1851.smart.campus.model.dto.PartJobDto;
import com.soft1851.smart.campus.model.entity.PartJob;

import java.util.List;

/**
 * @author Su
 * @className PartJobService
 * @Description TODO
 * @Date 2020/6/17 10:40
 * @Version 1.0
 **/
public interface PartJobService extends IService<PartJob> {

    List<PartJob> findList(JobPageDto jobPageDto);

    int add(PartJobDto partJobDto);

    int updateJob(PartJobDto partJobDto);

    int delete(JobDto jobDto);
}
