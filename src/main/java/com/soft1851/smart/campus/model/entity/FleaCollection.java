package com.soft1851.smart.campus.model.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * @author 倪涛涛
 * @version 1.0.0
 * @ClassName a.java
 * @Description TODO
 * @createTime 2020年06月09日 11:26:00
 */
@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class FleaCollection {
    /**
     * id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long pkFleaCollectionId;


    /**
     * 用户id
     */
    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.REFRESH}, optional = false)
    private FleaUser fleaUser;

    /**
     * 商品id
     */
    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.REFRESH}, optional = false)
    private FleaGoods fleaGoods;

    /**
     * 收藏时间
     */
    @Column(nullable = false)
    private Timestamp createTime;

    /**
     * 删除标志
     */
    @Column(nullable = false,length = 4)
    private Boolean isDeleted;

}
