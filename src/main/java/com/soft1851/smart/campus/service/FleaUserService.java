package com.soft1851.smart.campus.service;

import com.soft1851.smart.campus.constant.ResponseResult;
import com.soft1851.smart.campus.model.dto.*;

/**
 * @author 倪涛涛
 * @version 1.0.0
 * @ClassName FleaGoodsService.java
 * @Description TODO
 * @createTime 2020年06月09日 13:58:00
 */
public interface FleaUserService {
    /**
     * 查询所有用户
     *
     * @param pageDto PageDto
     * @return ResponseResult
     */
    ResponseResult findAllUser(PageDto pageDto);

    //模糊搜索
    ResponseResult findUserByContent(FleaSearchDto fleaSearchDto);
}
