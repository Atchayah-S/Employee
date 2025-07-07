package com.example.employeecrud.mapper;

import com.example.employeecrud.dao.Employees;
import com.example.employeecrud.dto.EmployeeDto;

import java.util.List;

public class EmployeeMapper {
    public static EmployeeDto EmployeesToEmployeeDto(Employees employee){
        EmployeeDto empdto=new EmployeeDto();
        empdto.setId(employee.getEmp_id());
        empdto.setName(employee.getName());
        empdto.setPhone(employee.getPhone());
        empdto.setEmail(employee.getEmail());
        return empdto;
    }
    public static List<EmployeeDto> EmployeesToEmployeeDtoList(List<Employees> employeesList) {
        return employeesList.stream()
                .map(EmployeeMapper::EmployeesToEmployeeDto)
                .toList();
    }
}
