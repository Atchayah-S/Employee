package com.example.employeecrud.dto;

import com.example.employeecrud.dao.Department;
import lombok.Data;

@Data
public class EmployeeDto {
    private Long id;
    private String name;
    private String email;
    private String phone;
    private DepartmentDto department;
}
