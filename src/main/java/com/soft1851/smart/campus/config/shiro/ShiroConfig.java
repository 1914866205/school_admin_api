package com.soft1851.smart.campus.config.shiro;

import com.soft1851.smart.campus.filter.JWTFilter;
import org.apache.commons.collections.map.HashedMap;
import org.apache.shiro.mgt.DefaultSessionStorageEvaluator;
import org.apache.shiro.mgt.DefaultSubjectDAO;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.DependsOn;

import javax.servlet.Filter;
import java.util.Map;

/**
 * @Description TODO
 * @Author wf
 * @Date 2020/5/19
 * @Version 1.0
 */
//@Configuration
public class ShiroConfig {

    @Bean(name = "shiroFilter")
    public ShiroFilterFactoryBean shiroFilter(SecurityManager securityManager) {
        ShiroFilterFactoryBean factoryBean = new ShiroFilterFactoryBean();
        Map<String, Filter> filterMap = new HashedMap();
        filterMap.put("jwt", new JWTFilter());
        factoryBean.setFilters(filterMap);
        factoryBean.setSecurityManager(securityManager);
        Map<String, String> filterRuleMap = new HashedMap();
        factoryBean.setUnauthorizedUrl("/noAuthor");
        filterRuleMap.put("/**", "jwt");
        filterRuleMap.put("/noAuthor", "anon");
        factoryBean.setFilterChainDefinitionMap(filterRuleMap);
        return factoryBean;
    }

    @Bean
    public SecurityManager securityManager() {
        DefaultWebSecurityManager defaultSecurityManager = new DefaultWebSecurityManager();
        defaultSecurityManager.setRealm(customRealm());
        //关闭session
        DefaultSessionStorageEvaluator evaluator = new DefaultSessionStorageEvaluator();
        evaluator.setSessionStorageEnabled(false);
        DefaultSubjectDAO subjectDAO = new DefaultSubjectDAO();
        defaultSecurityManager.setSubjectDAO(subjectDAO);
        return defaultSecurityManager;
    }

    @Bean
    public CustomRealm customRealm() {
        CustomRealm customRealm = new CustomRealm();
        return customRealm;
    }

    @Bean
    public LifecycleBeanPostProcessor lifecycleBeanPostProcessor() {
        return new LifecycleBeanPostProcessor();
    }

    /**
     * *
     * 开启Shiro的注解(如@RequiresRoles,@RequiresPermissions),需借助SpringAOP扫描使用Shiro注解的类,并在必要时进行安全逻辑验证
     * *
     * 配置以下两个bean(DefaultAdvisorAutoProxyCreator(可选)和AuthorizationAttributeSourceAdvisor)即可实现此功能
     * * @return
     */
    @Bean
    @DependsOn({"lifecycleBeanPostProcessor"})
    public DefaultAdvisorAutoProxyCreator advisorAutoProxyCreator() {
        DefaultAdvisorAutoProxyCreator advisorAutoProxyCreator = new DefaultAdvisorAutoProxyCreator();
        advisorAutoProxyCreator.setProxyTargetClass(true);
        return advisorAutoProxyCreator;
    }

    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor() {
        AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor = new AuthorizationAttributeSourceAdvisor();
        authorizationAttributeSourceAdvisor.setSecurityManager(securityManager());
        return authorizationAttributeSourceAdvisor;
    }
}
