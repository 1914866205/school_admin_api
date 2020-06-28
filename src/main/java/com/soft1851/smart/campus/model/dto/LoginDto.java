package com.soft1851.smart.campus.model.dto;

import lombok.Data;

/**
 * @Description TODO
 * @Author wf
 * @Date 2020/5/30
 * @Version 1.0
 */
@Data
public class LoginDto {
    private String account;
    private String password;
    private String code;
}
