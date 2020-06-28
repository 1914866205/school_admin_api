package com.soft1851.smart.campus.repository;

import com.soft1851.smart.campus.model.dto.UpdateAppVersionDto;
import com.soft1851.smart.campus.model.entity.AppVersion;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author Tao
 */
public interface AppVersionRepository extends JpaRepository<AppVersion, Long> {
    /**
     * 根据id查询APP版本数据
     *
     * @param id
     * @return
     */
    AppVersion findAppVersionByPkAppVersionId(Long id);

    /**
     * 根据主键id删除APP版本数据
     *
     * @param pkAppVersionId
     */
    @Transactional(rollbackFor = RuntimeException.class)
    @Modifying
    @Query("delete from AppVersion where pkAppVersionId = ?1")
    void deleteByPkAppVersionId(Long pkAppVersionId);

    /**
     * 批量删除
     *
     * @param ids
     */
    @Modifying
    @Transactional(timeout = 10, rollbackFor = RuntimeException.class)
    @Query("delete from AppVersion f where f.pkAppVersionId in (?1)")
    void deleteBatch(List<Long> ids);

    /**
     * 批量逻辑删除
     *
     * @param ids
     * @return
     */
    @Modifying
    @Transactional(rollbackFor = RuntimeException.class)
    @Query(value = "update first_smart_campus.app_version v set v.is_deleted = true where v.pk_app_version_id in ?1", nativeQuery = true)
    int deleteBatchAppVersion(List<Long> ids);

    /**
     * 修改版本数据信息
     *
     * @param updateAppVersionDto
     */
    @Modifying
    @Transactional(rollbackFor = RuntimeException.class)
    @Query(value = "UPDATE first_smart_campus.app_version SET app_type=:#{#updateAppVersionDto.appType}," +
            "download_link=:#{#updateAppVersionDto.downloadLink}," +
            "version_description=:#{#updateAppVersionDto.versionDescription}, " +
            "max_version=:#{#updateAppVersionDto.maxVersion} " +
            "WHERE pk_app_version_id=:#{#updateAppVersionDto.pkAppVersionId}", nativeQuery = true)
    void updateAppVersion(@Param("updateAppVersionDto") UpdateAppVersionDto updateAppVersionDto);

//    /**
//     * 查找出最高版本号
//     *
//     * @return
//     */
//    AppVersion findTopByOrderByPkAppVersionIdDesc();
//
//    /***
//     * 查找出最低版本号的数据
//     * @return
//     */
//    AppVersion findTopByOrderByPkAppVersionIdAsc();


    /**
     * 查找出最高版本号
     *
     * @return
     */
    @Query(value = "select * from first_smart_campus.app_version" +
            " where pk_app_version_id=" +
            "(select Max(pk_app_version_id) from first_smart_campus.app_version where is_deleted = false)"
            , nativeQuery = true)
    AppVersion findMaxVersionMessage();

    /**
     * 查找出最高版本号
     *
     * @return
     */
    @Query(value = "select * from first_smart_campus.app_version" +
            " where pk_app_version_id=" +
            "(select MIN(pk_app_version_id) from first_smart_campus.app_version where is_deleted = false)"
            , nativeQuery = true)
    AppVersion findMinVersionMessage();


    /**
     * 查询所有主键id
     *
     * @return
     */
    @Query(value = "select v.pk_app_version_id from first_smart_campus.app_version as v ", nativeQuery = true)
    List<Long> selectAllPkAppVersionId();

    /**
     * 批量修改最高版本号
     *
     * @param maxVersion
     * @param ids
     * @return
     */
    @Modifying
    @Transactional(rollbackFor = RuntimeException.class)
    @Query("update AppVersion v set v.maxVersion = ?1 where v.pkAppVersionId in ?2")
    int updateMaxVersion(String maxVersion, List<Long> ids);

    /**
     * 批量修改最低版本号
     *
     * @param minVersion
     * @param ids
     * @return
     */
    @Modifying
    @Transactional(rollbackFor = RuntimeException.class)
    @Query("update AppVersion v set v.minVersion = ?1 where v.pkAppVersionId in ?2")
    int updateMinVersion(String minVersion, List<Long> ids);

    /**
     * 逻辑删除
     *
     * @param pkAppVersionId
     * @return
     */
    @Modifying
    @Transactional(rollbackFor = RuntimeException.class)
    @Query(value = "update first_smart_campus.app_version s set s.is_deleted = true where s.pk_app_version_id=?1", nativeQuery = true)
    int setIsDeletedByPkAppVersionId(Long pkAppVersionId);

    /**
     * 分页查询所有APP版本数据（去除逻辑删除）
     * @param pageable
     * @return
     */
    @Query(value = "select * from first_smart_campus.app_version where is_deleted = false",nativeQuery = true)
    Page<AppVersion> getAllAppVersion(Pageable pageable);


   }
