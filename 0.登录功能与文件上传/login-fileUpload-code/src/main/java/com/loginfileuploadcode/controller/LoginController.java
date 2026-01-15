package com.loginfileuploadcode.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.loginfileuploadcode.pojo.Employee;
import com.loginfileuploadcode.pojo.EmployeeLoginDTO;
import com.loginfileuploadcode.pojo.EmployeeLoginVO;
import com.loginfileuploadcode.pojo.Result;
import com.loginfileuploadcode.service.EmployeeService;

@RestController
@Slf4j
public class LoginController {

    @Autowired
    private EmployeeService employeeService;

    @PostMapping("/login")
    public Result<EmployeeLoginVO> login(@RequestBody EmployeeLoginDTO employeeLoginDTO) {
        log.info("登录 {}", employeeLoginDTO);

        Employee employee = employeeService.login(employeeLoginDTO);
        if(employee == null){
            return Result.error("ログイン失敗");
        }

        EmployeeLoginVO employeeLoginVO = new EmployeeLoginVO(
                employee.getId(),
                employee.getName(),
                employee.getUsername()
        );
        //TODO 令牌导入
//        employeeLoginVO.setToken(null);

        return Result.success(employeeLoginVO);
    }
}
