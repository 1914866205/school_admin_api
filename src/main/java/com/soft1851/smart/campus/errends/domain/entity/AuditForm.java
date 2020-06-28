package com.soft1851.smart.campus.errends.domain.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * @author wl
 * @ClassNameAuditForm
 * @Description TODO
 * @Date 2020/6/17
 * @Version 1.0
 */

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@EntityListeners(AuditingEntityListener.class)
@Table(name = "audit_form")
public class AuditForm {

    /**
     * 主键自增
     */
    @Id
    @Column(name = "id",length = 30)
    private String id;
    /**
     * 结束时间
     */
    @Column(name = "end_time", nullable = false)
    private Timestamp endTime;
    /**
     * 申请人
     */
    @Column(name = "founderId", nullable = false)
    private String founderId;
    /**
     * 原因
     */
    @Column(name = "remark", nullable = false)
    private String remark;
    /**
     * 审核人
     */
    @Column(name = "reviewer_id", nullable = false)
    private String reviewerId;
    /**
     * 状态 0是通过 1是未通过
     */
    @Column(name = "stauts", nullable = false)
    private Integer stauts;
    /**
     * 创建时间
     */
    //@JsonIgnore
    @Column(nullable = false)
    @CreatedDate
    private Timestamp gmtCreate;

    /**
     * 修改时间
     */
    @JsonIgnore
    @LastModifiedDate
    @Column(nullable = false)
    private Timestamp gmtModified;

    /**
     * 删除标志（0 逻辑删除， 1 未删除）
     */
//    @JsonIgnore
    @Column(nullable = false, length = 4)
    private Boolean isDeleted;
}
