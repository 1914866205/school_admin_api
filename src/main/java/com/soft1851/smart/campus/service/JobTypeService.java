package com.soft1851.smart.campus.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.soft1851.smart.campus.model.dto.JobDto;
import com.soft1851.smart.campus.model.entity.JobType;

import java.util.List;


/**
 * @author Su
 * @className JobTypeService
 * @Description TODO
 * @Date 2020/6/17 10:39
 * @Version 1.0
 **/
public interface JobTypeService extends IService<JobType> {

    /**
     * 校招职位类型列表
     * @return
     */
    List<JobType> typeList();

    /**
     * 添加类型
     * @param jobDto
     * @return
     */
    int add(JobDto jobDto);

    /**
     * 更新
     * @param jobDto
     * @return
     */
    int update(JobDto jobDto);
}
