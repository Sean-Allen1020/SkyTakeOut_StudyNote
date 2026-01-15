package com.loginfileuploadcode.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 登录封装结果
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeLoginVO {
    private Long id;

    private String name;

    private String username;

//    private String token;
}
