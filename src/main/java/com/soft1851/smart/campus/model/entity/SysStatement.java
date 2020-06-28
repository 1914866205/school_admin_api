package com.soft1851.smart.campus.model.entity;

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
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SysStatement {

    /**
     * 主键
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long pkStatementId;

    /**
     * 类型
     */
    @Column(name = "statement_type")
    private String statementType;

    /**
     * 标题
     */
    @Column(name = "statement_title", length = 32)
    private String statementTitle;

    /**
     * 内容
     */
    @Column(name = "statement_content", length = 32)
    private String statementContent;

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
