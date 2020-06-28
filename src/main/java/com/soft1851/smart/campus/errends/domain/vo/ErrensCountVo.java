package com.soft1851.smart.campus.errends.domain.vo;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author wl
 * @ClassNameErrensCountVo
 * @Description 统计表
 * @Date 2020/6/19
 * @Version 1.0
 */

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ErrensCountVo {
    /**
     * id
     */
    private String id;
    /**
     * 名字
     */
    private String name;
    /**
     * 手机号
     */
    private String phoneNumber;
    /**
     * 身份证正面
     */
    private String idCard;
    /**
     * 订单完成量
     */
    private Integer countOrder;
    /**
     * 状态
     */
    private Integer status;

    //不通过原因
    private String remark;
    /**
     * 审核人名字
     */
    private String reviewerName;
    /**
     * 审核人工号
     */
    private String jobNumber;
}
