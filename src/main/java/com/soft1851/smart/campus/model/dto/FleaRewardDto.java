package com.soft1851.smart.campus.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author 倪涛涛
 * @version 1.0.0
 * @ClassName RewardDto.java
 * @Description TODO
 * @createTime 2020年06月11日 10:32:00
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FleaRewardDto {
    /**
     * 悬赏Id
     */
    private Long pkRewardId;
    /**
     * 描述
     */
    private String description;

    /**
     * 图片地址
     */
    private String imageUrl;


    /**
     * 标题
     */
    private String title;

    /**
     * 发布人Id
     */
    private Long fleaUserId;

    public FleaRewardDto(String description, String imageUrl, String title, Long fleaUserId) {
        this.description = description;
        this.imageUrl = imageUrl;
        this.title = title;
        this.fleaUserId = fleaUserId;
    }
}
