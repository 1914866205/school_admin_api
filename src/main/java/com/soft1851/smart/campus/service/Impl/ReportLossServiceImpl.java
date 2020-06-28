package com.soft1851.smart.campus.service.Impl;

import com.soft1851.smart.campus.constant.ResponseResult;
import com.soft1851.smart.campus.constant.ResultCode;
import com.soft1851.smart.campus.model.dto.PageDto;
import com.soft1851.smart.campus.model.entity.ReportLoss;
import com.soft1851.smart.campus.model.entity.SysCard;
import com.soft1851.smart.campus.model.entity.UserAccount;
import com.soft1851.smart.campus.repository.CardRepository;
import com.soft1851.smart.campus.repository.ReportLossRepository;
import com.soft1851.smart.campus.repository.UserAccountRepository;
import com.soft1851.smart.campus.service.ReportLossService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName ReportServiceImpl
 * @Description TODO
 * @Author 田震
 * @Date 2020/6/1
 **/
@Service
public class ReportLossServiceImpl implements ReportLossService {
    @Resource
    private ReportLossRepository reportLossRepository;
    @Resource
    private UserAccountRepository userAccountRepository;
    @Resource
    private CardRepository cardRepository;

    /**
     * 分页查询所有信息
     *
     * @param pageDto
     * @return
     */
    @Override
    public ResponseResult findAllByPage(PageDto pageDto) {
        Pageable pageable = PageRequest.of(
                pageDto.getCurrentPage(),
                pageDto.getPageSize(),
                Sort.Direction.ASC,
                "pkReportLossId");
        Page<ReportLoss> reportLosses = reportLossRepository.findAll(pageable);
        return ResponseResult.success(reportLosses.getContent());
    }

    @Override
    public ResponseResult updateLossStatus(Long pkReportLossId, Boolean lossStatus) {
        return ResponseResult.success(reportLossRepository.updateLossStatus(pkReportLossId, lossStatus));
    }

    @Override
    public ResponseResult deleteReportLoss(Long pkReportLossId) {
        reportLossRepository.deleteBypkReportLossId(pkReportLossId);
        return ResponseResult.success("删除成功");

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
            reportLossRepository.deleteBatch(idsList);
            return ResponseResult.success("删除成功");
        } else {
            return ResponseResult.failure(ResultCode.PARAM_IS_BLANK);
        }
    }

    @Override
    public ResponseResult getAllReportLoss(PageDto pageDto) {
        Pageable pageable = PageRequest.of(
                pageDto.getCurrentPage(),
                pageDto.getPageSize(),
                Sort.Direction.ASC,
                "pk_report_loss_id");
        Page<ReportLoss> reportLosses = reportLossRepository.getAllReportLoss(pageable);
        return ResponseResult.success(reportLosses.getContent());
    }

    @Override
    public ResponseResult insertReportLoss(ReportLoss reportLoss) {
        //通过卡号查找帐号
        UserAccount userAccount = userAccountRepository.findUserAccountByCardNumber(reportLoss.getLossJobNumber());
        //通过挂失的卡号和卡密去查找一卡通数据
        SysCard sysCard = cardRepository.findSysCardByCardNumberAndCardPassword(reportLoss.getLossJobNumber(), reportLoss.getPassword());
        //用户是否存在
        if (userAccount != null) {
            //判断帐号状态 若为禁用状态无法挂失申请
            if (!userAccount.getStatus()) {
                //一卡通未申请过挂失
                if (!sysCard.getStatus()) {
                    ReportLoss reportLoss1 = ReportLoss.builder()
                            .gmtCreate(Timestamp.valueOf(LocalDateTime.now()))
                            .gmtModified(Timestamp.valueOf(LocalDateTime.now()))
                            .isDeleted(false)
                            .lossJobNumber(reportLoss.getLossJobNumber())
                            .lossName(userAccount.getUserName())
                            .lossPhone(userAccount.getPhoneNumber())
                            .lossStatus(false)
                            .password(reportLoss.getPassword())
                            .remark(reportLoss.getRemark())
                            .build();
                    //新增信息
                    reportLossRepository.save(reportLoss1);
                    //修改一卡通的状态为禁用
                    cardRepository.updateStatus(sysCard.getPkCardId(), true);
                    return ResponseResult.success("申请挂失成功");
                } else {
                    return ResponseResult.failure(ResultCode.CARD_REPORT);
                }
            } else {
                return ResponseResult.failure(ResultCode.USER_ACCOUNT_FORBIDDEN);
            }
        } else {
            return ResponseResult.failure(ResultCode.USER_ACCOUNT_PASSWORD_ERROR);
        }
    }

    @Override
    public ResponseResult adminInsertReportLoss(String cardNumber) {
        //通过卡号查询用户表 查看用户状态
        UserAccount userAccount = userAccountRepository.findUserAccountByCardNumber(cardNumber);
        //通过卡号查询一卡通数据
        SysCard sysCard = cardRepository.findByCardNumber(cardNumber);
        if (userAccount != null) {
            //一卡通状态为启用状态
           if (sysCard!=null){
               if (sysCard.getStatus()){
                   //禁用一卡通
                   cardRepository.updateStatus(sysCard.getPkCardId(), false);
                   //新增挂失记录
                   ReportLoss reportLoss1 = ReportLoss.builder()
                           .gmtCreate(Timestamp.valueOf(LocalDateTime.now()))
                           .gmtModified(Timestamp.valueOf(LocalDateTime.now()))
                           .isDeleted(false)
                           .lossJobNumber(userAccount.getJobNumber())
                           .lossName(userAccount.getUserName())
                           .lossPhone(userAccount.getPhoneNumber())
                           .lossStatus(true)
                           .password(sysCard.getCardPassword())
                           .remark("管理员操作一卡通挂失")
                           .build();
                   //新增信息
                   reportLossRepository.save(reportLoss1);
                   return ResponseResult.success("挂失成功！");
               }else {
                   return ResponseResult.failure(ResultCode.USER_ACCOUNT_FORBIDDEN);
               }

           }else {
               return ResponseResult.failure(ResultCode.RESULT_CODE_DATA_NONE);
           }
        } else {
            return ResponseResult.failure(ResultCode.RESULT_CODE_DATA_NONE);
        }

    }

}