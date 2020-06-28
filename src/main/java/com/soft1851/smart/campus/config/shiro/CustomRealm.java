package com.soft1851.smart.campus.config.shiro;


import com.soft1851.smart.campus.model.entity.SysUser;
import com.soft1851.smart.campus.repository.SysUserRepository;
import com.soft1851.smart.campus.repository.SysUserRoleRepository;
import com.soft1851.smart.campus.service.RoleService;
import com.soft1851.smart.campus.utils.JWTUtil;
import com.soft1851.smart.campus.utils.TreeNode;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

import javax.annotation.Resource;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @Description TODO
 * @Author wf
 * @Date 2020/5/19
 * @Version 1.0
 */
/**
 * @Description TODO
 * @Author wf
 * @Date 2020/5/19
 * @Version 1.0
 */
@Slf4j
public class CustomRealm extends AuthorizingRealm {
    @Resource
    private SysUserRepository sysUserRepository;
    @Resource
    private RoleService roleService;
    @Resource
    private SysUserRoleRepository sysUserRoleRepository;

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        String phoneNumber = JWTUtil.getPhoneNumber(principalCollection.toString());
        //获取用户信息
        SysUser user = sysUserRepository.getSysUserBySysUserPhoneNumber(phoneNumber);
        String roleId = JWTUtil.getRoleId(principalCollection.toString());
        List<TreeNode> menus = roleService.getRoleMenuByRoleId(Long.parseLong(roleId));
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        //获取用户权限
        Set<String> userPermission = new HashSet<>();
        menus.forEach(menu -> {
            userPermission.add(menu.getName());
        });
        System.out.println(userPermission.toString());
        assert false;
        log.info(userPermission.toString());
        info.setStringPermissions(userPermission);
        return info;
    }

    /**
     * 使用JWT替代原生Token
     *
     * @param token
     * @return
     */
    @Override
    public boolean supports(AuthenticationToken token) {
        return token instanceof JWTToken;
    }

    /**
     * 这里可以注入userService,为了方便演示，我就写死了帐号了密码
     * private UserService userService;
     * <p>
     * 获取即将需要认证的信息
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authcToken) throws AuthenticationException {
        String token = (String) authcToken.getPrincipal();
        String phoneNumber = JWTUtil.getPhoneNumber(token);
        if (phoneNumber == null) {
            throw new AuthenticationException("token invalid");
        }
        //根据用户名从数据库获取密码
        SysUser user = sysUserRepository.getSysUserBySysUserPhoneNumber(phoneNumber);
        if (user == null) {
            throw new AccountException("用户名不正确");
        }
        String password = user.getSysPassword();
        System.out.println(password);
        String roleId = JWTUtil.getRoleId(token);
        log.info("角色id:" + roleId);
        log.info("账号密码：" + password);
        log.info("账号：" + phoneNumber);
        log.info("token的值: " + token);
        if (!JWTUtil.deToken(token, phoneNumber, password, roleId)) {
            throw new AuthenticationException("用户名或密码不正确");
        }
        log.info(">>>>>>>>>>>>>>>>>>>登录认证>>>>>>>>>>>>>>");
        return new SimpleAuthenticationInfo(token, token, getName());
    }
}
