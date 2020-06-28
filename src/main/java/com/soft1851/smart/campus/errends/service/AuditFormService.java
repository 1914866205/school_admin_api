package com.soft1851.smart.campus.errends.service;

import com.soft1851.smart.campus.constant.ResponseResult;
import com.soft1851.smart.campus.errends.domain.dto.AuditFormDto;
import com.soft1851.smart.campus.errends.domain.dto.FinshOrderDto;

/**
 * @author wl
 * @ClassNameAuditFormService
 * @Description TODO
 * @Date 2020/6/17
 * @Version 1.0
 */
public interface AuditFormService {
    /**
     * 审核跑腿
     * @param auditFormDto
     * @return
     */
    ResponseResult saveAuditForm(AuditFormDto auditFormDto);

    /**
     * 查询所有跑腿
     * @param finshOrderDto
     * @return
     */
    ResponseResult selectErrends(FinshOrderDto finshOrderDto);

    /**
     * 查询跑腿审核订单
     *
     * @param finshOrderDto
     * @return
     */

    ResponseResult selectReviewForm(FinshOrderDto finshOrderDto);

    /**
     * 删除跑腿人员
     *
     * @return
     */

    ResponseResult DeleteErrends(FinshOrderDto finshOrderDto);
}
