package com.soft1851.smart.campus.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Tao
 * @version 1.0
 * @ClassName UpdateSysStatementDto
 * @Description TODO
 * @date 2020-06-12 8:31
 **/
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UpdateSysStatementDto {
    /**
     * 声明id
     */
    private Long pkStatementId;
    /**
     * 声明标题
     */
    private String statementTitle;
    /**
     * 声明类型
     */
    private String statementType;
    /**
     * 声明内容
     */
    private String statementContent;
}
