package com.soft1851.smart.campus.model.vo;

import com.soft1851.smart.campus.model.entity.Company;
import com.soft1851.smart.campus.model.entity.JobType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.sql.Timestamp;

/**
 * @author su
 * @className Job
 * @Description TODO
 * @Date 2020/6/12
 * @Version 1.0
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class JobVo {

    private Long pkJobId;
    private String name;
    private String description;
    private String boss;
    private String bossPhone;
    private Company company;
    private String workplace;
    private String workingTime;
//    private BigDecimal pay;
    private Integer min;
    private Integer max;
    private String experience;
    private String diploma;
    private JobType jobType;
    private Integer number;
    private Timestamp gmtCreate;

}
