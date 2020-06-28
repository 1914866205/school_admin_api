package com.soft1851.smart.campus.service.Impl;

import com.soft1851.smart.campus.constant.ResponseResult;
import com.soft1851.smart.campus.constant.ResultCode;
import com.soft1851.smart.campus.model.dto.PageDto;
import com.soft1851.smart.campus.model.dto.QueryDto;
import com.soft1851.smart.campus.model.entity.InfoManage;
import com.soft1851.smart.campus.model.entity.InfoMangeType;
import com.soft1851.smart.campus.model.vo.InfoManageTypeIdVo;
import com.soft1851.smart.campus.repository.InfoManageRepository;
import com.soft1851.smart.campus.repository.InfoManageTypeRepository;
import com.soft1851.smart.campus.service.InfoManageService;
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
 * @ClassName InfoManageServiceImpl
 * @Description 咨询service实现
 * @Date 2020/6/1  10:22
 * @Version 1.0
 **/
@Service
public class InfoManageServiceImpl implements InfoManageService {

    @Resource
    private InfoManageRepository infoManageRepository;

    @Resource
    private InfoManageTypeRepository infoManageTypeRepository;


    /***
     * 添加咨询
     * @param infoManageTypeIdVo
     * @return
     */
    @Override
    public ResponseResult insertInfoManage(InfoManageTypeIdVo infoManageTypeIdVo) {
        System.out.println(infoManageTypeIdVo);
        InfoManage infoManage = new InfoManage();
        infoManage.setTitle(infoManageTypeIdVo.getTitle());
        infoManage.setCover(infoManageTypeIdVo.getCover());
        infoManage.setText(infoManageTypeIdVo.getText());
        infoManage.setIsTop(false);
        infoManage.setIsDeleted(false);
        infoManageRepository.save(infoManage);
        //获取其返回的自增主键
        Long id = infoManage.getPkInfoManageId();
        System.out.println("返回的id：" + id);
        //添加成功之后再存入关系表
        if (id != null) {
            InfoMangeType infoMangeType = new InfoMangeType();
            infoMangeType.setTypeId(infoManageTypeIdVo.getTypeId());
            infoMangeType.setInfoId(id);
            infoMangeType.setIsDeleted(false);
            infoManageTypeRepository.save(infoMangeType);
            return ResponseResult.success("添加成功");
        } else {
            return ResponseResult.success(ResultCode.DATABASE_ERROR);
        }
    }


    /**
     * 分页查询咨询
     *
     * @param pageDto
     * @return
     */
    @Override
    public ResponseResult findAllInfo(PageDto pageDto) {
        Pageable pageable = PageRequest.of(
                pageDto.getCurrentPage(),
                pageDto.getPageSize(),
                Sort.Direction.DESC,
                "gmt_create");
        Page<InfoManage> infoManagePage = infoManageRepository.getAllInfoManage(pageable);
        return ResponseResult.success(infoManagePage.getContent());
    }

    /**
     * 单个删除咨询
     *
     * @param id
     * @return
     */
    @Override
    public ResponseResult deleteInfoManage(Long id) {
        //根据id查询角色数据是否存在 ，若存在进行删除，不存则返回 数据有误
        InfoManage infoManage = infoManageRepository.findByPkInfoManageId(id);
        if (infoManage != null) {
            infoManageRepository.deleteById(id);
            return ResponseResult.success("删除成功");
        } else {
            return ResponseResult.failure(ResultCode.RESULT_CODE_DATA_NONE);
        }
    }

    /**
     * 批量删除咨询
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
            infoManageRepository.deleteBatch(idsList);
            return ResponseResult.success("删除成功");
        } else {
            return ResponseResult.failure(ResultCode.PARAM_IS_BLANK);
        }
    }

    /**
     * 修改咨询
     *
     * @param infoManage
     * @return
     */
    @Override
    public ResponseResult updateInfoManage(InfoManage infoManage) {
        //判断时候为空
        InfoManage infoManage1 = infoManageRepository.findByPkInfoManageId(infoManage.getPkInfoManageId());
        if (infoManage1 != null) {
            infoManage1.setText(infoManage.getText());
            infoManage1.setGmtCreate(Timestamp.valueOf(LocalDateTime.now()));
            infoManage1.setIsDeleted(false);
            infoManageRepository.saveAndFlush(infoManage1);
            return ResponseResult.success("修改成功" + infoManage1);
        } else {
            return ResponseResult.failure(ResultCode.RESULT_CODE_DATA_NONE);
        }
    }

    /**
     * 修改置顶状态
     * @param queryDto
     * @return
     */
    @Override
    public ResponseResult changeInfoMange(QueryDto queryDto) {
        InfoManage infoManage = infoManageRepository.findByPkInfoManageId(Long.valueOf(queryDto.getFiled1()));
        if (infoManage != null) {
            infoManageRepository.changeIsTop(infoManage.getPkInfoManageId(), queryDto.getStatus());
            return ResponseResult.success();
        } else {
            return ResponseResult.failure(ResultCode.RESULT_CODE_DATA_NONE);
        }
    }
}
