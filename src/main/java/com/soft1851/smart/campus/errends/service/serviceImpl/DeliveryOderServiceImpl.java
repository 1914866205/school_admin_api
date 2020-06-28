package com.soft1851.smart.campus.errends.service.serviceImpl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.soft1851.smart.campus.constant.ResponseResult;
import com.soft1851.smart.campus.errends.domain.dto.DeliveryOrderDto;
import com.soft1851.smart.campus.errends.domain.dto.FinshOrderDto;
import com.soft1851.smart.campus.errends.domain.entity.CancleDeliveryOrder;
import com.soft1851.smart.campus.errends.domain.entity.Commodity;
import com.soft1851.smart.campus.errends.domain.entity.DeliveryOrder;
import com.soft1851.smart.campus.errends.domain.entity.Transaction;
import com.soft1851.smart.campus.errends.domain.vo.DeliveryOderInformationVo;
import com.soft1851.smart.campus.errends.mapper.*;
import com.soft1851.smart.campus.errends.service.DeliveryOrderService;
import com.soft1851.smart.campus.errends.util.PageUtil;
import com.soft1851.smart.campus.mapper.UserAccountMapper;
import com.soft1851.smart.campus.model.entity.UserAccount;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author wl
 * @ClassNameDeliveryOderServceImpl
 * @Description TODO
 * @Date 2020/6/14
 * @Version 1.0
 */
@Service
@Slf4j
@Transactional(rollbackFor = RuntimeException.class)
public class DeliveryOderServiceImpl implements DeliveryOrderService {
    @Resource
    private DeliveryOrderMapper deliveryOrderMapper;
    @Resource
    private TransactionMapper transactionMapper;
    @Resource
    private CommodityMapper commodityMapper;
    @Resource
    private UserAccountMapper userAccountMapper;
    @Resource
    private CancleDeliveryOderMapper cancleDeliveryOderMapper;

    @Override
    public ResponseResult getOrder(FinshOrderDto finshOrderDto) {
        List<DeliveryOderInformationVo> list = new ArrayList<>();

        //分页减一
        Pageable pageable = PageRequest.of(finshOrderDto.getNum(), finshOrderDto.getSize());

        //查询所有完成的值
        QueryWrapper<DeliveryOrder> deliveryOrderQueryWrapper = new QueryWrapper<>();
        deliveryOrderQueryWrapper.orderByDesc("oder_create_time").eq("status", finshOrderDto.getStatus());
        List<DeliveryOrder> deliveryOrders = deliveryOrderMapper.selectList(deliveryOrderQueryWrapper);
log.info(String.valueOf(deliveryOrders));
        for (DeliveryOrder deliveryOrder : deliveryOrders) {
            //查出商品信息
            Commodity commodity = commodityMapper.selectById(deliveryOrder.getCommodityId());
            //查出发单人信息
            QueryWrapper<UserAccount> userAccountQueryWrapper = new QueryWrapper<>();
            userAccountQueryWrapper.select("nickname", "job_number", "phone_number")
                    .eq("job_number", deliveryOrder.getFounderId());
            UserAccount userAccount = userAccountMapper.selectOne(userAccountQueryWrapper);
            log.info(String.valueOf(userAccount));
            //根据不同状态追加不同值
            if (finshOrderDto.getStatus() == 0 || finshOrderDto.getStatus() == 1) {
                if (finshOrderDto.getStatus() == 1) {

                    QueryWrapper<CancleDeliveryOrder>cancleDeliveryOrderQueryWrapper=new QueryWrapper<>();
                    cancleDeliveryOrderQueryWrapper.select("cancle_time").eq("oder_id",deliveryOrder.getId());

                    CancleDeliveryOrder cancleDeliveryOrder = cancleDeliveryOderMapper.selectOne(cancleDeliveryOrderQueryWrapper);
                    DeliveryOderInformationVo deliveryOderInformationVo = DeliveryOderInformationVo.builder()
                            .amount(deliveryOrder.getAmount())
                            .cancleTime(cancleDeliveryOrder.getCancleTime())
                            .priceRange(commodity.getPriceRang()).type(commodity.getType())
                            .deliveryTime(deliveryOrder.getDeliveryTime())
                            .destination(deliveryOrder.getDestination())
                            .founderId(userAccount.getJobNumber())
                            .founderName(deliveryOrder.getFounderName())
                            .founderPhonenumber(deliveryOrder.getFounderPhonenumber())
                            .id(deliveryOrder.getId()).oderCreateTime(deliveryOrder.getOderCreateTime())
                            .originAddress(deliveryOrder.getOriginAddress()).receiverName(deliveryOrder.getReceiverName())
                            .receiverPhoneNumber(deliveryOrder.getReceiverPhoneNumber())
                            .build();
                    list.add(deliveryOderInformationVo);
                    //发布的信息
                } else if (finshOrderDto.getStatus() == 0) {
                    log.info(deliveryOrder.getFounderName());
                    DeliveryOderInformationVo deliveryOderInformationVo = DeliveryOderInformationVo.builder()
                            .amount(deliveryOrder.getAmount())
                            .priceRange(commodity.getPriceRang()).type(commodity.getType()).deliveryTime(deliveryOrder.getDeliveryTime())
                            .destination(deliveryOrder.getDestination())
                            .founderId(userAccount.getJobNumber())
                            .founderName(deliveryOrder.getFounderName())
                            .founderPhonenumber(deliveryOrder.getFounderPhonenumber())
                            .id(deliveryOrder.getId())
                            .oderCreateTime(deliveryOrder.getOderCreateTime())
                            .originAddress(deliveryOrder.getOriginAddress())
                            .receiverName(deliveryOrder.getReceiverName())
                            .receiverPhoneNumber(deliveryOrder.getReceiverPhoneNumber())
                            .build();
                    list.add(deliveryOderInformationVo);
                }
            } else {
                //查询交易表
                QueryWrapper<Transaction> transactionQueryWrapper = new QueryWrapper<>();
                transactionQueryWrapper
                        .select("errands_id", "order_id", "transaction_create", "transaction_end")
                        .eq("status", finshOrderDto.getStatus())
                        .eq("order_id", deliveryOrder.getId())
                        .orderByDesc("transaction_create");
                Transaction transaction = transactionMapper.selectOne(transactionQueryWrapper);

                //查出跑腿
                QueryWrapper<UserAccount> errendsAccountQueryWrapper = new QueryWrapper<>();
                errendsAccountQueryWrapper.select("nickname", "job_number", "phone_number")
                        .eq("job_number", transaction.getErrandsId());
                UserAccount errends = userAccountMapper.selectOne(userAccountQueryWrapper);
                //完成 进行  被抢单  抢单表都有数据
                DeliveryOderInformationVo deliveryOderInformationVo = DeliveryOderInformationVo.builder()
                        .amount(deliveryOrder.getAmount())
                        .priceRange(commodity.getPriceRang()).type(commodity.getType()).deliveryTime(deliveryOrder.getDeliveryTime())
                        .destination(deliveryOrder.getDestination())
                        .founderId(userAccount.getJobNumber())
                        .founderName(deliveryOrder.getFounderName())
                        .founderPhonenumber(deliveryOrder.getFounderPhonenumber())
                        .id(deliveryOrder.getId())
                        .oderCreateTime(deliveryOrder.getOderCreateTime())
                        .originAddress(deliveryOrder.getOriginAddress())
                        .receiverName(deliveryOrder.getReceiverName())
                        .receiverPhoneNumber(deliveryOrder.getReceiverPhoneNumber())
                        .remark(deliveryOrder.getRemark())
                        .name(errends.getUserName()).errendsPhoneNumber(errends.getPhoneNumber())
                        .finshTime(transaction.getTransactionEnd())
                        .build();
                list.add(deliveryOderInformationVo);
            }


        }
        org.springframework.data.domain.Page<DeliveryOderInformationVo> deliveryOderInformationVos = PageUtil.listConvertToPage(list, pageable);
        int total = (int) deliveryOderInformationVos.getTotalElements();
        List<DeliveryOderInformationVo> content = deliveryOderInformationVos.getContent();
        Map<String, Object> map = new HashMap<>();
        map.put("order", content);
        map.put("total", total);
        return ResponseResult.success(map);
    }



    @Override
    public ResponseResult getOrderByFoundIdOrFounderName(DeliveryOrderDto deliveryOrderDto) {
    //        QueryWrapper<DeliveryOderInformationVo> pa = (QueryWrapper<DeliveryOderInformationVo>) deliveryOrderMapper.getByOrderIdOrFounderName(deliveryOrderDto);
    //        List<DeliveryOderInformationVo> page = pa
            return ResponseResult.success(deliveryOrderMapper.getByOrderIdOrFounderName(deliveryOrderDto));
    }
}