package com.soft1851.smart.campus.utils;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author wf
 * @version 1.0
 * @ClassName TreeNode
 * @Description TODO
 * @date 2020-05-30
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TreeNode {
    private Long pkMenuId;

    private Long parentId;

    private Integer type;

    private String name;

    private String icon;

    private String path;

    private Integer sort;

    private List<TreeNode> subMenus;

    public TreeNode(Long pkMenuId, Long parentId, Integer type,String name, String icon, String path, Integer sort) {
        this.pkMenuId = pkMenuId;
        this.parentId = parentId;
        this.type = type;
        this.name = name;
        this.icon = icon;
        this.path = path;
        this.sort = sort;
    }
}
