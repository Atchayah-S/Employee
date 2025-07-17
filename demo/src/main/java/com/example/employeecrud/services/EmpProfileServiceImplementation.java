package com.example.employeecrud.services;

import com.example.employeecrud.dao.EmployeeProfile;
import com.example.employeecrud.dao.Employees;
import com.example.employeecrud.dto.ProfileDto;
import com.example.employeecrud.exceptions.EmployeeNotFoundException;
import com.example.employeecrud.exceptions.ResourceNotFoundException;
import com.example.employeecrud.mapper.ProfileMapper;
import com.example.employeecrud.repository.EmployeeProfileRepo;
import com.example.employeecrud.repository.EmployeesRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmpProfileServiceImplementation implements EmpProfileService{
    @Autowired
    EmployeeProfileRepo profileRepo;
    @Autowired
    EmployeesRepo employeesRepo;
    public ProfileDto saveProfile(Long empId,EmployeeProfile employeeProfile){
        Employees employee=employeesRepo.findById(empId).orElseThrow(()->new EmployeeNotFoundException("Employee id not found"));
        employeeProfile.setEmployee(employee);
     return ProfileMapper.toProfileDto(profileRepo.save(employeeProfile));
    }

    public ProfileDto getProfile(Long id){
        return ProfileMapper.toProfileDto(profileRepo.findById(id).orElseThrow(()->new ResourceNotFoundException("Profile id: "+id+" not found")));
    }

    @Override
    public List<ProfileDto> getAllProfile() {
        return ProfileMapper.toProfileDtoList(profileRepo.findAll());
    }

    @Override
    public ProfileDto updateEmployeeProfile(Long id, EmployeeProfile updateProfile) {
        EmployeeProfile existingProfile=profileRepo.findById(id).
                orElseThrow(()-> new ResourceNotFoundException("Profile id: "+id+" not found"));
        existingProfile.setRole(updateProfile.getRole());
        existingProfile.setJoinDate(updateProfile.getJoinDate());
        existingProfile.setWorkLocationType(updateProfile.getWorkLocationType());
              return ProfileMapper.toProfileDto(profileRepo.save(existingProfile));
    }

    @Override
    public String deleteProfile(Long id) {
        EmployeeProfile profile=profileRepo.findById(id).orElseThrow(()->new ResourceNotFoundException("Profile id: "+id+" not found"));
        profile.getEmployee().setEmployeeProfile(null);
        profileRepo.deleteById(id);
        return "Profile Deleted Successfully";
    }


}
