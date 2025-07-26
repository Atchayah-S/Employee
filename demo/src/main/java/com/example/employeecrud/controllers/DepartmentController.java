package com.example.employeecrud.controllers;

import com.example.employeecrud.dao.Department;
import com.example.employeecrud.dto.GenericResponseEntity;
import com.example.employeecrud.exceptions.InvalidDataException;
import com.example.employeecrud.repository.DepartmentRepo;
import com.example.employeecrud.services.DeptService;
import com.example.employeecrud.utitlity.DataValidation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class DepartmentController {
    @Autowired
    private DepartmentRepo deptRepo;
    @Autowired
    private DeptService deptService;

    @PostMapping("/addDepartment")
    public GenericResponseEntity<Department> addDepartment(@RequestBody Department department){
        if(DataValidation.validateDepartment(deptRepo,department.getDeptName())!=null)
            throw new InvalidDataException("Department "+department.getDeptName()+" Already exists");
        return GenericResponseEntity.<Department>builder()
                .message("Department added successfully")
                .data(deptService.saveDepartment(department))
                .success(true)
                .build();
    }


    @PostMapping("Department/addAll")
    public GenericResponseEntity<List<Department>> addAllDepartment(@RequestBody List<Department> departmentList){
        String existingDepartments="";
        for (Department department:departmentList){
            String error=DataValidation.validateDepartment(deptRepo,department.getDeptName());
            existingDepartments=error!=null?existingDepartments+department.getDeptName()+" ":existingDepartments;
        }
        if (existingDepartments!=null)
            throw new InvalidDataException(existingDepartments+" departments already exists");
        return GenericResponseEntity.<List<Department>>builder()
                .message("All department added successfully")
                .data(deptService.saveAllDepartments(departmentList))
                .success(true)
                .build();

    }

    @GetMapping("Department/fetchAll")
    public GenericResponseEntity<List<Department>> fetchAllDepartments(){
        return GenericResponseEntity.<List<Department>>builder()
                .message("All department added successfully")
                .data(deptService.getAllDepartments())
                .success(true)
                .build();

    }

    @PutMapping("updateDepartment/{id}")
    public GenericResponseEntity<Department> updateDepartment(@PathVariable Long id,@RequestBody Department updatedData){
        Department existingData=deptRepo.findById(id).orElse(null);
        if(existingData!=null){
        existingData.setDeptName(updatedData.getDeptName());}
        return GenericResponseEntity.<Department>builder()
                .message("Department details updated successfully")
                .data(deptService.saveDepartment(existingData))
                .success(true)
                .build();

    }

    @GetMapping("/fetchById/{id}")
    public GenericResponseEntity<Department> getDepartment(@PathVariable Long id){
        return GenericResponseEntity.<Department>builder()
                .message("Department fetched successfully")
                .data(deptService.findDepartment(id))
                .success(true)
                .build();

    }

    @DeleteMapping("/deleteDepartment/{id}")
    public GenericResponseEntity<String> deleteDepartment(@PathVariable Long id){
        return GenericResponseEntity.<String>builder()
                .message(deptService.deleteDepartment(id))
                .data(null)
                .success(true)
                .build();

    }
}
