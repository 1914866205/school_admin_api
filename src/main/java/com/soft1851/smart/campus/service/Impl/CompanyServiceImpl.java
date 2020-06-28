package com.soft1851.smart.campus.service.Impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.soft1851.smart.campus.mapper.CompanyMapper;
import com.soft1851.smart.campus.mapper.JobMapper;
import com.soft1851.smart.campus.model.dto.CompanyDto;
import com.soft1851.smart.campus.model.dto.JobDto;
import com.soft1851.smart.campus.model.dto.JobPageDto;
import com.soft1851.smart.campus.model.entity.Company;
import com.soft1851.smart.campus.model.entity.Job;
import com.soft1851.smart.campus.service.CompanyService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

/**
 * @author su
 * @className CompanyServiceIMPL
 * @Description TODO
 * @Date 2020/6/17
 * @Version 1.0
 **/
@Service
public class CompanyServiceImpl extends ServiceImpl<CompanyMapper, Company> implements CompanyService {

    @Resource
    private CompanyMapper companyMapper;

    @Resource
    private JobMapper jobMapper;

    @Override
    public List<Company> findAll(JobPageDto jobPageDto) {
        QueryWrapper<Company> wrapper = new QueryWrapper<>();
        wrapper.eq("is_deleted", false);
        IPage<Company> page = new Page<>(jobPageDto.getCurrentPage(), jobPageDto.getPageSize());
        return companyMapper.selectPage(page, wrapper).getRecords();
    }

    @Override
    public int addCompany(CompanyDto companyDto) {
        Company company = Company.builder()
                .name(companyDto.getName())
                .tag(companyDto.getTag())
                .logo(companyDto.getLogo())
                .workers(companyDto.getWorkers())
                .type(companyDto.getType())
                .description(companyDto.getDescription())
                .workingTime(companyDto.getWorkingTime())
                .workingStatus(companyDto.getWorkingStatus())
                .jobNumbers(companyDto.getJobNumbers())
                .vacation(companyDto.getVacation())
                .address(companyDto.getAddress())
                .longitude(companyDto.getLongitude())
                .latitude(companyDto.getLatitude())
                .isDeleted(false)
                .gmtCreate(Timestamp.valueOf(LocalDateTime.now()))
                .gmtModified(Timestamp.valueOf(LocalDateTime.now()))
                .build();
        return companyMapper.insert(company);
    }

    @Override
    public int update(CompanyDto companyDto) {
        Company company = new Company();
        UpdateWrapper<Company> wrapper = new UpdateWrapper<>();
        wrapper.set("name",companyDto.getName())
                .set("tag",companyDto.getTag())
                .set("logo",companyDto.getLogo())
                .set("workers",companyDto.getWorkers())
                .set("type",companyDto.getType())
                .set("description",companyDto.getDescription())
                .set("working_time", companyDto.getWorkingTime())
                .set("working_status",companyDto.getWorkingStatus())
                .set("vacation", companyDto.getVacation())
                .set("address", companyDto.getAddress())
                .set("longitude", companyDto.getLongitude())
                .set("latitude", companyDto.getLatitude())
                .set("gmt_modified", Timestamp.valueOf(LocalDateTime.now()))
                .eq("pk_company_id", companyDto.getId());
        return companyMapper.update(company, wrapper);
    }

    @Override
    public int delete(JobDto jobDto) {
        Company company = new Company();
        Job job = new Job();
        UpdateWrapper<Company> wrapper = new UpdateWrapper<>();
        wrapper.set("is_deleted", true)
                .eq("pk_company_id", jobDto.getId());
        //把公司相关的职位删除
        UpdateWrapper<Job> jobWrapper = new UpdateWrapper<>();
        jobWrapper.set("is_deleted", true)
                .eq("company_id", jobDto.getId());
        jobMapper.update(job, jobWrapper);
        return companyMapper.update(company, wrapper);
    }


}
