package com.loginfileuploadcode.service.impl;

import com.loginfileuploadcode.mapper.EmployeeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;
import com.loginfileuploadcode.pojo.Employee;
import com.loginfileuploadcode.pojo.EmployeeLoginDTO;
import com.loginfileuploadcode.service.EmployeeService;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    EmployeeMapper employeeMapper;

    @Override
    public Employee login(EmployeeLoginDTO employeeLoginDTO) {

        //将明文密码加密为md5格式
        String password = DigestUtils.md5DigestAsHex(employeeLoginDTO.getPassword().getBytes());
        employeeLoginDTO.setPassword(password);
        Employee employee = employeeMapper.getByUsernameAndPassword(employeeLoginDTO);

        //验证账号信息是否为空
        if(employee == null){
            return null;
        }


        return employee;
    }
}

