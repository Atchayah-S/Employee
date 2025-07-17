package com.example.employeecrud.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class ProfileDto {
    private long profileId;
    private String joinDate;
    private String workLocationType;
    private String role;
    private long empId;

   // private EmployeeDto employeeDto;
}
