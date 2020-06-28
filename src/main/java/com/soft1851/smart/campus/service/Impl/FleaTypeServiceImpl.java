package com.soft1851.smart.campus.service.Impl;

import com.soft1851.smart.campus.constant.ResponseResult;
import com.soft1851.smart.campus.constant.ResultCode;
import com.soft1851.smart.campus.model.dto.FleaTypeIncreasedDto;
import com.soft1851.smart.campus.model.dto.TypeDto;
import com.soft1851.smart.campus.model.entity.FleaType;
import com.soft1851.smart.campus.repository.FleaTypeRepository;
import com.soft1851.smart.campus.service.FleaTypeService;
import com.soft1851.smart.campus.utils.FleaTreeBuilder;
import com.soft1851.smart.campus.utils.FleaTreeNode;
import com.soft1851.smart.campus.utils.TreeBuilder;
import com.soft1851.smart.campus.utils.TreeNode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;

/**
 * @Author yhChen
 * @Description
 * @Date 2020/6/12
 */
@Slf4j
@Service
public class FleaTypeServiceImpl implements FleaTypeService {
    @Resource
    private FleaTypeRepository fleaTypeRepository;

    @Override
    public Map<String, Object> findAllType() {
        Map<String, Object> map = new TreeMap<>();
        List<FleaTreeNode> list = new ArrayList<>();
        //查找所有的type
        List<FleaType> types = fleaTypeRepository.findAll();
        for (FleaType fleaType : types) {
            //如果没有父节点
            if (fleaType.getParentId() == 0) {
                FleaTreeNode treeNode = new FleaTreeNode(fleaType.getPkFleaTypeId(), 0L, fleaType.getTypeName(), fleaType.getTypeCoverUrl(), fleaType.getTypeUrl(), new ArrayList<>());
                list.add(treeNode);
            } else {
                FleaTreeNode treeNode = new FleaTreeNode(fleaType.getPkFleaTypeId(), fleaType.getParentId(), fleaType.getTypeName(), fleaType.getTypeCoverUrl(), fleaType.getTypeUrl(), new ArrayList<>());
                list.add(treeNode);
            }
        }
        List<FleaTreeNode> trees = FleaTreeBuilder.buildTreeByLoop(list);
        map.put("types", trees);
        return map;
    }

    @Override
    public ResponseResult getGoodsByType(TypeDto typeDto) {
        Pageable pageable = PageRequest.of(typeDto.getCurrentPage(), typeDto.getPageSize(), Sort.Direction.DESC, "goodsCreateTime");
        if (fleaTypeRepository.getGoodsByTypeId(pageable, typeDto).size() == 0) {
            return ResponseResult.failure(ResultCode.RESULT_CODE_DATA_NONE);
        }
        return ResponseResult.success(fleaTypeRepository.getGoodsByTypeId(pageable, typeDto));
    }

    @Override
    public ResponseResult typeDeletedById(Long pkId) {
        //先在数据库里找，如果没找到，直接返回
        if (!fleaTypeRepository.findById(pkId).isPresent()) {
            return ResponseResult.failure(ResultCode.RESULT_CODE_DATA_NONE);
        }
        fleaTypeRepository.deleteById(pkId);
        return ResponseResult.success();
    }

    @Override
    public ResponseResult typeModify(FleaType fleaType) {
        log.info("------------" + fleaType + "-------------");
        //先在数据库里查找该数据
        FleaType fleaTypeById = fleaTypeRepository.getOne(fleaType.getPkFleaTypeId());
        if (fleaTypeById == null) {
            log.error("需要修改的数据在数据库里不存在");
            return ResponseResult.failure(ResultCode.RESULT_CODE_DATA_NONE);
        } else {
            log.error("需要修改的数据在数据库里存在");
            log.error("开始判断是否重复");
            List<FleaType> fleaTypes = fleaTypeRepository.findFleaTypesByPkFleaTypeIdIsNotAndTypeNameEqualsOrTypeUrlEquals(fleaType.getPkFleaTypeId(), fleaType.getTypeName(), fleaType.getTypeCoverUrl());
            log.info(fleaTypes.toString());
            if (fleaTypes.size() > 0) {
                log.error("需要修改的数据在数据库里存在重复值");
                return ResponseResult.failure(ResultCode.DATA_IS_WRONG);
            } else {
                fleaTypeRepository.save(fleaType);
                return ResponseResult.success();
            }
        }
    }

    @Override
    public ResponseResult typeIncreased(FleaTypeIncreasedDto fleaTypeIncreasedDto) {
        FleaType fleaType = FleaType.builder()
                .parentId(fleaTypeIncreasedDto.getParentId())
                .typeCoverUrl(fleaTypeIncreasedDto.getTypeCoverUrl())
                .typeName(fleaTypeIncreasedDto.getTypeName())
                .typeUrl(fleaTypeIncreasedDto.getTypeUrl())
                .isDeleted(false)
                .build();

        log.error("需要添加的数据在数据库里是否存在");
        log.error("开始判断是否重复");
        List<FleaType> fleaTypes = fleaTypeRepository.findFleaTypesByTypeNameEqualsOrTypeUrlEquals(fleaType.getTypeName(), fleaType.getTypeUrl());
        log.info(fleaTypes.toString());
        if (fleaTypes.size() > 0) {
            log.error("需要添加的数据在数据库里存在重复值");
            return ResponseResult.success("数据已存在");
        } else {
            fleaTypeRepository.save(fleaType);
            return ResponseResult.success();
        }
    }
}
