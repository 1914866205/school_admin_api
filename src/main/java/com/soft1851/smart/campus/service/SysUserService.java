package com.soft1851.smart.campus.service;

import com.soft1851.smart.campus.constant.ResponseResult;
import com.soft1851.smart.campus.model.dto.LoginDto;
import com.soft1851.smart.campus.utils.TreeNode;

import java.util.List;
import java.util.Map;

/**
 * @Description TODO
 * @Author wf
 * @Date 2020/5/30
 * @Version 1.0
 */
public interface SysUserService {

    /**
     * 登录
     * @param loginDto
     * @return
     */
    Map<String, Object> login(LoginDto loginDto);

    /**
     * 根据用户账号获取用户权限
     * @param phoneNumber
     * @return
     */
    List<TreeNode> getSysUserMenu(String phoneNumber);
    /**
     * 重置默认密码
     * @param pkUserId
     * @return
     */
    ResponseResult setPasswordPkUserId(String pkUserId);


    /**
     * 逻辑删除系统用户
     * @param phoneNumber
     * @return
     */
    int updateIsDeletedByPhoneNumber(String phoneNumber);

    /**
     * 修改用户的状态
     * @param userId
     * @return
     */
    int updateIsEnabledById(boolean isEnabled, String userId);
}
