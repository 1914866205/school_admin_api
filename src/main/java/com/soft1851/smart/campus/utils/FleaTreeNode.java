package com.soft1851.smart.campus.utils;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @Description TODO
 * @Author 涛涛
 * @Date 2020/4/24 9:46
 * @Version 1.0
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FleaTreeNode {
    private Long pkFleaTypeId;
    private Long parentId;
    private String typeName;
    private String typeCoverUrl;
    private String typeUrl;
    private List<FleaTreeNode> subTypes;


    public FleaTreeNode(Long pkFleaTypeId, Long parentId, String typeName, String typeCoverUrl, String typeUrl) {
        this.pkFleaTypeId = pkFleaTypeId;
        this.parentId = parentId;
        this.typeName = typeName;
        this.typeCoverUrl = typeCoverUrl;
        this.typeUrl = typeUrl;
    }

}
