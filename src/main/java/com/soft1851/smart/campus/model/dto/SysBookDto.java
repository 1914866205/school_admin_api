package com.soft1851.smart.campus.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

/**
 * @Description 传来的图书
 * @Author 涛涛
 * @Date 2020/6/1 8:25
 * @Version 1.0
 **/
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SysBookDto {
    /**
     * 图书名id
     */
    private Long id;
    /**
     * 作者
     */
    private String author;
    /**
     * 封面
     */
    private String cover;
    /**
     * 类型
     */
    private String type;
    /**
     * 简介
     */
    private String description;
    /**
     * 书名
     */
    private String bookName;
    /**
     * 库存总数
     */
    private Integer bookNumber;

    /**
     * 状态
     */
    private Boolean status;
}
