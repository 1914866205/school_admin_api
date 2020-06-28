package com.soft1851.smart.campus.service.Impl;

import com.alibaba.fastjson.JSONArray;
import com.soft1851.smart.campus.constant.ResponseResult;
import com.soft1851.smart.campus.constant.ResultCode;
import com.soft1851.smart.campus.mapper.SysBorrowMapper;
import com.soft1851.smart.campus.model.dto.BorrowDto;
import com.soft1851.smart.campus.model.dto.BorrowInsertDto;
import com.soft1851.smart.campus.model.dto.PageDto;
import com.soft1851.smart.campus.model.dto.TimeBorrowPageDto;
import com.soft1851.smart.campus.model.entity.SysBorrow;
import com.soft1851.smart.campus.repository.SysBorrowRepository;
import com.soft1851.smart.campus.service.SysBorrowService;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * @Description TODO
 * @Author 涛涛
 * @Date 2020/6/3 12:57
 * @Version 1.0
 **/
@Service
public class SysBorrowServiceImpl implements SysBorrowService {
    @Resource
    private SysBorrowRepository sysBorrowRepository;
    @Resource
    private SysBorrowMapper sysBorrowMapper;

    @Override
    public ResponseResult findAllByPage(PageDto pageDto) {
        Pageable pageable = PageRequest.of(
                pageDto.getCurrentPage(),
                pageDto.getPageSize(),
                Sort.Direction.ASC,
                "pkBorrowId");
        Page<SysBorrow> sysBorrow = sysBorrowRepository.findAll(pageable);
        return ResponseResult.success(sysBorrow.getContent());
    }

    @Override
    public ResponseResult getBorrowByTime(BorrowDto borrowDto) {
        //分页要减一
        Pageable pageable = PageRequest.of(
                borrowDto.getCurrentPage(),
                borrowDto.getPageSize());
        //查询所有符合条件的
        List<SysBorrow> sysBorrows = sysBorrowRepository.getSysBorrowsByGmtCreateBetween(Timestamp.valueOf(borrowDto.getStartTime()), Timestamp.valueOf(borrowDto.getEndTime()));
        Page<SysBorrow> sysBorrowInfo = new PageImpl<SysBorrow>(sysBorrows, pageable, sysBorrows.size());
        return ResponseResult.success(sysBorrowInfo.getContent());
    }

    @Override
    public ResponseResult borrowInsert(BorrowInsertDto borrowInsertDto) {
        SysBorrow sysBorrow = SysBorrow.builder()
                .borrowBookName(borrowInsertDto.getBorrowBookName())
                .borrowUserNumber(borrowInsertDto.getBorrowUserNumber())
                .borrowUserPhone(borrowInsertDto.getBorrowUserPhone())
                .borrowUserName(borrowInsertDto.getBorrowUserName())
                .borrowBookId(borrowInsertDto.getBorrowBookId())
                .isReturned(false)
                .gmtReturn(Timestamp.valueOf(LocalDateTime.now()))
                .isDeleted(false)
                .gmtCreate(Timestamp.valueOf(LocalDateTime.now()))
                .gmtModified(Timestamp.valueOf(LocalDateTime.now()))
                .build();
        sysBorrowRepository.save(sysBorrow);
        return ResponseResult.success();
    }

    /**
     * 查询时间内的数据
     *
     * @param timeBorrowPageDto
     * @return
     */
    @Override
    public ResponseResult getSysBorrowsByTime(TimeBorrowPageDto timeBorrowPageDto) {
        JSONArray times = JSONArray.parseArray(timeBorrowPageDto.getTime());
        String startTime = times.get(0).toString();
        String endTime = times.get(1).toString();
        Timestamp timestamp = Timestamp.valueOf(startTime);
        Timestamp timestamp1 = Timestamp.valueOf(endTime);
        List<SysBorrow> sysBorrowList = null;
        try {
            sysBorrowList = sysBorrowMapper.getSysBorrowsByTime(timestamp, timestamp1, timeBorrowPageDto.getCurrentPage(), timeBorrowPageDto.getPageSize());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ResponseResult.success(sysBorrowList);
    }

    @Override
    public ResponseResult setIsReturn(Long pkBorrowId) {
        SysBorrow sysBorrow = sysBorrowRepository.findByPkBorrowId(pkBorrowId);
        if (sysBorrow != null) {
            Timestamp timestamp = Timestamp.valueOf(LocalDateTime.now());
            sysBorrowRepository.setIsReturn(pkBorrowId, timestamp);
            return ResponseResult.success("还书成功");
        } else {
            return ResponseResult.failure(ResultCode.RESULT_CODE_DATA_NONE);
        }
    }

    @Override
    public ResponseResult deletedBatch(String ids) {
        //判断是否有数据
        if (ids.length() != 0) {
            //将接收到的ids字符串，使用逗号分割
            String[] idArr = ids.split(",");
            List<Long> idsList = new ArrayList<Long>();
            for (String id : idArr) {
                //遍历所有id存入到list
                idsList.add(Long.valueOf(id));
            }
            sysBorrowRepository.deleteBatchByPkBorrowIds(idsList);
            return ResponseResult.success("批量删除成功");
        } else {
            return ResponseResult.failure(ResultCode.PARAM_IS_BLANK);
        }
    }

    @Override
    public ResponseResult deleteSysBorrows(Long sysPkSysBorrows) {
        SysBorrow sysBorrow = sysBorrowRepository.findByPkBorrowId(sysPkSysBorrows);
        if (sysBorrow != null) {
            sysBorrowRepository.deleteSysBorrow(sysPkSysBorrows);
            return ResponseResult.success("删除成功");
        } else {
            return ResponseResult.failure(ResultCode.RESULT_CODE_DATA_NONE);
        }
    }




}
