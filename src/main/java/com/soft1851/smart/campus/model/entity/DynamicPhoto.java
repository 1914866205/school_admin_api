package com.soft1851.smart.campus.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.Id;
import java.sql.Timestamp;

/**
 * @author Yujie_Zhao
 * @ClassName DynamicPhoto
 * @Description TODO
 * @Date 2020/6/12  14:25
 * @Version 1.0
 **/
@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class DynamicPhoto {

    @Id
    private String pkDynamicPhotoId;

    /**
     * 动态id
     */
    @Column(nullable = false)
    private String dynamicId;

    /**
     * 图片地址
     */
    @Column(nullable = false)
    private String picture;

    /**
     * 创建时间
     */
    @Column(nullable = false)
    @CreatedDate
    private Timestamp gmtCreate;

    /**
     * 修改时间
     */
    @JsonIgnore
    @Column(nullable = false)
    @LastModifiedDate
    private Timestamp gmtModified;

    /**
     * 删除标志（0 逻辑删除， 1 未删除）
     */
    @Column(nullable = false, length = 4)
    private Boolean isDeleted = false;

}
