package com.example.employeecrud.mapper;

import com.example.employeecrud.dao.Department;
import com.example.employeecrud.dto.DepartmentDto;

public class DepartmentMapper {
    public static DepartmentDto toDepartmentDto(Department department){
        DepartmentDto departmentDto=new DepartmentDto();
        departmentDto.setDeptId(department.getDeptID());
        departmentDto.setDeptName(department.getDeptName());
        return departmentDto;
    }
}
