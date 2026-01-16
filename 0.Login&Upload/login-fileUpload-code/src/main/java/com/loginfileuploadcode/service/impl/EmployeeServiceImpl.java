package com.loginfileuploadcode.service.impl;

import com.loginfileuploadcode.exception.PasswordErrorException;
import com.loginfileuploadcode.mapper.EmployeeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;
import com.loginfileuploadcode.pojo.Employee;
import com.loginfileuploadcode.pojo.EmployeeLoginDTO;
import com.loginfileuploadcode.service.EmployeeService;

import javax.security.auth.login.AccountNotFoundException;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    EmployeeMapper employeeMapper;

    @Override
    public Employee login(EmployeeLoginDTO employeeLoginDTO) throws AccountNotFoundException {
        //1. 获取前端传来的账号信息
        String username = employeeLoginDTO.getUsername();
        String password = employeeLoginDTO.getPassword();

        Employee employee = employeeMapper.getByUsername(username);
        //2. 账号不存在
        if(employee == null){
            throw new AccountNotFoundException("アカウントは存在しない");
        }
        //3. 密码比对
        //将明文密码加密为md5格式
        String passwordMd5 = DigestUtils.md5DigestAsHex(password.getBytes());

        if(!employee.getPassword().equals(passwordMd5)){
            throw new PasswordErrorException("パスワードは正しくない");
        }

        return employee;
    }
}

