package com.soft1851.smart.campus.service.Impl;

import com.soft1851.smart.campus.constant.ResponseResult;
import com.soft1851.smart.campus.constant.ResultCode;
import com.soft1851.smart.campus.mapper.AppVersionMapper;
import com.soft1851.smart.campus.model.dto.PageDto;
import com.soft1851.smart.campus.model.dto.TimeBorrowPageDto;
import com.soft1851.smart.campus.model.dto.UpdateAppVersionDto;
import com.soft1851.smart.campus.model.entity.AppVersion;
import com.soft1851.smart.campus.repository.AppVersionRepository;
import com.soft1851.smart.campus.service.AppVersionService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.transaction.Transactional;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Tao
 * @version 1.0
 * @ClassName AppVersionServiceImpl
 * @Description TODO
 * @date 2020-06-03 8:15
 **/
@Service
@Transactional(rollbackOn =Exception.class )
public class AppVersionServiceImpl implements AppVersionService {
    @Resource
    private AppVersionRepository appVersionRepository;
    @Resource
    private AppVersionMapper appVersionMapper;

    /**
     * 分页查询所有版本号
     *
     * @param pageDto
     * @return
     */
    @Override
    public ResponseResult findAllAppVersion(PageDto pageDto) {
        Pageable pageable = PageRequest.of(
                pageDto.getCurrentPage(),
                pageDto.getPageSize(),
                Sort.Direction.DESC,
                "gmt_create");
        Page<AppVersion> appVersions = appVersionRepository.getAllAppVersion(pageable);
        return ResponseResult.success(appVersions.getContent());
    }

    /**
     * 新增版本
     *
     * @param appVersion
     * @return
     */
    @Override
    public ResponseResult increaseAppVersion(AppVersion appVersion) {
        //查找最低版本号
        AppVersion minAppVersion = appVersionRepository.findMinVersionMessage();
        //查询所有版本id 进行批量修改的操作
        List<Long> ids = appVersionRepository.selectAllPkAppVersionId();
        appVersionRepository.updateMaxVersion(appVersion.getCurrentVersion(), ids);
        //判断数据是否完整
        boolean isAppType = appVersion.getAppType() != null;
        boolean isCurrent = appVersion.getCurrentVersion() != null;
        boolean isDescription = appVersion.getVersionDescription() != null;
        boolean isDownloadLink = appVersion.getDownloadLink() != null;
        //数据信息完整进行新增版本信息
        if (isAppType && isCurrent && isDescription && isDownloadLink) {
            AppVersion appVersion1 = AppVersion.builder()
                    .appType(appVersion.getAppType())
                    .currentVersion(appVersion.getCurrentVersion())
                    .maxVersion(appVersion.getCurrentVersion())
                    .minVersion(minAppVersion.getMinVersion())
                    .versionDescription(appVersion.getVersionDescription())
                    .downloadLink(appVersion.getDownloadLink())
                    .gmtCreate(Timestamp.valueOf(LocalDateTime.now()))
                    .gmtModified(Timestamp.valueOf(LocalDateTime.now()))
                    .isDeleted(false)
                    .build();
            appVersionRepository.save(appVersion1);
            return ResponseResult.success();
        } else {
            return ResponseResult.failure(ResultCode.PARAM_NOT_COMPLETE);
        }
    }

    /**
     * 修改版本数据
     * @param updateAppVersionDto
     * @return
     */
    @Override
    public ResponseResult modificationAppVersion(UpdateAppVersionDto updateAppVersionDto) {
        appVersionRepository.updateAppVersion(updateAppVersionDto);
        return ResponseResult.success();
    }


    /**
     * 逻辑删除
     *
     * @param id
     * @return
     */
    @Override
    public ResponseResult deletionAppVersion(Long id) {
        //需要删除的APP版本号
        AppVersion appVersion = appVersionRepository.findAppVersionByPkAppVersionId(id);
        //查询最高版本的数据
        AppVersion maxAppVersion = appVersionRepository.findMaxVersionMessage();
        //查询最低版本的数据
        AppVersion minAppVersion = appVersionRepository.findMinVersionMessage();
        //判断数据存在
        if (appVersion != null) {
            //判断即将被删除的APP版本的数据是否为最高版本的数据
            if ((appVersion.getCurrentVersion()).equals(maxAppVersion.getCurrentVersion())) {
                //当前删除的为最高版本数据
                appVersionRepository.setIsDeletedByPkAppVersionId(id);
                //修改其他所有版本数据的最高版本号
                AppVersion updateMaxAppVersion = appVersionRepository.findMaxVersionMessage();
                List<Long> ids = appVersionRepository.selectAllPkAppVersionId();
                appVersionRepository.updateMaxVersion(updateMaxAppVersion.getCurrentVersion(), ids);
                return ResponseResult.success();
                //判断即将被删除的APP版本号是否为最低版本号
            } else if ((appVersion.getCurrentVersion()).equals(minAppVersion.getCurrentVersion())) {
                //当前删除的为最高版本数据
                appVersionRepository.setIsDeletedByPkAppVersionId(id);
                //修改其他所有版本数据的最低版本号
                AppVersion updateMinAppVersion = appVersionRepository.findMinVersionMessage();
                List<Long> ids = appVersionRepository.selectAllPkAppVersionId();
                appVersionRepository.updateMinVersion(updateMinAppVersion.getCurrentVersion(), ids);
                return ResponseResult.success();
            } else {
                //即将删除的数据既不是最高版本号也不是最低版本号
                appVersionRepository.deleteByPkAppVersionId(id);
                return ResponseResult.success();
            }
        } else {
            return ResponseResult.failure(ResultCode.RESULT_CODE_DATA_NONE);
        }
    }

    /**
     * 批量删除
     *
     * @param ids
     * @return
     */
    @Override
    public ResponseResult deletedBatch(String ids) {
        ids = ids.substring(1, ids.length() - 1);
        //判断是否有数据
        if (ids.length() != 0) {
            //将接收到的ids字符串，使用逗号分割
            String[] idArr = ids.split(",");
            List<Long> idsList = new ArrayList<Long>();
            for (String id : idArr) {
                //遍历所有id存入到list
                idsList.add(Long.valueOf(id));
            }
            appVersionRepository.deleteBatchAppVersion(idsList);
            //批量删除完成后 查询目前的最高版本号和最低版本号
            AppVersion maxAppVersion = appVersionRepository.findMaxVersionMessage();
            AppVersion minAppVersion = appVersionRepository.findMinVersionMessage();
            List<Long> versionIds = appVersionRepository.selectAllPkAppVersionId();
            //修改最高最低的版本所有数据
            appVersionRepository.updateMinVersion(minAppVersion.getCurrentVersion(), versionIds);
            appVersionRepository.updateMaxVersion(maxAppVersion.getCurrentVersion(), versionIds);
            return ResponseResult.success();
        } else {
            return ResponseResult.failure(ResultCode.PARAM_IS_BLANK);
        }
    }

    @Override
    public ResponseResult getAppVersionsByTime(TimeBorrowPageDto timeBorrowPageDto) {
        String[] times = timeBorrowPageDto.getTime().split(",");
        String startTime = times[0];
        String endTime = times[1];
        Timestamp timestamp = Timestamp.valueOf(startTime);
        Timestamp timestamp1 = Timestamp.valueOf(endTime);
        List<AppVersion> appVersionList = null;
        try {
            appVersionList = appVersionMapper.getAppVersionByTime(timestamp,timestamp1,timeBorrowPageDto.getCurrentPage(),timeBorrowPageDto.getPageSize());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ResponseResult.success(appVersionList);
    }
}
