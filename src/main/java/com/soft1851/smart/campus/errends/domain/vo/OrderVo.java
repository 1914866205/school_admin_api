package com.soft1851.smart.campus.errends.domain.vo;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.time.LocalDate;

/**
 * @author wl
 * @ClassNameOrderVo
 * @Description TODO
 * @Date 2020/6/26
 * @Version 1.0
 */
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)

public class OrderVo {
    private Integer orderCount;
    private LocalDate time;
}
