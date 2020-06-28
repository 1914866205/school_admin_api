package com.soft1851.smart.campus.errends.controller;

import com.soft1851.smart.campus.constant.ResponseResult;
import com.soft1851.smart.campus.errends.domain.dto.AuditFormDto;
import com.soft1851.smart.campus.errends.domain.dto.FinshOrderDto;
import com.soft1851.smart.campus.errends.service.AuditFormService;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author wl
 * @ClassNameAuditFormController
 * @Description TODO
 * @Date 2020/6/17
 * @Version 1.0
 */

@RestController
@RequestMapping(value = "errends")
@Api(value = "review", tags = "跑腿申请")
public class AuditFormController {
    @Resource
    private AuditFormService auditFormService;

    /**
     * 成为跑腿
     *
     * @param auditFormDto
     * @return
     */
    @PostMapping(value = "/Errends")
    public ResponseResult becomeErrends(@RequestBody AuditFormDto auditFormDto) {
        return auditFormService.saveAuditForm(auditFormDto);
    }

    /**
     * 查询所有跑腿
     *
     * @param finshOrderDto
     * @return
     */
    @PostMapping(value = "/all/errends")
    public ResponseResult getAllerrends(@RequestBody FinshOrderDto finshOrderDto) {
        return auditFormService.selectErrends(finshOrderDto);
    }

    /**
     * 查询所有跑腿申请
     */
    @PostMapping(value = "/nopass/errends")
    public ResponseResult getErredsFrom(@RequestBody FinshOrderDto finshOrderDto) {
        return auditFormService.selectReviewForm(finshOrderDto);
    }

    /**
     * 删除所有跑腿申请
     */
    @PostMapping(value = "/delete/errends")
    public ResponseResult deteleErrends(@RequestBody FinshOrderDto finshOrderDto) {
        return auditFormService.DeleteErrends(finshOrderDto);
    }
}
