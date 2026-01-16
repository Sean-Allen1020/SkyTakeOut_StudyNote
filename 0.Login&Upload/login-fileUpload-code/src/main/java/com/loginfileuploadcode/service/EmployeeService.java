package com.loginfileuploadcode.service;

import com.loginfileuploadcode.pojo.Employee;
import com.loginfileuploadcode.pojo.EmployeeLoginDTO;

import javax.security.auth.login.AccountNotFoundException;

public interface EmployeeService {
    Employee login(EmployeeLoginDTO employeeLoginDTO) throws AccountNotFoundException;
}
