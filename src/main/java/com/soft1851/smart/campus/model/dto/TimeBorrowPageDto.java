package com.soft1851.smart.campus.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Tao
 * @version 1.0
 * @ClassName TimeBorrowPageDto
 * @Description TODO
 * @date 2020-06-13 20:53
 **/
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TimeBorrowPageDto {
    private String time;
    private int currentPage;
    private int pageSize;
}
