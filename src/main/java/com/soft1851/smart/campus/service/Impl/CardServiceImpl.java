package com.soft1851.smart.campus.service.Impl;

import com.soft1851.smart.campus.constant.ResponseResult;
import com.soft1851.smart.campus.constant.ResultCode;
import com.soft1851.smart.campus.model.dto.PageDto;
import com.soft1851.smart.campus.model.entity.SysCard;
import com.soft1851.smart.campus.repository.CardRepository;
import com.soft1851.smart.campus.service.CardService;
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
 * @ClassName CardServiceImpl
 * @Description TODO
 * @Author 田震
 * @Date 2020/5/26
 **/
@Service
public class CardServiceImpl implements CardService {
    @Resource
    private CardRepository cardRepository;

    @Override
    public ResponseResult findAllByPage(PageDto pageDto) {
        Pageable pageable = PageRequest.of(
                pageDto.getCurrentPage(),
                pageDto.getPageSize(),
                Sort.Direction.ASC,
                "pk_card_id");
        Page<SysCard> sysCards = cardRepository.findAll(pageable);
        return ResponseResult.success(sysCards.getContent());
    }

    @Override
    public ResponseResult insertAll(List<SysCard> sysCards) {
        List<SysCard> sysCardList=new ArrayList<>();
        return ResponseResult.success(sysCardList);
    }

    @Override
    public ResponseResult deleteCard(Long pkCardId) {
        cardRepository.deleteByPkCardId(pkCardId);
        return ResponseResult.success();
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
            cardRepository.deleteBatch(idsList);
            return ResponseResult.success("删除成功");
        } else {
            return ResponseResult.failure(ResultCode.PARAM_IS_BLANK);
        }
    }

    @Override
    public ResponseResult updateCard(SysCard sysCard) {
        SysCard card=cardRepository.findByPkCardId(sysCard.getPkCardId());
        if (card.getStatus()){
            card.setCardPassword(sysCard.getCardPassword());
            card.setJobNumber(sysCard.getJobNumber());
            card.setCardBalance(sysCard.getCardBalance());
            cardRepository.saveAndFlush(card);
            return ResponseResult.success(card);
        }
        return ResponseResult.failure(ResultCode.DATABASE_ERROR);
    }

    @Override
    public ResponseResult insert(SysCard sysCard) {
        if (cardRepository.findByCardNumber(sysCard.getCardNumber())==null){
            SysCard sysCard1=new SysCard();
            sysCard1.setJobNumber(sysCard.getJobNumber());
            sysCard1.setCardNumber(sysCard.getCardNumber());
            sysCard1.setCardBalance(sysCard.getCardBalance());
            sysCard1.setCardPassword(sysCard.getCardPassword());
            sysCard1.setIsDeleted(false);
            sysCard1.setStatus(false);
             sysCard1.setGmtCreate(Timestamp.valueOf(LocalDateTime.now()));
            sysCard1.setGmtModified(Timestamp.valueOf(LocalDateTime.now()));
            cardRepository.save(sysCard1);
            return ResponseResult.success(ResultCode.SUCCESS);
        }
        return ResponseResult.failure(ResultCode.DATABASE_ERROR);
    }

    @Override
    public ResponseResult updateStatus(Long pkCardId, Boolean Status) {
        return ResponseResult.success(cardRepository.updateStatus(pkCardId,Status));
    }


    @Override
    public ResponseResult getAllSysCard(PageDto pageDto) {
        Pageable pageable = PageRequest.of(
                pageDto.getCurrentPage(),
                pageDto.getPageSize(),
                Sort.Direction.ASC,
                "pk_card_id");
        Page<SysCard> sysCards = cardRepository.getAllSysCard(pageable);
        return ResponseResult.success(sysCards.getContent());
    }

}