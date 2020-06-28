package com.soft1851.smart.campus.model.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @Author yhChen
 * @Description
 * @Date 2020/6/13
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class FleaRewardVo {
    private Long pkFleaRewardId;
    private String title;
    private String description;
    private String imageUrl;
    private Date createTime;
    private Boolean isDeleted;
    private String nickname;
    private String username;
    private String sex;
}
