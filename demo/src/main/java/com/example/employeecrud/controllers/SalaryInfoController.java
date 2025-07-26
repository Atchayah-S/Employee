package com.example.employeecrud.controllers;

import com.example.employeecrud.dao.SalaryInfo;
import com.example.employeecrud.dto.GenericResponseEntity;
import com.example.employeecrud.dto.SalaryInfoDto;
import com.example.employeecrud.services.SalaryInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class SalaryInfoController {
    @Autowired
    private SalaryInfoService salaryInfoService;
    @PostMapping("/addSalaryInfo/Employee/{empId}")
    public GenericResponseEntity<SalaryInfoDto> addSalaryInfo(@PathVariable long empId, @RequestBody SalaryInfo salaryInfo){
        return GenericResponseEntity.<SalaryInfoDto>builder()
                .message("Salary Info created successfully")
                .data(salaryInfoService.addSalaryInfo(empId,salaryInfo))
                .success(true)
                .build();

    }
    @PutMapping("/updateSalaryInfo/Employee/{empId}")
    public GenericResponseEntity<SalaryInfoDto> updateSalaryInfo(@PathVariable long empId,@RequestBody SalaryInfo updatedSalaryInfo){
        return GenericResponseEntity.<SalaryInfoDto>builder()
                .message("Salary Info updated successfully")
                .data(salaryInfoService.updateSalaryInfo(empId,updatedSalaryInfo))
                .success(true)
                .build();

    }
    @GetMapping("/fetchSalaryInfo/Employee/{empId}")
    public GenericResponseEntity<SalaryInfoDto> fetchSalaryInfo(@PathVariable long empId){
        return GenericResponseEntity.<SalaryInfoDto>builder()
                .message("Salary Info fetched successfully")
                .data( salaryInfoService.fetchSalaryInfo(empId))
                .success(true)
                .build();

    }
    @DeleteMapping("/deleteSalaryInfo/{salaryInfoId}")
    public GenericResponseEntity<String> deleteSalaryInfo(@PathVariable long salaryInfoId){
        return GenericResponseEntity.<String>builder()
                .message(salaryInfoService.deleteSalaryInfo(salaryInfoId))
                .data( null)
                .success(true)
                .build();

    }
}
