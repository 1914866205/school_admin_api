package com.soft1851.smart.campus.service.Impl;

import com.alibaba.fastjson.JSONArray;
import com.soft1851.smart.campus.constant.ResponseResult;
import com.soft1851.smart.campus.constant.ResultCode;
import com.soft1851.smart.campus.mapper.SysStatementMapper;
import com.soft1851.smart.campus.model.dto.PageDto;
import com.soft1851.smart.campus.model.dto.TimeBorrowPageDto;
import com.soft1851.smart.campus.model.dto.UpdateSysStatementDto;
import com.soft1851.smart.campus.model.entity.SysStatement;
import com.soft1851.smart.campus.repository.SysStatementRepository;
import com.soft1851.smart.campus.service.SysStatementService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Tao
 * @version 1.0
 * @ClassName SysStatementServiceImpl
 * @Description TODO
 * @date 2020-06-02 10:34
 **/
@Service
public class SysStatementServiceImpl implements SysStatementService {
    @Resource
    private SysStatementRepository sysStatementRepository;
    @Resource
    private SysStatementMapper sysStatementMapper;

    /**
     * 分页查询所有声明数据
     *
     * @param pageDto
     * @return
     */
    @Override
    public ResponseResult findAllStatement(PageDto pageDto) {
        Pageable pageable = PageRequest.of(
                pageDto.getCurrentPage(),
                pageDto.getPageSize(),
                Sort.Direction.DESC,
                "gmt_create");
        Page<SysStatement> statementPage = sysStatementRepository.getAllSysStatement(pageable);
        return ResponseResult.success(statementPage.getContent());
    }

    @Override
    public ResponseResult increaseSysStatement(SysStatement sysStatement) {
        if (sysStatement.getStatementContent().length() != 0 &&
                sysStatement.getStatementTitle().length() != 0 &&
                sysStatement.getStatementType().length() != 0) {
            SysStatement sysStatement1 = SysStatement.builder()
                    .statementType(sysStatement.getStatementType())
                    .statementTitle(sysStatement.getStatementTitle())
                    .statementContent(sysStatement.getStatementContent())
                    .gmtCreate(Timestamp.valueOf(LocalDateTime.now()))
                    .gmtModified(Timestamp.valueOf(LocalDateTime.now()))
                    .isDeleted(false)
                    .build();
            System.out.println(sysStatement1);
            sysStatementRepository.save(sysStatement1);
            return ResponseResult.success();
        }else {
            return ResponseResult.failure(ResultCode.PARAM_NOT_COMPLETE);
        }
    }

    @Override
    public ResponseResult modificationSysStatement(UpdateSysStatementDto updateStatement) {
        sysStatementRepository.updateSysStatement(updateStatement);
        SysStatement sysStatement = sysStatementRepository.findSysStatementByPkStatementId(updateStatement.getPkStatementId());
        return ResponseResult.success(sysStatement);
    }


    @Override
    public ResponseResult deletionSysStatement(Long sysPkSysStatement) {
        SysStatement sysStatement1 = sysStatementRepository.findSysStatementByPkStatementId(sysPkSysStatement);
        if (sysStatement1!=null){
            sysStatementRepository.deleteByPkStatementId(sysPkSysStatement);
            return ResponseResult.success();
        }else {
           return ResponseResult.failure(ResultCode.RESULT_CODE_DATA_NONE);
        }
    }

    @Override
    public ResponseResult deletedBatch(String ids) {
        ids = ids.substring(1, ids.length() - 1);
        //判断是否有数据
        if (ids.length() != 0) {
            //将接收到的ids字符串，使用逗号分割
            String[] idArr = ids.split(",");
            List<Long> idsList = new ArrayList<Long>();
            for (String id : idArr) {
                //遍历所有id存入到list
                idsList.add(Long.valueOf(id));
            }
            sysStatementRepository.deleteBatch(idsList);
            return ResponseResult.success();
        } else {
            return ResponseResult.failure(ResultCode.PARAM_IS_BLANK);
        }
    }

    /**
     * 逻辑删除
     * @param sysPkSysStatement
     * @return
     */
    @Override
    public ResponseResult deleteSysStatement(Long sysPkSysStatement) {
        sysStatementRepository.deleteSysStatement(sysPkSysStatement);
        return ResponseResult.success();
    }

    @Override
    public ResponseResult deleteBatchByPkStatementId(String ids) {
        ids = ids.substring(1, ids.length() - 1);
        //判断是否有数据
        if (ids.length() != 0) {
            //将接收到的ids字符串，使用逗号分割
            String[] idArr = ids.split(",");
            List<Long> idsList = new ArrayList<Long>();
            for (String id : idArr) {
                //遍历所有id存入到list
                idsList.add(Long.valueOf(id));
            }
            sysStatementRepository.deleteBatchByPkStatementId(idsList);
            return ResponseResult.success();
        } else {
            return ResponseResult.failure(ResultCode.PARAM_IS_BLANK);
        }
    }

    @Override
    public ResponseResult getSysStatementsByTime(TimeBorrowPageDto timeBorrowPageDto) {
        JSONArray times = JSONArray.parseArray(timeBorrowPageDto.getTime());
        String startTime = times.get(0).toString();
        String endTime = times.get(1).toString();
        Timestamp timestamp = Timestamp.valueOf(startTime);
        Timestamp timestamp1 = Timestamp.valueOf(endTime);
        List<SysStatement> sysStatements = null;
        try {
            sysStatements = sysStatementMapper.getSysStatementByTime(timestamp,timestamp1,timeBorrowPageDto.getCurrentPage(),timeBorrowPageDto.getPageSize());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ResponseResult.success(sysStatements);
    }
}
