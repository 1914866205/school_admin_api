package com.soft1851.smart.campus.errends.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author wl
 * @ClassNameFinshOrderDto
 * @Description TODO
 * @Date 2020/6/9
 * @Version 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FinshOrderDto{
    /**
     * 订单发起人
     */
    private String founderId;
    /**
     * 订单状态 3 是完成 1是取消 2是进行中 0是正常
     */
    private Integer status;
    /**
     * 第几页
     */
    private Integer num;
    /**
     * 分页数
     */
    private Integer size;
    /**
     * 订单id
     */
    private String id;
    /**
     *
     */
    private String name;
    /**
     * 删除id
     */
    private List<String> reqId;
}
