package com.example.employeecrud.controllers;

import com.example.employeecrud.dao.EmployeeProfile;
import com.example.employeecrud.dto.GenericResponseEntity;
import com.example.employeecrud.dto.ProfileDto;
import com.example.employeecrud.services.EmpProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class EmployeeProfileController {
    @Autowired
    private EmpProfileService empProfileService;
    @PostMapping("/createProfile/{id}")
    public GenericResponseEntity<ProfileDto> createProfile(@PathVariable long id, @RequestBody EmployeeProfile employeeProfile){
        return GenericResponseEntity.<ProfileDto>builder()
                .message("Profile save successfully")
                .data(empProfileService.saveProfile(id,employeeProfile))
                .success(true)
                .build();

    }

    @GetMapping("/fetchProfile/{id}")
    public GenericResponseEntity<ProfileDto> fetchProfile(@PathVariable long id){
        return GenericResponseEntity.<ProfileDto>builder()
                .message("Employee Profile fetched successfully")
                .data(empProfileService.getProfile(id))
                .success(true)
                .build();

    }

    @GetMapping("/fetchAll/employeesProfile")
    public GenericResponseEntity<List<ProfileDto>> fetchAllProfile(){
        return GenericResponseEntity.<List<ProfileDto>>builder()
                .message("All Employees Profile fetched successfully")
                .data(empProfileService.getAllProfile())
                .success(true)
                .build();
    }

    @PutMapping("/updateProfile/{id}")
    public GenericResponseEntity<ProfileDto> updateProfile(@PathVariable long id,@RequestBody EmployeeProfile updatedProfile){
        return GenericResponseEntity.<ProfileDto>builder()
                .message("Employee Profile updated successfully")
                .data(empProfileService.updateEmployeeProfile(id,updatedProfile))
                .success(true)
                .build();

    }

    @DeleteMapping("/deleteProfile/{id}")
    public GenericResponseEntity<String> deleteProfile(@PathVariable long id){
        return GenericResponseEntity.<String>builder()
                .message(empProfileService.deleteProfile(id))
                .data(null)
                .success(true)
                .build();

    }
}
