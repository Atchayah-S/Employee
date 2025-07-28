package com.example.employeecrud.controllers;

import com.example.employeecrud.dao.Department;
import com.example.employeecrud.dto.GenericResponseEntity;
import com.example.employeecrud.exceptions.InvalidDataException;
import com.example.employeecrud.repository.DepartmentRepo;
import com.example.employeecrud.services.DeptService;
import com.example.employeecrud.utitlity.DataValidation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/department")
public class DepartmentController {
    @Autowired
    private DepartmentRepo deptRepo;
    @Autowired
    private DeptService deptService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public GenericResponseEntity<Department> addDepartment(@RequestBody Department department){
        if(DataValidation.validateDepartment(deptRepo,department.getDeptName())!=null)
            throw new InvalidDataException("Department "+department.getDeptName()+" Already exists");
        return GenericResponseEntity.<Department>builder()
                .message("Department added successfully")
                .data(deptService.saveDepartment(department))
                .statusCode(201)
                .status(HttpStatus.CREATED)
                .success(true)
                .build();
    }


    @PostMapping("/addAll")
    @ResponseStatus(HttpStatus.CREATED)
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
                .statusCode(201)
                .status(HttpStatus.CREATED)
                .success(true)
                .build();

    }

    @GetMapping
    public GenericResponseEntity<List<Department>> fetchAllDepartments(){
        return GenericResponseEntity.<List<Department>>builder()
                .message("All department added successfully")
                .data(deptService.getAllDepartments())
                .statusCode(200)
                .status(HttpStatus.OK)
                .success(true)
                .build();

    }

    @PutMapping("/{id}")
    public GenericResponseEntity<Department> updateDepartment(@PathVariable Long id,@RequestBody Department updatedData){
        Department existingData=deptRepo.findById(id).orElse(null);
        if(existingData!=null){
        existingData.setDeptName(updatedData.getDeptName());}
        return GenericResponseEntity.<Department>builder()
                .message("Department details updated successfully")
                .data(deptService.saveDepartment(existingData))
                .statusCode(200)
                .status(HttpStatus.OK)
                .success(true)
                .build();

    }

    @GetMapping("/{id}")
    public GenericResponseEntity<Department> getDepartment(@PathVariable Long id){
        return GenericResponseEntity.<Department>builder()
                .message("Department fetched successfully")
                .data(deptService.findDepartment(id))
                .success(true)
                .statusCode(200)
                .status(HttpStatus.OK)
                .build();

    }

    @DeleteMapping("/{id}")
    public GenericResponseEntity<String> deleteDepartment(@PathVariable Long id){
        return GenericResponseEntity.<String>builder()
                .message(deptService.deleteDepartment(id))
                .data(null)
                .statusCode(200)
                .status(HttpStatus.OK)
                .success(true)
                .build();

    }
}
