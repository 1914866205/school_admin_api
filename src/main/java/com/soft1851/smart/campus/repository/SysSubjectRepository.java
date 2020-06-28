package com.soft1851.smart.campus.repository;

import com.soft1851.smart.campus.model.entity.SysSubject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 * @author xunmi
 * @ClassName SysSubjectRepository
 * @Description 科目相关操作的类
 * @Date 2020/5/29
 * @Version 1.0
 **/
public interface SysSubjectRepository extends JpaRepository<SysSubject, Long> {

    /**
     * 通过科目 id 查找指定科目名
     *
     * @param id
     * @return
     */
    @Query(value = "SELECT name FROM sys_subject WHERE pk_subject_id = ?1", nativeQuery = true)
    String getSubjectName(Long id);

    /**
     * 通过科目 id 查找指定科目背景色
     *
     * @param id
     * @return
     */
    @Query(value = "SELECT background_color FROM sys_subject WHERE pk_subject_id = ?1", nativeQuery = true)
    String getSubjectBackgroundColor(Long id);


}
