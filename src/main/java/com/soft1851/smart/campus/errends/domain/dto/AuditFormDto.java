package com.soft1851.smart.campus.errends.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author wl
 * @ClassNameAudiFormDto
 * @Description TODO
 * @Date 2020/6/17
 * @Version 1.0
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AuditFormDto {

    private String remark;
    private String founderId;
    private String reviewerId;
    private Integer stauts;
}
