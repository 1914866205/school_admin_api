package com.soft1851.smart.campus.service.Impl;

import com.soft1851.smart.campus.constant.ResponseResult;
import com.soft1851.smart.campus.constant.ResultCode;
import com.soft1851.smart.campus.exception.CustomException;
import com.soft1851.smart.campus.model.dto.LoginDto;
import com.soft1851.smart.campus.model.entity.SysUser;
import com.soft1851.smart.campus.repository.SysUserRepository;
import com.soft1851.smart.campus.repository.SysUserRoleRepository;
import com.soft1851.smart.campus.service.RedisService;
import com.soft1851.smart.campus.service.RoleService;
import com.soft1851.smart.campus.service.SysUserService;
import com.soft1851.smart.campus.utils.JWTUtil;
import com.soft1851.smart.campus.utils.TreeNode;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;

import javax.annotation.Resource;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * @Description TODO
 * @Author wf
 * @Date 2020/5/30
 * @Version 1.0
 */
@Service
public class SysUserServiceImpl implements SysUserService {
    @Resource
    private SysUserRepository sysUserRepository;
    @Resource
    private RedisService redisService;
    @Resource
    private SysUserRoleRepository sysUserRoleRepository;
    @Resource
    private RoleService roleService;

    @Override
    public Map<String, Object> login(LoginDto loginDto) {
        SysUser admin = sysUserRepository.getSysUserBySysUserPhoneNumber(loginDto.getAccount());
        System.out.println("用户的信息>>>>>>>>>>>>>>>" + admin.toString());
        SysUser user = new SysUser();
        user.setPkUserId(admin.getPkUserId());
        user.setSysUserAvatar(admin.getSysUserAvatar());
        user.setSysUserPhoneNumber(admin.getSysUserPhoneNumber());
        user.setSysUserName(admin.getSysUserName());
        String code =  redisService.getValue("code", String.class);
        System.out.println("验证码: " + code);
        System.out.println("*****************"+ loginDto);
        long roleId = sysUserRoleRepository.getRoleIdByPhoneNumber(String.valueOf(loginDto.getAccount()));
        System.out.println(">>>>>>>>>>>>角色id<<<<<<<<<<<<<<<<" + roleId);
        if(loginDto.getCode().equals(code)){
            if (admin != null) {
                System.out.println("用户密码>>>>>>>>>>>>" + admin.getSysPassword());
                System.out.println("登录密码>>>>>>>>>>>>>" + loginDto.getPassword());
                if (admin.getSysPassword().equals(loginDto.getPassword())) {
                    String token = JWTUtil.getToken(loginDto.getAccount(), loginDto.getPassword(), String.valueOf(roleId));
                    Map<String, Object> result = new LinkedHashMap<>();
                    result.put("user", user);
                    result.put("token", token);
                    return result;
                }
                throw new CustomException("密码有误", ResultCode.USER_PASSWORD_ERROR);
            }
            throw new CustomException("账号有误", ResultCode.USER_ACCOUNT_ERROR);
        }
        throw new CustomException("验证码错误", ResultCode.USER_VERIFY_CODE_ERROR);
    }

    @Override
    public List<TreeNode> getSysUserMenu(String phoneNumber) {
        long roleId = sysUserRoleRepository.getRoleIdByPhoneNumber(phoneNumber);
        List<TreeNode> menus = roleService.getRoleMenuByRoleId(roleId);
        return menus;
    }

    @Override
    public ResponseResult setPasswordPkUserId(String pkUserId) {
        SysUser sysUser = sysUserRepository.findSysUserByPkUserId(pkUserId);
        if (sysUser!=null){
            return ResponseResult.success(sysUserRepository.setPasswordByPkUserId(pkUserId));
        }else {
            return ResponseResult.failure(ResultCode.USER_NOT_FOUND);
        }
    }

    @Override
    public int updateIsDeletedByPhoneNumber(String phoneNumber) {
        int result = sysUserRepository.updateIsDeletedByPhoneNumber(true, phoneNumber);
        if(result > 0){
            return result;
        }
        throw new CustomException("逻辑删除异常", ResultCode.DATA_UPDATE_ERROR);
    }

    @Override
    public int updateIsEnabledById(boolean isEnabled, String userId) {
        int result = sysUserRepository.updateIsEnabledById(isEnabled, userId);
        if(result > 0){
            return result;
        }
        throw new CustomException("修改用户状态异常", ResultCode.DATA_UPDATE_ERROR);
    }

}
