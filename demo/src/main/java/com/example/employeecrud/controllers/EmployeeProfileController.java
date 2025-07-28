package com.example.employeecrud.controllers;

import com.example.employeecrud.dao.EmployeeProfile;
import com.example.employeecrud.dto.GenericResponseEntity;
import com.example.employeecrud.dto.ProfileDto;
import com.example.employeecrud.services.EmpProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/EmployeeProfile")
public class EmployeeProfileController {
    @Autowired
    private EmpProfileService empProfileService;
    @PostMapping("/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public GenericResponseEntity<ProfileDto> createProfile(@PathVariable long id, @RequestBody EmployeeProfile employeeProfile){
        return GenericResponseEntity.<ProfileDto>builder()
                .message("Profile save successfully")
                .data(empProfileService.saveProfile(id,employeeProfile))
                .statusCode(201)
                .status(HttpStatus.CREATED)
                .success(true)
                .build();

    }

    @GetMapping("/{id}")
    public GenericResponseEntity<ProfileDto> fetchProfile(@PathVariable long id){
        return GenericResponseEntity.<ProfileDto>builder()
                .message("Employee Profile fetched successfully")
                .data(empProfileService.getProfile(id))
                .statusCode(200)
                .status(HttpStatus.OK)
                .success(true)
                .build();

    }

    @GetMapping
    public GenericResponseEntity<List<ProfileDto>> fetchAllProfile(){
        return GenericResponseEntity.<List<ProfileDto>>builder()
                .message("All Employees Profile fetched successfully")
                .data(empProfileService.getAllProfile())
                .statusCode(200)
                .status(HttpStatus.OK)
                .success(true)
                .build();
    }

    @PutMapping("/{id}")
    public GenericResponseEntity<ProfileDto> updateProfile(@PathVariable long id,@RequestBody EmployeeProfile updatedProfile){
        return GenericResponseEntity.<ProfileDto>builder()
                .message("Employee Profile updated successfully")
                .data(empProfileService.updateEmployeeProfile(id,updatedProfile))
                .statusCode(200)
                .status(HttpStatus.OK)
                .success(true)
                .build();

    }

    @DeleteMapping("/{id}")
    public GenericResponseEntity<String> deleteProfile(@PathVariable long id){
        return GenericResponseEntity.<String>builder()
                .message(empProfileService.deleteProfile(id))
                .data(null)
                .statusCode(200)
                .status(HttpStatus.OK)
                .success(true)
                .build();

    }
}
