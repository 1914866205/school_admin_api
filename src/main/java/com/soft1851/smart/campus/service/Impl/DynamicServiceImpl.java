package com.soft1851.smart.campus.service.Impl;

import com.soft1851.smart.campus.constant.ResponseResult;
import com.soft1851.smart.campus.constant.ResultCode;
import com.soft1851.smart.campus.model.dto.DynamicDto;
import com.soft1851.smart.campus.model.dto.DynamicFindDto;
import com.soft1851.smart.campus.model.dto.PageDto;
import com.soft1851.smart.campus.model.entity.Dynamic;
import com.soft1851.smart.campus.model.entity.UserAccount;
import com.soft1851.smart.campus.repository.DynamicRepository;
import com.soft1851.smart.campus.repository.UserAccountRepository;
import com.soft1851.smart.campus.service.DynamicService;
import com.soft1851.smart.campus.utils.SnowFlake;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author Yujie_Zhao
 * @ClassName DynamicServiceImpl
 * @Description 校友圈动态资讯
 * @Date 2020/6/12  15:28
 * @Version 1.0
 **/
@Service
public class DynamicServiceImpl implements DynamicService {

    @Resource
    private DynamicRepository dynamicRepository;
    @Resource
    private UserAccountRepository userAccountRepository;

    @Override
    public ResponseResult findAll(Boolean isDelete) {
        List<Dynamic> dynamicList = dynamicRepository.findAllByIsDeleted(false);
        return ResponseResult.success(dynamicList.size());
    }

    @Override
    public ResponseResult findDynamicInfo(Boolean isDelete) {
        List<Dynamic> dynamicList = dynamicRepository.findAllByIsDeleted(false);
        List<DynamicFindDto> dynamicFindDtos = new ArrayList<>();
        dynamicList.forEach(dynamic -> {
            UserAccount userAccount = userAccountRepository.findByPkUserAccountId(dynamic.getUserId());
            DynamicFindDto dynamicFindDto = DynamicFindDto.builder()
                    .pkDynamicId(dynamic.getPkDynamicId())
                    .comments(dynamic.getComments())
                    .content(dynamic.getContent())
                    .gmtCreate(dynamic.getGmtCreate())
                    .gmtModified(dynamic.getGmtModified())
                    .isDeleted(dynamic.getIsDeleted())
                    .thumbs(dynamic.getThumbs())
                    .type(dynamic.getType())
                    .userName(userAccount.getUserName())
                    .nickName(userAccount.getNickname())
                    .build();
            dynamicFindDtos.add(dynamicFindDto);
        });
        System.out.println(dynamicList.size());

        return ResponseResult.success(dynamicFindDtos);
    }

    /**
     * 添加动态资讯
     * @param dynamicDto
     * @return
     */
    @Override
    public ResponseResult insertDynamic(DynamicDto dynamicDto) {
        Dynamic dynamic = Dynamic.builder()
                .pkDynamicId(String.valueOf(new SnowFlake(1, 3).nextId()))
                .comments(0)
                .isDeleted(false)
                .title("")
                .thumbs(0)
                .type(dynamicDto.getType())
                .content(dynamicDto.getContent())
                .userId(dynamicDto.getUserId())
                .build();
        dynamicRepository.save(dynamic);
        return ResponseResult.success(dynamic);
    }

    /**
     * 批量查找动态资讯
     * @param pageDto
     * @return
     */
    @Override
    public ResponseResult findAllDynamic(PageDto pageDto) {
        Pageable pageable = PageRequest.of(
                pageDto.getCurrentPage(),
                pageDto.getPageSize(),
                Sort.Direction.DESC,
                "gmtCreate");
        Page<Dynamic> dynamicPage = dynamicRepository.findAllByIsDeleted(pageable);
        List<DynamicFindDto> dynamicFindDtos = new ArrayList<>();
        dynamicPage.forEach(dynamic -> {
            UserAccount userAccount = userAccountRepository.findByPkUserAccountId(dynamic.getUserId());
            DynamicFindDto dynamicFindDto = DynamicFindDto.builder()
                    .pkDynamicId(dynamic.getPkDynamicId())
                    .comments(dynamic.getComments())
                    .content(dynamic.getContent())
                    .gmtCreate(dynamic.getGmtCreate())
                    .gmtModified(dynamic.getGmtModified())
                    .isDeleted(dynamic.getIsDeleted())
                    .thumbs(dynamic.getThumbs())
                    .type(dynamic.getType())
                    .userName(userAccount.getUserName())
                    .nickName(userAccount.getNickname())
                    .build();
            dynamicFindDtos.add(dynamicFindDto);
        });
        return ResponseResult.success(dynamicFindDtos);
    }

    /**
     * 删除单个动态
     * @param id
     * @return
     */
    @Override
    public ResponseResult deleteDynamic(String id) {
        //根据id查询角色数据是否存在 ，若存在进行删除，不存则返回 数据有误
        Dynamic dynamic = dynamicRepository.findDynamicByPkDynamicIdAndIsDeleted(id,false);
        if (dynamic != null) {
            dynamic.setIsDeleted(true);
            dynamicRepository.saveAndFlush(dynamic);
            return ResponseResult.success("删除成功");
        } else {
            return ResponseResult.failure(ResultCode.RESULT_CODE_DATA_NONE);
        }
    }

    /**
     * 批量删除动态
     * @param ids
     * @return
     */
    @Override
    public ResponseResult deletedBatch(String ids) {
        //判断是否有数据
        if (ids.length() != 0) {
            //将接收到的ids字符串，使用逗号分割
            String[] idArr = ids.split(",");
            List<String> idsList = new ArrayList<String>();
            //遍历所有id存入到list
            Collections.addAll(idsList, idArr);
            int a = dynamicRepository.updateIsDelete(idsList);
            if(a>0){
                return ResponseResult.success("删除成功");
            }else {
                return ResponseResult.success(ResultCode.DATABASE_ERROR);
            }
        } else {
            return ResponseResult.failure(ResultCode.PARAM_IS_BLANK);
        }

    }

    /**
     * 修改动态信息
     * @param dynamic
     * @return
     */
    @Override
    public ResponseResult updateDynamic(Dynamic dynamic) {
        return null;
    }

}
