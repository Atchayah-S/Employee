package com.example.employeecrud.controllers;

import com.example.employeecrud.dao.EmployeeProfile;
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
    public ProfileDto createProfile(@PathVariable long id, @RequestBody EmployeeProfile employeeProfile){
        return empProfileService.saveProfile(id,employeeProfile);
    }

    @GetMapping("/fetchProfile/{id}")
    public ProfileDto fetchProfile(@PathVariable long id){
        return empProfileService.getProfile(id);
    }

    @GetMapping("/fetchAll/employeesProfile")
    public List<ProfileDto> fetchAllProfile(){
        return empProfileService.getAllProfile();
    }

    @PutMapping("/updateProfile/{id}")
    public ProfileDto updateProfile(@PathVariable long id,@RequestBody EmployeeProfile updatedProfile){
        return empProfileService.updateEmployeeProfile(id,updatedProfile);
    }

    @DeleteMapping("/deleteProfile/{id}")
    public String deleteProfile(@PathVariable long id){
        return empProfileService.deleteProfile(id);
    }
}
