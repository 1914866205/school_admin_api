package com.soft1851.smart.campus.repository;

import com.soft1851.smart.campus.model.entity.SysMenu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 * @Description TODO
 * @Author wf
 * @Date 2020/5/30
 * @Version 1.0
 */
public interface SysMenuRepository extends JpaRepository<SysMenu, Long> {

    @Query(value = "SELECT u FROM SysMenu u ")
    Object[] getAllMenus();
}
