package com.soft1851.smart.campus.service.Impl;

import com.soft1851.smart.campus.constant.ResponseResult;
import com.soft1851.smart.campus.constant.ResultCode;
import com.soft1851.smart.campus.exception.CustomException;
import com.soft1851.smart.campus.mapper.SysRoleMapper;
import com.soft1851.smart.campus.model.dto.PageDto;
import com.soft1851.smart.campus.model.dto.UpdateSysRoleDto;
import com.soft1851.smart.campus.model.entity.SysRole;
import com.soft1851.smart.campus.repository.SysRoleMenuRepository;
import com.soft1851.smart.campus.repository.SysRoleRepository;
import com.soft1851.smart.campus.service.SysRoleService;
import org.apache.commons.beanutils.ConvertUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.*;

/**
 * @author Tao
 * @version 1.0
 * @ClassName SysRoleServiceImpl
 * @Description TODO
 * @date 2020-05-31 14:58
 **/
@Service
public class SysRoleServiceImpl implements SysRoleService {

    @Resource
    private SysRoleRepository sysRoleRepository;

    @Resource
    private SysRoleMapper sysRoleMapper;

    @Resource
    private SysRoleMenuRepository sysRoleMenuRepository;

    /**
     * 分页查询所有
     *
     * @return
     */
    @Override
    public ResponseResult findAllSysRoleByPage() {
        /*Pageable pageable = PageRequest.of(
                pageDto.getCurrentPage(),
                pageDto.getPageSize(),
                Sort.Direction.ASC,
                "pkRoleId");
        Page<SysRole> sysRoles = sysRoleRepository.findAll(pageable);
        return ResponseResult.success(sysRoles.getContent());*/
        List<SysRole> sysRoles = sysRoleRepository.findAllRole();
        return ResponseResult.success(sysRoles);
    }

    /**
     * 删除单个角色信息
     *
     * @param id
     * @return
     */
    @Override
    public ResponseResult deletedSysRole(Long id) {
        //根据id查询角色数据是否存在 ，若存在进行删除，不存则返回 数据有误
        SysRole sysRole = sysRoleRepository.findByPkRoleId(id);
        if (sysRole != null) {
            //进行逻辑删除
            sysRoleRepository.setIsDeletedByPkRoleId(id);
            return ResponseResult.success();
        } else {
            return ResponseResult.failure(ResultCode.RESULT_CODE_DATA_NONE);
        }
    }

    /**
     * 批量删除数据
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
            //进行逻辑删除
            sysRoleRepository.deleteBatchByPkRoleId(idsList);
            return ResponseResult.success();
        } else {
            return ResponseResult.failure(ResultCode.PARAM_IS_BLANK);
        }
    }


    /**
     * 新增
     *
     * @param sysRole
     * @return
     */
    @Override
    public ResponseResult increaseSysRole(SysRole sysRole) {
        SysRole sysRole2 = sysRoleRepository.findTopByOrderBySortDesc();
        SysRole sysRole1 = SysRole.builder()
//                .pkRoleId()
                .roleName(sysRole.getRoleName())
                .roleDescription(sysRole.getRoleDescription())
                .isDeleted(false)
                .roleDecoration("测试")
                .gmtCreate(Timestamp.valueOf(LocalDateTime.now()))
                .gmtModified(Timestamp.valueOf(LocalDateTime.now()))
                .sort(sysRole2.getSort() + 1)
                .build();
        sysRoleRepository.save(sysRole1);
        return ResponseResult.success();
    }

    @Override
    public ResponseResult updateSysRoleSort(String sortList) {
        // 将前端传过来的sortId字符串转换为数组形式
        // 这个sortList的形式为[3,2,1,4] 含义：Id为3的元素 sortId为1，Id为2的元素 sortId为2
        String[] sortArray = null;
        sortArray = sortList.split(",");
        //判断前端传过来的sortId是否有相同的数据
        HashSet<String> sortHashSet = new HashSet<>();
        for (int j = 0; j < sortArray.length; j++) {
            sortHashSet.add(sortArray[j]);
        }
        //前端返回数据中无相同数据 为正确
        if (sortHashSet.size() == sortArray.length) {
            for (int i = 0; i < sortArray.length; i++) {
                sysRoleRepository.updateSysRole(Long.parseLong(sortArray[i]), (i + 1));
            }
            // 查询出所有角色数据
            List<SysRole> roles = sysRoleRepository.findAllRole();
            return ResponseResult.success(roles);
        } else {
            return ResponseResult.failure(ResultCode.DATA_IS_WRONG);
        }
    }

    @Override
    public ResponseResult getAllSysRole(PageDto pageDto) {
        Pageable pageable = PageRequest.of(
                pageDto.getCurrentPage(),
                pageDto.getPageSize(),
                Sort.Direction.ASC,
                "pk_role_id"
        );
        Page<SysRole> sysRolePage = sysRoleRepository.getAllSysRole(pageable);
        return ResponseResult.success(sysRolePage.getContent());
    }

    /**
     * 修改角色信息接口
     * @param updateSysRoleDto
     * @return
     */
    @Override
    public ResponseResult updateSysRole(UpdateSysRoleDto updateSysRoleDto) {
        sysRoleRepository.updateSysRole(updateSysRoleDto);
        return ResponseResult.success();
    }

    @Override
    public ResponseResult findAllSysRole() {
        List<Map> mapList = null;
        try {
            mapList =  sysRoleMapper.getAllSysRole();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ResponseResult.success(mapList);
    }

    @Override
    public int deleteRoleMenu(String ids, long roleId) {
        List<Long> idList = new ArrayList<>();
        ids = ids.substring(1, ids.length() - 1);
        String[] strings = ids.split(",");
        for (String id : strings) {
            long roleMenuId = sysRoleMenuRepository.getRoleMenuId(roleId, Long.parseLong(id));
            idList.add(roleMenuId);
        }
        int n = sysRoleMenuRepository.deleteBatchByPkRoleId(idList);
        if (n > 0) {
            return n;
        }
        throw new CustomException("删除角色权限", ResultCode.DATA_UPDATE_ERROR);
    }
}
