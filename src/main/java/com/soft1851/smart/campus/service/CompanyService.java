package com.soft1851.smart.campus.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.soft1851.smart.campus.model.dto.CompanyDto;
import com.soft1851.smart.campus.model.dto.JobDto;
import com.soft1851.smart.campus.model.dto.JobPageDto;
import com.soft1851.smart.campus.model.entity.Company;

import java.util.List;

/**
 * @author Su
 * @className CompanyService
 * @Description TODO
 * @Date 2020/6/17 10:37
 * @Version 1.0
 **/
public interface CompanyService extends IService<Company> {

    List<Company> findAll(JobPageDto jobPageDto);

    int addCompany(CompanyDto companyDto);

    int update(CompanyDto companyDto);

    int delete(JobDto jobDto);
}
