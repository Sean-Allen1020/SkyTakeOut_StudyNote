package com.loginfileuploadcode.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import com.loginfileuploadcode.pojo.Employee;
import com.loginfileuploadcode.pojo.EmployeeLoginDTO;

@Mapper
public interface EmployeeMapper {

    @Select("select * from employee where username = #{username} and password = #{password}")
    Employee getByUsernameAndPassword(EmployeeLoginDTO employeeLoginDTO);
}
