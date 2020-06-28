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
public class FleaOrder {
    /**
     * id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String pkFleaOrderId;

    /**
     * 商品id
     */
    @OneToOne(cascade = {CascadeType.MERGE, CascadeType.REFRESH}, optional = false)
    private FleaGoods fleaGoods;

    /**
     * 买方id
     */
    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.REFRESH}, optional = false)
    private FleaUser fleaUserBuyer;
    /**
     * 卖方id
     */
    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.REFRESH}, optional = false)
    private FleaUser fleaUserSeller;
    /**
     * 创建时间
     */
    @Column(nullable = false)
    private Timestamp createTime;

    /**
     * 删除标志
     */
    @Column(nullable = false, length = 4)
    private Boolean isDeleted;
}
