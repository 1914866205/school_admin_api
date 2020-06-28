package com.soft1851.smart.campus.repository;

import com.soft1851.smart.campus.model.dto.UpdateClazzDto;
import com.soft1851.smart.campus.model.entity.Clazz;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author xunmi
 * @ClassName ClazzService
 * @Description 班级相关操作的类
 * @Date 2020/6/13
 * @Version 1.0
 **/
public interface ClazzRepository extends JpaRepository<Clazz, Long> {
    /**
     * 分页查询所有班级数据
     *
     * @param pageable
     * @return
     */
    @Query(value = "select * from first_smart_campus.clazz where is_deleted = false", nativeQuery = true)
    Page<Clazz> getAllClazz(Pageable pageable);

    /**
     * 查找未被逻辑删除班级数据
     *
     * @param name
     * @return
     */
    @Query(value = "select * from first_smart_campus.clazz where name=?1 and is_deleted=false", nativeQuery = true)
    Clazz getClazz(String name);

    /**
     * 修改用户表中班级id字段（向班级中添加学生）
     * (向班级中删除学生)
     *
     * @param ids
     * @param pkClazzId
     * @return
     */
    @Modifying
    @Transactional(rollbackFor = RuntimeException.class)
    @Query(value = "update first_smart_campus.user_account u set u.clazz_id=?2 where u.pk_user_account_id in ?1", nativeQuery = true)
    int increaseStudentToClazz(List<String> ids, Long pkClazzId);

    /**
     * 逻辑删除班级
     *
     * @param pkClazzId
     */
    @Modifying
    @Transactional(rollbackFor = RuntimeException.class)
    @Query(value = "UPDATE first_smart_campus.clazz SET is_deleted = true WHERE pk_clazz_id=?1", nativeQuery = true)
    void deleteClazz(Long pkClazzId);

    /**
     * 根据班级id查询班级数据
     *
     * @param pkClazzId
     * @return
     */
    Clazz findClazzByPkClazzId(Long pkClazzId);

    /**
     * 修改班级数据
     *
     * @param updateClazzDto
     */
    @Transactional(rollbackFor = RuntimeException.class)
    @Modifying
    @Query(value = "UPDATE first_smart_campus.clazz SET " +
            "clazz_headmaster=:#{#updateClazzDto.clazzHeadmaster},college_id=:#{#updateClazzDto.clazzHeadmaster}," +
            "college_id=:#{#updateClazzDto.collegeId},grade=:#{#updateClazzDto.grade}," +
            "name=:#{#updateClazzDto.name} WHERE pk_clazz_id=:#{#updateClazzDto.clazzId}", nativeQuery = true)
    void updateClazz(@Param("updateClazzDto") UpdateClazzDto updateClazzDto);



    /**
     * 批量逻辑删除
     * @param ids
     * @return
     */
    @Modifying
    @Transactional(rollbackFor = RuntimeException.class)
    @Query(value = "update first_smart_campus.clazz f set f.is_deleted = true where f.pk_clazz_id in ?1",nativeQuery = true)
    int deleteBatchByPkClazzId(List<Long> ids);
}
