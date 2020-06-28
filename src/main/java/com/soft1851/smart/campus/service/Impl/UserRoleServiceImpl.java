package com.soft1851.smart.campus.service.Impl;

import com.alibaba.fastjson.JSON;
import com.soft1851.smart.campus.constant.ResponseResult;
import com.soft1851.smart.campus.constant.ResultCode;
import com.soft1851.smart.campus.model.dto.AdminDto;
import com.soft1851.smart.campus.model.dto.AdminUpdateDto;
import com.soft1851.smart.campus.model.dto.DoubleFieldDto;
import com.soft1851.smart.campus.model.entity.SysRoleMenu;
import com.soft1851.smart.campus.model.entity.SysUser;
import com.soft1851.smart.campus.model.entity.UserRole;
import com.soft1851.smart.campus.repository.SysRoleMenuRepository;
import com.soft1851.smart.campus.repository.SysUserRepository;
import com.soft1851.smart.campus.repository.UserRoleRepository;
import com.soft1851.smart.campus.service.UserRoleService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.*;

/**
 * @author Tao
 * @version 1.0
 * @ClassName UserRoleServiceImpl
 * @Description TODO
 * @date 2020-06-01 14:59
 **/
@Service
public class UserRoleServiceImpl implements UserRoleService {
    @Resource
    private SysUserRepository sysUserRepository;
    @Resource
    private UserRoleRepository userRoleRepository;
    @Resource
    private SysRoleMenuRepository sysRoleMenuRepository;

    //查询所有管理员数据信息
    @Override
    public ResponseResult selectAllAdmin() {
        List<Map> mapList = new ArrayList<Map>();
        List<SysUser> sysUsers = sysUserRepository.getSysUserByIsDeleted(false);
        sysUsers.forEach(sysUser -> {
            Map admin = sysUserRepository.selectAdminById(sysUser.getPkUserId());
            mapList.add(admin);
        });
        return ResponseResult.success(JSON.toJSON(mapList));
    }

    @Override
    public ResponseResult increaseAdmin(AdminDto adminDto) {
        //只需要密码、用户名、手机号,激活状态
        String userId = UUID.randomUUID().toString();
        System.out.println("***用户表***" + userId);
        SysUser sysUser1 = SysUser.builder()
                .pkUserId(userId)
                .gmtCreate(Timestamp.valueOf(LocalDateTime.now()))
                .gmtModified(Timestamp.valueOf(LocalDateTime.now()))
                .isDeleted(false)
                .isEnabled(adminDto.getIsEnabled())
                .salt("xyz")
                .sysPassword("123456")
                .sysUserAvatar("https://niit-student.oss-cn-beijing.aliyuncs.com/markdown/20200601182918.png")
                .sysUserName(adminDto.getName())
                .sysUserPhoneNumber(adminDto.getPhoneNumber())
                .build();
        sysUserRepository.save(sysUser1);
        System.out.println("***用户角色关联表***" + userId);
        UserRole userRole = UserRole.builder()
                .gmtCreate(Timestamp.valueOf(LocalDateTime.now()))
                .gmtModified(Timestamp.valueOf(LocalDateTime.now()))
                .isDeleted(false)
                .roleId(Long.parseLong(adminDto.getRole()))
                .sysUserId(userId)
                .build();
        userRoleRepository.save(userRole);
        System.out.println("***插入结束***" + userId);
        return ResponseResult.success();
    }

    @Override
    public ResponseResult deletedAdmin(String sysUserId) {
        UserRole sysUser = userRoleRepository.findUserRoleBySysUserId(sysUserId);
        //首先查询用户是否存在 若存在进行单个删除
        if (sysUser != null) {
            userRoleRepository.deleteBySysUserId(sysUserId);
            sysUserRepository.deleteByPkUserId(sysUserId);
            return ResponseResult.success();
        } else {
            return ResponseResult.failure(ResultCode.USER_NOT_FOUND);
        }
    }

    @Override
    public ResponseResult modificationAdmin(AdminDto adminDto) {
        SysUser user = SysUser.builder()
                .sysUserName(adminDto.getName())
                .sysUserPhoneNumber(adminDto.getPhoneNumber())
                .isEnabled(adminDto.getIsEnabled())
                .pkUserId(adminDto.getUserId())
                .build();
        int result = sysUserRepository.updateSysUserInfo(user);
        int result1 =userRoleRepository.updateBySysUserId(Long.parseLong(adminDto.getRole().toString()), adminDto.getUserId());
        return ResponseResult.success();
    }

    @Override
    public ResponseResult insertUserRole(DoubleFieldDto doubleFieldDto) {
        String menuId = doubleFieldDto.getFirstField();
        String[] menuIds = menuId.substring(1, menuId.length()-1).split(",");
        for (int i = 0, len = menuIds.length; i < len; i++) {
            SysRoleMenu roleMenu = SysRoleMenu.builder()
                    .pkRoleId(Long.parseLong(doubleFieldDto.getSecondField()))
                    .menuId(Long.parseLong(menuIds[i]))
                    .gmtCreate(Timestamp.valueOf(LocalDateTime.now()))
                    .gmtModified(Timestamp.valueOf(LocalDateTime.now()))
                    .isDeleted(false)
                    .build();
           sysRoleMenuRepository.save(roleMenu);
        }
        return ResponseResult.success();
    }
}
