package com.example.employeecrud.controllers;

import com.example.employeecrud.dao.SalaryInfo;
import com.example.employeecrud.dto.AddressDto;
import com.example.employeecrud.dto.SalaryInfoDto;
import com.example.employeecrud.services.SalaryInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class SalaryInfoController {
    @Autowired
    private SalaryInfoService salaryInfoService;
    @PostMapping("/addSalaryInfo/Employee/{empId}")
    public SalaryInfoDto addSalaryInfo(@PathVariable long empId, @RequestBody SalaryInfo salaryInfo){
        return salaryInfoService.addSalaryInfo(empId,salaryInfo);
    }
    @PutMapping("/updateSalaryInfo/Employee/{empId}")
    public SalaryInfoDto updateSalaryInfo(@PathVariable long empId,@RequestBody SalaryInfo updatedSalaryInfo){
        return salaryInfoService.updateSalaryInfo(empId,updatedSalaryInfo);
    }
    @GetMapping("/fetchSalaryInfo/Employee/{empId}")
    public SalaryInfoDto fetchSalaryInfo(@PathVariable long empId){
        return salaryInfoService.fetchSalaryInfo(empId);
    }
    @DeleteMapping("/deleteSalaryInfo/{salaryInfoId}")
    public String deleteSalaryInfo(@PathVariable long salaryInfoId){
        return salaryInfoService.deleteSalaryInfo(salaryInfoId);
    }
}
