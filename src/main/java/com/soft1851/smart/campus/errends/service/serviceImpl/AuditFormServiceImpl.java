package com.soft1851.smart.campus.errends.service.serviceImpl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.soft1851.smart.campus.constant.ResponseResult;
import com.soft1851.smart.campus.constant.ResultCode;
import com.soft1851.smart.campus.errends.domain.dto.AuditFormDto;
import com.soft1851.smart.campus.errends.domain.dto.FinshOrderDto;
import com.soft1851.smart.campus.errends.domain.entity.AuditForm;
import com.soft1851.smart.campus.errends.domain.entity.ReviewForm;
import com.soft1851.smart.campus.errends.domain.entity.Transaction;
import com.soft1851.smart.campus.errends.domain.vo.ErrensCountVo;
import com.soft1851.smart.campus.errends.mapper.*;
import com.soft1851.smart.campus.errends.repository.AuditFormRepository;
import com.soft1851.smart.campus.errends.service.AuditFormService;
import com.soft1851.smart.campus.errends.util.PageUtil;
import com.soft1851.smart.campus.errends.util.SnowFlake;
import com.soft1851.smart.campus.mapper.UserAccountMapper;
import com.soft1851.smart.campus.model.entity.SysUser;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
/**
 * @author wl
 * @ClassNameAuditFormServiceImpl
 * @Description TODO
 * @Date 2020/6/16
 * @Version 1.0
 */
@Service
@Transactional(rollbackFor = RuntimeException.class)
@Slf4j
public class AuditFormServiceImpl implements AuditFormService {
    @Resource
    private AuditFormRepository auditFormRepository;
    @Resource
    private ReviwFormMapper reviwFormMapper;
    @Resource
    private DeliveryOrderMapper deliveryOrderMapper;
    @Resource
    private TransactionMapper transactionMapper;
    @Resource
    private AuditFormMapper auditFormMapper;
    @Resource
    private UserAccountMapper userAccountMapper;
    @Resource
    private SysUserMapper sysUserMapper;

    @Override
    public ResponseResult saveAuditForm(AuditFormDto auditFormDto) {
        SnowFlake snowFlake = new SnowFlake(1, 3);
        long id = snowFlake.nextId();
        AuditForm auditForm = AuditForm.builder()
                .endTime(Timestamp.valueOf(LocalDateTime.now()))
                .founderId(auditFormDto.getFounderId()).gmtCreate(Timestamp.valueOf(LocalDateTime.now()))
                .gmtModified(Timestamp.valueOf(LocalDateTime.now())).id(String.valueOf(id)).isDeleted(false)
                .remark(auditFormDto.getRemark()).reviewerId(auditFormDto.getReviewerId()).stauts(auditFormDto.getStauts())
                .build();
        auditFormRepository.save(auditForm);
        //更改申请表状态
        QueryWrapper<ReviewForm> queryWrapper = new QueryWrapper();
        queryWrapper.eq("requester_id", auditFormDto.getFounderId());
        ReviewForm reviewForm = ReviewForm.builder().status((long) auditFormDto.getStauts()).build();
        reviwFormMapper.update(reviewForm, queryWrapper);
        return ResponseResult.success();
    }

    @Override
    public ResponseResult   selectErrends(FinshOrderDto finshOrderDto) {
        List<ErrensCountVo> list = new ArrayList<>();
        //查询申请表中数据  查询所有跑腿
        QueryWrapper<ReviewForm> reviewFormQueryWrapper = new QueryWrapper<>();
        reviewFormQueryWrapper.eq("status", finshOrderDto.getStatus());
        List<ReviewForm> reviewForms = reviwFormMapper.selectList(reviewFormQueryWrapper);
        log.info(String.valueOf(reviewForms));
        if (reviewForms.size() != 0) {
            //通过状态
            if (finshOrderDto.getStatus() == 1) {
                for (ReviewForm reviewForm : reviewForms) {

                    //统计订单量
                    QueryWrapper<Transaction> transactionQueryWrapper = new QueryWrapper<>();
                    transactionQueryWrapper.eq("errands_id", reviewForm.getRequesterId());
                    Integer transaction = transactionMapper.selectCount(transactionQueryWrapper);
                    QueryWrapper<AuditForm> auditFormQueryWrapper = new QueryWrapper<>();
                    //查出审核人
                    auditFormQueryWrapper.select("founder_id", "reviewer_id").eq("founder_id", reviewForm.getRequesterId());
                    AuditForm auditForm = auditFormMapper.selectOne(auditFormQueryWrapper);
                    QueryWrapper<SysUser> userAccountQueryWrapper = new QueryWrapper<>();
                    log.info(String.valueOf(auditForm));
                    userAccountQueryWrapper.select("sys_user_name").eq("sys_user_phone_number", auditForm.getReviewerId());
                    SysUser sysUser = sysUserMapper.selectOne(userAccountQueryWrapper);
                    ErrensCountVo errensCountVo = ErrensCountVo.builder()
                            .countOrder(transaction)
                            .idCard(reviewForm.getIdCardFront())
                            .jobNumber(reviewForm.getRequesterId())
                            .name(reviewForm.getRequesterName())
                            .phoneNumber(reviewForm.getRequesterPhonenumber())
                            .reviewerName(sysUser.getSysUserName())
                            .status(finshOrderDto.getStatus())
                            .build();
                    list.add(errensCountVo);
                }

                //分页减一
                Pageable pageable = PageRequest.of(finshOrderDto.getNum(), finshOrderDto.getSize());
                org.springframework.data.domain.Page<ErrensCountVo> deliveryOderInformationVos = PageUtil.listConvertToPage(list, pageable);
                int total = (int) deliveryOderInformationVos.getTotalElements();
                List<ErrensCountVo> content = deliveryOderInformationVos.getContent();
                Map<String, Object> map = new HashMap<>();
                map.put("order", content);
                map.put("total", total);
                return ResponseResult.success(map);
                //不通过状态
            } else if (finshOrderDto.getStatus() == 2) {
                for (ReviewForm reviewForm : reviewForms) {
                    //不通过
                    QueryWrapper<AuditForm> auditFormQueryWrapper = new QueryWrapper<>();
                    //查出审核人
                    auditFormQueryWrapper.select("founder_id", "remark", "reviewer_id").eq("founder_id", reviewForm.getRequesterId());
                    AuditForm auditForm = auditFormMapper.selectOne(auditFormQueryWrapper);
                    QueryWrapper<SysUser> userAccountQueryWrapper = new QueryWrapper<>();
                    userAccountQueryWrapper.select("sys_user_name").eq("sys_user_phone_number", auditForm.getReviewerId());
                    SysUser sysUser = sysUserMapper.selectOne(userAccountQueryWrapper);

                    ErrensCountVo errensCountVo = ErrensCountVo.builder()
                            .idCard(reviewForm.getIdCardFront())
                            .jobNumber(reviewForm.getRequesterId())
                            .name(reviewForm.getRequesterName())
                            .phoneNumber(reviewForm.getRequesterPhonenumber())
                            .reviewerName(sysUser.getSysUserName())
                            .status(finshOrderDto.getStatus())
                            .remark(auditForm.getRemark())
                            .build();
                    list.add(errensCountVo);
                }
                //分页减一
                Pageable pageable = PageRequest.of(finshOrderDto.getNum(), finshOrderDto.getSize());
                org.springframework.data.domain.Page<ErrensCountVo> deliveryOderInformationVos = PageUtil.listConvertToPage(list, pageable);
                int total = (int) deliveryOderInformationVos.getTotalElements();
                List<ErrensCountVo> content = deliveryOderInformationVos.getContent();
                Map<String, Object> map = new HashMap<>();
                map.put("order", content);
                map.put("total", total);
                return ResponseResult.success(map);
            }


            return ResponseResult.failure(ResultCode.RESULT_CODE_DATA_NONE);

        }


        return null;
    }

    @Override
    public ResponseResult selectReviewForm(FinshOrderDto finshOrderDto) {
        List<ReviewForm> list = new ArrayList<>();
        QueryWrapper<ReviewForm> queryWrapper = new QueryWrapper<>();
        queryWrapper.select("gmt_create","id_card_back","id_card_front","remark",
                "requester_id","requester_name","requester_phonenumber","status","id").eq("status", finshOrderDto.getStatus());
        List<ReviewForm> reviewForms = reviwFormMapper.selectList(queryWrapper);
        for (ReviewForm reviewForm : reviewForms) {
            list.add(reviewForm);
        }

        //分页减一
        Pageable pageable = PageRequest.of(finshOrderDto.getNum(), finshOrderDto.getSize());
        org.springframework.data.domain.Page<ReviewForm> reviewFormPage = PageUtil.listConvertToPage(list, pageable);
        List<ReviewForm> content = reviewFormPage.getContent();
        long total = reviewFormPage.getTotalElements();
        Map<String, Object> map = new HashMap<>();
        map.put("order", content);
        map.put("total", total);
        return ResponseResult.success(map);


    }

    @Override
    public ResponseResult DeleteErrends(FinshOrderDto finshOrderDto) {
        log.info(String.valueOf(finshOrderDto.getReqId()));
        //循环删除

        for (int i = 0; i < finshOrderDto.getReqId().size(); i++) {
            QueryWrapper<ReviewForm> reviewFormQueryWrapper = new QueryWrapper<>();
            reviewFormQueryWrapper.eq("requester_id", finshOrderDto.getReqId().get(i));
            int delete = reviwFormMapper.delete(reviewFormQueryWrapper);

            if (delete == 1) {
                QueryWrapper<AuditForm> auditFormQueryWrapper = new QueryWrapper<>();
                auditFormQueryWrapper.eq("founder_id", finshOrderDto.getReqId().get(i));
                auditFormMapper.delete(auditFormQueryWrapper);

            }
        }

        return ResponseResult.success();

    }

}
