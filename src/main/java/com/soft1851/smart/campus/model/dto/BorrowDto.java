package com.soft1851.smart.campus.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Description TODO
 * @Author 涛涛
 * @Date 2020/6/4 11:34
 * @Version 1.0
 **/
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BorrowDto {
    //查询起始时间
    private String startTime;
    //查询截止时间
    private String endTime;
    //当前页
    private int currentPage;
    //页面大小
    private int pageSize;
}
