package com.soft1851.smart.campus.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Tao
 * @version 1.0
 * @ClassName SysFeedbackDto
 * @Description TODO
 * @date 2020-06-12 9:17
 **/
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UpdateSysFeedbackDto {
    private Long pkFeedbackId;
    private Boolean isHandled;
}
