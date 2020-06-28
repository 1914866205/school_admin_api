package com.soft1851.smart.campus.model.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.util.Date;

/**
 * @Description TODO
 * @Author wf
 * @Date 2020/6/3
 * @Version 1.0
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TowerVo {
    private Long roomId;
    private String roomName;
    private String towerName;
    private Long towerId;
    private Long unitId;
    private String unitName;
    private Date gmtGreate;
    private Double electricityBalance;
}
