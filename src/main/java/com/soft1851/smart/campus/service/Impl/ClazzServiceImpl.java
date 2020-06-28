package com.soft1851.smart.campus.service.Impl;

import com.soft1851.smart.campus.constant.ResponseResult;
import com.soft1851.smart.campus.constant.ResultCode;
import com.soft1851.smart.campus.mapper.ClazzMapper;
import com.soft1851.smart.campus.mapper.UserAccountMapper;
import com.soft1851.smart.campus.model.dto.PageDto;
import com.soft1851.smart.campus.model.dto.UpdateClazzDto;
import com.soft1851.smart.campus.model.entity.Clazz;
import com.soft1851.smart.campus.model.vo.ClazzVo;
import com.soft1851.smart.campus.repository.ClazzRepository;
import com.soft1851.smart.campus.service.ClazzService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * @author xunmi
 * @ClassName ClazzServiceImpl
 * @Description TODO
 * @Date 2020/6/1
 * @Version 1.0
 **/
@Service
public class ClazzServiceImpl implements ClazzService {

    @Resource
    private ClazzRepository clazzRepository;
    @Resource
    private UserAccountMapper userAccountMapper;
    @Resource
    private ClazzMapper clazzMapper;


    /**
     * 分页查询所有未被逻辑删除的班级数据
     *
     * @param pageDto
     * @return
     */
    @Override
    public ResponseResult findAllClazz(PageDto pageDto) {
        Pageable pageable = PageRequest.of(
                pageDto.getCurrentPage(),
                pageDto.getPageSize(),
                Sort.Direction.DESC,
                "gmt_create");
        Page<Clazz> clazzes = clazzRepository.getAllClazz(pageable);
        return ResponseResult.success(clazzes.getContent());
    }

    /**
     * 新增班级
     *
     * @param clazz
     * @return
     */
    @Override
    public ResponseResult increaseClazz(Clazz clazz) {
        //首先查一下数据库是否存在此班级
        Clazz clazz1 = clazzRepository.getClazz(clazz.getName());
        //若不存在此班级 那么进行添加班级
        if (clazz1 == null) {
            Clazz clazz2 = Clazz.builder()
                    .clazzHeadmaster(clazz.getClazzHeadmaster())
                    .collegeId(clazz.getCollegeId())
                    .gmtCreate(Timestamp.valueOf(LocalDateTime.now()))
                    .gmtModified(Timestamp.valueOf(LocalDateTime.now()))
                    .grade(clazz.getGrade())
                    .isDeleted(false)
                    .name(clazz.getName())
                    .build();
            clazzRepository.save(clazz2);
            return ResponseResult.success("新增班级成功");
        } else {
            return ResponseResult.failure(ResultCode.DATA_ALREADY_EXISTED);
        }
    }

    /**
     * 批量新增学生进班级
     *
     * @param pkClazzId
     * @param ids
     * @return
     */
    @Override
    public ResponseResult increaseStudentToClazz(Long pkClazzId, String ids) {
        //判断是否有数据
        if (ids.length() != 0) {
            //将接收到的ids字符串，使用逗号分割
            String[] idArr = ids.split(",");
            List<String> idsList = new ArrayList<String>();
            for (String id : idArr) {
                //遍历所有id存入到list
                idsList.add(id);
            }
            clazzRepository.increaseStudentToClazz(idsList, pkClazzId);
            return ResponseResult.success("新增学生成功");
        } else {
            return ResponseResult.failure(ResultCode.PARAM_IS_BLANK);
        }
    }

    /**
     * 删除班级
     *
     * @param clazzId
     * @return
     */
    @Override
    public ResponseResult deletedClazz(Long clazzId) {
        Clazz clazz = clazzRepository.findClazzByPkClazzId(clazzId);
        //首先查询班级是否存在
        if (clazz != null) {
            //逻辑删除班级数据
            clazzRepository.deleteClazz(clazzId);
            //将此班级的其他学生的数据的班级id设置为0 未分配班级状态
            List<String> ids = null;
            //将学生的id存入到ids中，进行批量修改班级的操作
            try {
                ids = userAccountMapper.findUserIdByClazzId(clazzId);
            } catch (SQLException e) {
                e.printStackTrace();
            }
            //进行批量学生数据班级置空
            clazzRepository.increaseStudentToClazz(ids, (long) 0);
            return ResponseResult.success("删除班级成功！");
        } else {
            return ResponseResult.failure(ResultCode.RESULT_CODE_DATA_NONE);
        }
    }

    /**
     * 修改班级数据
     *
     * @param updateClazzDto
     * @return
     */
    @Override
    public ResponseResult updateClazz(UpdateClazzDto updateClazzDto) {
        Clazz clazz = clazzRepository.findClazzByPkClazzId(updateClazzDto.getClazzId());
        if (clazz != null) {
            clazzRepository.updateClazz(updateClazzDto);
            return ResponseResult.success("修改班级数据成功！");
        } else {
            return ResponseResult.failure(ResultCode.RESULT_CODE_DATA_NONE);
        }

    }

    @Override
    public ResponseResult deleteBatchByClazzId(String ids) {
        ids = ids.substring(1, ids.length() - 1);
        // 判断是否有数据
        if (ids.length() != 0) {
            // 将接收到的ids字符串，使用逗号分割
            String[] idArr = ids.split(",");
            List<Long> idsList = new ArrayList<Long>();
            for (String id : idArr) {
                //遍历所有id存入到list
                idsList.add(Long.valueOf(id));

                // 1.删除归属班级的学生班级id为0   遍历查找出归属此班级的学生进行批量置空操作
                List<String> studentIds = null;
                try {
                    studentIds = userAccountMapper.findUserIdByClazzId(Long.valueOf(id));
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                //将该班所有学生和班主任班级id设置为0
                clazzRepository.increaseStudentToClazz(studentIds, (long) 0);
            }
            // 2.最后删除班级表
            clazzRepository.deleteBatchByPkClazzId(idsList);

            return ResponseResult.success("批量删除成功！");
        } else {
            return ResponseResult.failure(ResultCode.PARAM_IS_BLANK);
        }
    }

    @Override
    public ResponseResult getAllClazzs(String keywords) {
        List<ClazzVo> clazzVos = null;
        try {
            clazzVos = clazzMapper.findClazzLike(keywords);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ResponseResult.success(clazzVos);
    }

    @Override
    public ResponseResult getAllClazz() {
        List<ClazzVo> clazzVos = clazzMapper.getAllClazz();
        return ResponseResult.success(clazzVos);
    }
}
