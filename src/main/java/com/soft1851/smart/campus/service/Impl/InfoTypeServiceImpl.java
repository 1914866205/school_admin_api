package com.soft1851.smart.campus.service.Impl;

import com.soft1851.smart.campus.constant.ResponseResult;
import com.soft1851.smart.campus.constant.ResultCode;
import com.soft1851.smart.campus.model.dto.PageDto;
import com.soft1851.smart.campus.model.entity.InfoType;
import com.soft1851.smart.campus.repository.InfoTypeRepository;
import com.soft1851.smart.campus.service.InfoTypeService;
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
 * @author Yujie_Zhao
 * @ClassName InfoTypeServiceImpl
 * @Description TODO
 * @Date 2020/6/2  15:28
 * @Version 1.0
 **/
@Service
public class InfoTypeServiceImpl implements InfoTypeService {

    @Resource
    private InfoTypeRepository infoTypeRepository;


    /**
     * 查询所有资讯分类
     * @param pageDto
     * @return
     */
    @Override
    public ResponseResult getAllInfoType(PageDto pageDto) {
        Pageable pageable = PageRequest.of(
                pageDto.getCurrentPage(),
                pageDto.getPageSize(),
                Sort.Direction.DESC,
                "gmt_create");
        Page<InfoType> infoTypePage = infoTypeRepository.getAllInfoType(pageable);
        return ResponseResult.success(infoTypePage.getContent());
    }

    /**
     * 新增资讯分类
     * @param infoType
     * @return
     */
    @Override
    public ResponseResult insertInfoType(InfoType infoType) {
        InfoType infoType1 = infoTypeRepository.findTopByOrderBySortDesc();

        infoType.setSort(infoType1.getSort()+1);
        infoType.setIsDeleted(false);
        infoTypeRepository.save(infoType);
        if (infoType.getPkInfoTypeId()!=null){
            return ResponseResult.success("添加成功");
        }
        else {
            return ResponseResult.success(ResultCode.DATABASE_ERROR);
        }
    }

    /**
     * 分类删除
     * @param id
     * @return
     */
    @Override
    public ResponseResult deleteInfoType(Long id) {
        InfoType infoType = infoTypeRepository.findByPkInfoTypeId(id);
        if (infoType!=null){
            infoTypeRepository.deleteInfoType(id);
            return ResponseResult.success("删除成功");
        }
        return ResponseResult.failure(ResultCode.USER_NOT_FOUND);
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
            infoTypeRepository.deleteBatchInfoType(idsList);
            return ResponseResult.success("删除成功");
        } else {
            return ResponseResult.failure(ResultCode.PARAM_IS_BLANK);
        }
    }

    /**
     * 修改资讯分类
     * @param infoType
     * @return
     */
    @Override
    public ResponseResult updateInfoType(InfoType infoType) {
        InfoType infoType1 = infoTypeRepository.findByPkInfoTypeId(infoType.getPkInfoTypeId());
        if (infoType1!= null){
            infoType1.setName(infoType.getName());
            infoType1.setSort(infoType.getSort());
            infoType1.setGmtCreate(Timestamp.valueOf(LocalDateTime.now()));
            infoType1.setIsDeleted(false);
            infoTypeRepository.saveAndFlush(infoType1);
            return ResponseResult.success("修改成功"+infoType1);
        }
        return ResponseResult.success(ResultCode.DATABASE_ERROR);
    }
}
