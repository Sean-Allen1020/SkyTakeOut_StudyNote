package com.loginfileuploadcode.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 前端接收的属性
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeLoginDTO {

    private String username;

    private String password;
}
