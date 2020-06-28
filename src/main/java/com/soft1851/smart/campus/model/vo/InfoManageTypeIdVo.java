package com.soft1851.smart.campus.model.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

/**
 * @author Yujie_Zhao
 * @ClassName InfoManageTypeId
 * @Description TODO
 * @Date 2020/6/2  11:08
 * @Version 1.0
 **/
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class InfoManageTypeIdVo {

    /**
     * 分类id
     */
    private Long typeId;

    /**
     * 主键id
     */
    private Long pkInfoManageId;

    /**
     * 标题
     */
    private String title;

    /**
     * 封面
     */
    private String cover;

    /**
     * 内容
     */
    private String text;
    /**
     * 置顶标志（ 0 不置顶， 1 置顶）
     */
    private Boolean isTop;
    /**
     * 创建时间
     */
    private Timestamp gmtCreate;
    /**
     * 更新时间
     */
    private Timestamp gmtModified;
    /**
     * 删除标志（0 未删除， 1 逻辑删除）
     */
    private Boolean isDeleted;
}
