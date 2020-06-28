package com.soft1851.smart.campus.service.Impl;

import com.soft1851.smart.campus.model.entity.SysSemester;
import com.soft1851.smart.campus.repository.SysSemesterRepository;
import com.soft1851.smart.campus.service.SysSemesterService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.sql.Time;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

/**
 * @author xunmi
 * @ClassName SysSemesterServiceImpl
 * @Description TODO
 * @Date 2020/6/1
 * @Version 1.0
 **/
@Service
public class SysSemesterServiceImpl implements SysSemesterService {

    @Resource
    private SysSemesterRepository sysSemesterRepository;

    @Override
    public List<SysSemester> findAll() {
        return sysSemesterRepository.getSysSemesterByIsDeleted(false);
    }

    @Override
    public void insertSemester(SysSemester sysSemester) {
        sysSemester.setGmtCreate(Timestamp.valueOf(LocalDateTime.now()));
        sysSemester.setGmtModified(Timestamp.valueOf(LocalDateTime.now()));
        sysSemester.setIsDeleted(false);
        sysSemesterRepository.save(sysSemester);
    }

    @Override
    public void updateIsDeletedById(SysSemester semester) {
        System.out.println(semester.getPkSemesterId());
        sysSemesterRepository.updateIsDeletedById(semester);
    }

    @Override
    public void updateSemesterById(SysSemester sysSemester) {
        sysSemesterRepository.updateSemesterById(sysSemester);
    }
}
