package com.soft1851.smart.campus.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Description TODO
 * @Author 涛涛
 * @Date 2020/6/4 15:24
 * @Version 1.0
 **/
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BorrowInsertDto {
    //借阅人
    private String borrowUserName;
    //借阅人学号
    private String borrowUserNumber;
    //借阅人手机号
    private String borrowUserPhone;
    //借阅书籍名称
    private String borrowBookName;
    //借阅书籍编号
    private String borrowBookId;
}
