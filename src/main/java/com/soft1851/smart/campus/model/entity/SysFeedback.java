package com.soft1851.smart.campus.model.entity;

import com.soft1851.smart.campus.annotation.ExcelVoAttribute;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * @Description TODO
 * @Author wf
 * @Date 2020/5/25
 * @Version 1.0
 */
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SysFeedback {

    /**
     * 主键
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ExcelVoAttribute(name = "反馈id",column = 0)
    private Long pkFeedbackId;

    /**
     * 标题
     */
    @Column(name = "title")
    @ExcelVoAttribute(name = "标题",column = 1)
    private String title;

    /**
     * 内容
     */
    @Column(name = "content")
    @ExcelVoAttribute(name = "内容",column = 2)
    private String content;

    /**
     * 联系方式
     */
    @Column(name = "contact_way")
    @ExcelVoAttribute(name = "联系电话",column = 3)
    private String contactWay;

    /**
     * 处理状态（0 未处理， 1 已处理）
     */
    @Column(name = "is_handled")
    private Boolean isHandled;

    /**
     * 图片内容
     */
    @Column(name = "pic_info")
    private String picInfo;

    /**
     * 创建时间
     */
    @Column(name = "gmt_create")
    private Timestamp gmtCreate;

    /**
     * 更新时间
     */
    @Column(name = "gmt_modified")
    private Timestamp gmtModified;

    /**
     * 删除标志（0 未删除， 1 已删除）
     */
    @Column(name = "is_deleted")
    private Boolean isDeleted;
}
