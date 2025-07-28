package com.example.employeecrud.services;

import com.example.employeecrud.dao.EmployeeProfile;
import com.example.employeecrud.dto.ProfileDto;

import java.util.List;

public interface EmployeeProfileService {
    public ProfileDto saveProfile(Long empId,EmployeeProfile employeeProfile);
    public ProfileDto getProfile(Long id);
    public List<ProfileDto> getAllProfile();
    public ProfileDto updateEmployeeProfile(Long id,EmployeeProfile updateProfile);
    public String deleteProfile(Long id);
}
