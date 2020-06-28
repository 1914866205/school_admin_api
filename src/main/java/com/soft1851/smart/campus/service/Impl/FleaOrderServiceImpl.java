package com.soft1851.smart.campus.service.Impl;

import com.soft1851.smart.campus.constant.ResponseResult;
import com.soft1851.smart.campus.model.dto.FleaOrderBatchIdDto;
import com.soft1851.smart.campus.model.dto.FleaOrderDto;
import com.soft1851.smart.campus.model.dto.PageDto;
import com.soft1851.smart.campus.repository.FleaOrderRepository;
import com.soft1851.smart.campus.service.FleaOrderService;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @Author yhChen
 * @Description
 * @Date 2020/6/12
 */
@Service
public class FleaOrderServiceImpl implements FleaOrderService {
    @Resource
    private FleaOrderRepository fleaOrderRepository;

    @Override
    public ResponseResult logicalDel(FleaOrderDto fleaOrderDto) {
        fleaOrderRepository.logicalDel(fleaOrderDto.getPkFleaOrderId());
        return ResponseResult.success("删除成功");
    }

    @Override
    public ResponseResult batchLogicalDel(FleaOrderBatchIdDto fleaOrderBatchIdDto) {
        int n = fleaOrderRepository.batchLogicalDel(fleaOrderBatchIdDto.getId());
        return ResponseResult.success("成功删除" + n + "条数据");
    }

    @Override
    public ResponseResult findAll(PageDto pageDto) {
        Pageable pageable = PageRequest.of(pageDto.getCurrentPage(), pageDto.getPageSize());
        return ResponseResult.success(fleaOrderRepository.findAllOrder(pageable));
    }
}
