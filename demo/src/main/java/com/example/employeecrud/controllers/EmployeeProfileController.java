package com.example.employeecrud.controllers;

import com.example.employeecrud.dao.EmployeeProfile;
import com.example.employeecrud.dto.GenericResponseEntity;
import com.example.employeecrud.dto.ProfileDto;
import com.example.employeecrud.services.EmployeeProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/EmployeeProfile")
public class EmployeeProfileController {
    private final String PROFILE_ADDED_SUCCESSFULLY="Employee Profile Added successfully";
    private final String PROFILE_FETCHED_SUCCESSFULLY="Employee Profile fetched successfully";
    private final String PROFILE_UPDATED_SUCCESSFULLY="Employee Profile updated successfully";
    @Autowired
    private EmployeeProfileService employeeProfileService;
    @PostMapping("/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public GenericResponseEntity<ProfileDto> createProfile(@PathVariable long id, @RequestBody EmployeeProfile employeeProfile){
        return GenericResponseEntity.<ProfileDto>builder()
                .message(PROFILE_ADDED_SUCCESSFULLY)
                .data(employeeProfileService.saveProfile(id,employeeProfile))
                .statusCode(201)
                .status(HttpStatus.CREATED)
                .success(true)
                .build();

    }

    @GetMapping("/{id}")
    public GenericResponseEntity<ProfileDto> fetchProfile(@PathVariable long id){
        return GenericResponseEntity.<ProfileDto>builder()
                .message(PROFILE_FETCHED_SUCCESSFULLY)
                .data(employeeProfileService.getProfile(id))
                .statusCode(200)
                .status(HttpStatus.OK)
                .success(true)
                .build();

    }

    @GetMapping
    public GenericResponseEntity<List<ProfileDto>> fetchAllProfile(){
        return GenericResponseEntity.<List<ProfileDto>>builder()
                .message(PROFILE_FETCHED_SUCCESSFULLY)
                .data(employeeProfileService.getAllProfile())
                .statusCode(200)
                .status(HttpStatus.OK)
                .success(true)
                .build();
    }

    @PutMapping("/{id}")
    public GenericResponseEntity<ProfileDto> updateProfile(@PathVariable long id,@RequestBody EmployeeProfile updatedProfile){
        return GenericResponseEntity.<ProfileDto>builder()
                .message(PROFILE_UPDATED_SUCCESSFULLY)
                .data(employeeProfileService.updateEmployeeProfile(id,updatedProfile))
                .statusCode(200)
                .status(HttpStatus.OK)
                .success(true)
                .build();

    }

    @DeleteMapping("/{id}")
    public GenericResponseEntity<String> deleteProfile(@PathVariable long id){
        return GenericResponseEntity.<String>builder()
                .message(employeeProfileService.deleteProfile(id))
                .data(null)
                .statusCode(200)
                .status(HttpStatus.OK)
                .success(true)
                .build();

    }
}
