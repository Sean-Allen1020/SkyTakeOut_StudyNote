package com.loginfileuploadcode.service;

import com.loginfileuploadcode.pojo.Employee;
import com.loginfileuploadcode.pojo.EmployeeLoginDTO;

public interface EmployeeService {
    Employee login(EmployeeLoginDTO employeeLoginDTO);
}
