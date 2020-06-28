package com.soft1851.smart.campus.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Tao
 * @version 1.0
 * @ClassName UpdateBorrowDto
 * @Description TODO
 * @date 2020-06-13 15:13
 **/
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UpdateBorrowDto {
    private Long id;
    private Boolean isDeleted;
}
