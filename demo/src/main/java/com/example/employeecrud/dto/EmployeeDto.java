package com.example.employeecrud.dto;

import com.example.employeecrud.dao.Department;
import com.example.employeecrud.dao.EmployeeProfile;
import com.example.employeecrud.dao.Project;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter @Setter
public class EmployeeDto {
    private Long id;
    private String name;
    private String email;
    private String phone;
    private DepartmentDto department;
   private ProfileDto profile;
   private List<ProjectDto> project;
}
