package com.example.employeecrud.dto;


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
   private List<AddressDto> address;
   private SalaryInfoDto salaryInfoDto;
}
