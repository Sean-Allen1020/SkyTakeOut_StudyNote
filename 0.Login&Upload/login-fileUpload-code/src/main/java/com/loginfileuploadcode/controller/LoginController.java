package com.loginfileuploadcode.controller;

import com.loginfileuploadcode.properties.JwtProperties;
import com.loginfileuploadcode.utils.JwtUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
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

import javax.security.auth.login.AccountNotFoundException;
import java.util.HashMap;
import java.util.Map;

@RestController
@Slf4j
@Api(tags = "登录退出接口")
public class LoginController {

    @Autowired
    private EmployeeService employeeService;
    @Autowired
    private JwtProperties jwtProperties;

    @PostMapping("/login")
    @ApiOperation("员工登录")
    public Result<EmployeeLoginVO> login(@RequestBody EmployeeLoginDTO employeeLoginDTO) throws AccountNotFoundException {
        log.info("登录开始 {}", employeeLoginDTO);

        Employee employee = employeeService.login(employeeLoginDTO);

        //令牌生成需要的自定义信息
        Map<String, Object> claims = new HashMap<>();
        claims.put("id", employee.getId());
        //令牌生成
        String token = JwtUtil.createJwt(claims, jwtProperties.getSecretKey(), jwtProperties.getTtl());

        EmployeeLoginVO employeeLoginVO = new EmployeeLoginVO(
                employee.getId(),
                employee.getName(),
                employee.getUsername(),
                token
        );
        log.info("登录成功 {}", employeeLoginVO);

        return Result.success(employeeLoginVO);
    }
}
