package com.soft1851.smart.campus.service.Impl;

import com.alibaba.fastjson.JSONArray;
import com.soft1851.smart.campus.constant.ResponseResult;
import com.soft1851.smart.campus.constant.ResultCode;
import com.soft1851.smart.campus.mapper.SysFeedbackMapper;
import com.soft1851.smart.campus.model.dto.PageDto;
import com.soft1851.smart.campus.model.dto.TimeBorrowPageDto;
import com.soft1851.smart.campus.model.dto.UpdateSysFeedbackDto;
import com.soft1851.smart.campus.model.entity.SysFeedback;
import com.soft1851.smart.campus.repository.SysFeedbackRepository;
import com.soft1851.smart.campus.service.SysFeedbackService;
import com.soft1851.smart.campus.utils.ExcelConsumer;
import com.soft1851.smart.campus.utils.ExportDataAdapter;
import com.soft1851.smart.campus.utils.ThreadPool;
import lombok.SneakyThrows;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;

/**
 * @author Tao
 * @version 1.0
 * @ClassName SysFeedbackServiceImpl
 * @Description TODO
 * @date 2020-06-02 22:32
 **/
@Service
@Transactional(rollbackFor = RuntimeException.class)
public class SysFeedbackServiceImpl implements SysFeedbackService {

    @Resource
    private SysFeedbackRepository sysFeedbackRepository;
    @Resource
    private SysFeedbackMapper sysFeedbackMapper;

    /**
     * 分页查询所有
     *
     * @param pageDto
     * @return
     */
    @Override
    public ResponseResult findAllSysFeedback(PageDto pageDto) {
        Pageable pageable = PageRequest.of(
                pageDto.getCurrentPage(),
                pageDto.getPageSize(),
                Sort.Direction.DESC,
                "gmt_create");
        Page<SysFeedback> sysFeedbacks = sysFeedbackRepository.getAllSysFeedback(pageable);
        return ResponseResult.success(sysFeedbacks.getContent());
    }

    @Override
    public ResponseResult increaseSysFeedback(SysFeedback sysFeedback) {
        boolean isTitle = sysFeedback.getTitle() != null;
        boolean isContent = sysFeedback.getContent() != null;
        if (isTitle && isContent) {
            SysFeedback sysFeedback1 = SysFeedback.builder()
                    .title(sysFeedback.getTitle())
                    .content(sysFeedback.getContent())
                    .contactWay(sysFeedback.getContactWay())
                    .isHandled(false)
                    .gmtCreate(Timestamp.valueOf(LocalDateTime.now()))
                    .gmtModified(Timestamp.valueOf(LocalDateTime.now()))
                    .isDeleted(false)
                    .build();
            sysFeedbackRepository.save(sysFeedback1);
            return ResponseResult.success("新增反馈成功");
        } else {
            return ResponseResult.failure(ResultCode.PARAM_NOT_COMPLETE);
        }
    }

    /**
     * 修改反馈接口
     *
     * @param updateSysFeedbackDto
     * @return
     */
    @Override
    public ResponseResult modificationSysFeedback(UpdateSysFeedbackDto updateSysFeedbackDto) {
        sysFeedbackRepository.updateSysFeedback(updateSysFeedbackDto);
        SysFeedback sysFeedback = sysFeedbackRepository.findSysFeedbackByPkFeedbackId(updateSysFeedbackDto.getPkFeedbackId());
        return ResponseResult.success();
    }

    /**
     * 单个删除反馈
     *
     * @param pkFeedbackId
     * @return
     */
    @Override
    public ResponseResult deletionSysFeedback(Long pkFeedbackId) {
        SysFeedback sysFeedback = sysFeedbackRepository.findSysFeedbackByPkFeedbackId(pkFeedbackId);
        if (sysFeedback != null) {
            sysFeedbackRepository.deleteByPkFeedbackId(pkFeedbackId);
            return ResponseResult.success();
        } else {
            return ResponseResult.failure(ResultCode.RESULT_CODE_DATA_NONE);
        }
    }

    /**
     * 批量删除
     *
     * @param ids
     * @return
     */
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
            sysFeedbackRepository.deleteBatchByPkFeedbackId(idsList);
            return ResponseResult.success();
        } else {
            return ResponseResult.failure(ResultCode.PARAM_IS_BLANK);
        }
    }

    /**
     * 逻辑删除
     * @param pkFeedbackId
     * @return
     */
    @Override
    public ResponseResult deleteSysFeedback(Long pkFeedbackId) {
        sysFeedbackRepository.deleteSysFeedback(pkFeedbackId);
        return ResponseResult.success();
    }

    /**
     * 批量逻辑删除
     * @param ids
     * @return
     */
    @Override
    public ResponseResult deleteBatchByPkFeedbackId(String ids) {
        //判断是否有数据
        if (ids.length() != 0) {
            //将接收到的ids字符串，使用逗号分割
            String[] idArr = ids.split(",");
            List<Long> idsList = new ArrayList<Long>();
            for (String id : idArr) {
                //遍历所有id存入到list
                idsList.add(Long.valueOf(id));
            }
            sysFeedbackRepository.deleteBatchByPkFeedbackId(idsList);
            return ResponseResult.success();
        } else {
            return ResponseResult.failure(ResultCode.PARAM_IS_BLANK);
        }
    }

    @Override
    public ResponseResult getSysFeedbackByTime(TimeBorrowPageDto timeBorrowPageDto) {
        JSONArray times = JSONArray.parseArray(timeBorrowPageDto.getTime());
        System.out.println(times);
        String startTime = times.get(0).toString();
        String endTime = times.get(1).toString();
        Timestamp timestamp = Timestamp.valueOf(startTime);
        Timestamp timestamp1 = Timestamp.valueOf(endTime);
        List<SysFeedback> sysFeedbacks = null;
        try {
            sysFeedbacks = sysFeedbackMapper.getSysFeedbackByTime(timestamp,timestamp1,timeBorrowPageDto.getCurrentPage(),timeBorrowPageDto.getPageSize());
            System.out.println(sysFeedbacks);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ResponseResult.success(sysFeedbacks);
    }

    @Override
    @SneakyThrows
    public void exportData() {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        assert attributes != null;
        HttpServletResponse response = attributes.getResponse();
        assert response != null;
//        response.setContentType("application/vnd.ms-excel;charset=utf-8");
//        response.setHeader("Content-Disposition","attachment");
        String fileName = "sysFeedback.xls";
        response.setContentType("application/x-msdownload");
        response.setHeader("Content-Disposition","attachment;filename="+ URLEncoder.encode(fileName, StandardCharsets.UTF_8));
        //导出excel对象
        SXSSFWorkbook sxssfWorkbook = new SXSSFWorkbook(1000);
        //数据缓冲
        ExportDataAdapter<SysFeedback> exportDataAdapter = new ExportDataAdapter<>();
        //线程同步对象
        CountDownLatch latch = new CountDownLatch(2);
        //启动线程获取数据(生产者)
        ThreadPool.getExecutor().submit(() -> produceExportData(exportDataAdapter, latch));
        //启动线程导出数据（消费者）
        ThreadPool.getExecutor().submit(() -> new ExcelConsumer<>(SysFeedback.class, exportDataAdapter, sxssfWorkbook, latch, "反馈数据").run());
        latch.await();
        //使用字节流写数据
        OutputStream outputStream = null;
        outputStream = response.getOutputStream();
        sxssfWorkbook.write(outputStream);
        outputStream.flush();
        outputStream.close();
    }

    /**
     * 生产者生产数据
     *
     * @param exportDataAdapter
     * @param latch
     */
    private void produceExportData(ExportDataAdapter<SysFeedback> exportDataAdapter, CountDownLatch latch) {
        List<SysFeedback> songs = sysFeedbackMapper.selectList(null);
        songs.forEach(exportDataAdapter::addData);
//        log.info("数据生产完成");
        System.out.println("数据生产完成");
        //数据生产结束
        latch.countDown();
    }
}
