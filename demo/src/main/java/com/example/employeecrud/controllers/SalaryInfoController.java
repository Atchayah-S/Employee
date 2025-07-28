package com.example.employeecrud.controllers;

import com.example.employeecrud.dao.SalaryInfo;
import com.example.employeecrud.dto.GenericResponseEntity;
import com.example.employeecrud.dto.SalaryInfoDto;
import com.example.employeecrud.services.SalaryInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/salaryInfo")
public class SalaryInfoController {
    @Autowired
    private SalaryInfoService salaryInfoService;
    @PostMapping("/{empId}")
    @ResponseStatus(HttpStatus.CREATED)
    public GenericResponseEntity<SalaryInfoDto> addSalaryInfo(@PathVariable long empId, @RequestBody SalaryInfo salaryInfo){
        return GenericResponseEntity.<SalaryInfoDto>builder()
                .message("Salary Info created successfully")
                .data(salaryInfoService.addSalaryInfo(empId,salaryInfo))
                .statusCode(201)
                .status(HttpStatus.CREATED)
                .success(true)
                .build();
    }
    @PutMapping("/{empId}")
    public GenericResponseEntity<SalaryInfoDto> updateSalaryInfo(@PathVariable long empId,@RequestBody SalaryInfo updatedSalaryInfo){
        return GenericResponseEntity.<SalaryInfoDto>builder()
                .message("Salary Info updated successfully")
                .data(salaryInfoService.updateSalaryInfo(empId,updatedSalaryInfo))
                .statusCode(200)
                .status(HttpStatus.OK)
                .success(true)
                .build();

    }
    @GetMapping("/Employee/{empId}")
    public GenericResponseEntity<SalaryInfoDto> fetchSalaryInfo(@PathVariable long empId){
        return GenericResponseEntity.<SalaryInfoDto>builder()
                .message("Salary Info fetched successfully")
                .data( salaryInfoService.fetchSalaryInfo(empId))
                .statusCode(200)
                .status(HttpStatus.OK)
                .success(true)
                .build();

    }
    @DeleteMapping("/{salaryInfoId}")
    public GenericResponseEntity<String> deleteSalaryInfo(@PathVariable long salaryInfoId){
        return GenericResponseEntity.<String>builder()
                .message(salaryInfoService.deleteSalaryInfo(salaryInfoId))
                .data( null)
                .statusCode(200)
                .status(HttpStatus.OK)
                .success(true)
                .build();

    }
}
