package com.example.employeecrud.controllers;

import com.example.employeecrud.dao.Department;
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
    public Department addDepartment(@RequestBody Department department){
        if(DataValidation.validateDepartment(deptRepo,department.getDeptName())!=null)
            throw new InvalidDataException("Department "+department.getDeptName()+" Already exists");
        return deptService.saveDepartment(department);
    }


    @PostMapping("Department/addAll")
    public List<Department> addAllDepartment(@RequestBody List<Department> departmentList){
        String existingDepartments="";
        for (Department department:departmentList){
            String error=DataValidation.validateDepartment(deptRepo,department.getDeptName());
            existingDepartments=error!=null?existingDepartments+department.getDeptName()+" ":existingDepartments;
        }
        if (existingDepartments!=null)
            throw new InvalidDataException(existingDepartments+" departments already exists");
        return deptService.saveAllDepartments(departmentList);
    }

    @GetMapping("Department/fetchAll")
    public List<Department> fetchAllDepartments(){
        return deptService.getAllDepartments();
    }

    @PutMapping("updateDepartment/{id}")
    public Department updateDepartment(@PathVariable Long id,@RequestBody Department updatedData){
        Department existingData=deptRepo.findById(id).orElse(null);
        if(existingData!=null){
        existingData.setDeptName(updatedData.getDeptName());}
        return deptService.saveDepartment(existingData);
    }

    @GetMapping("/fetchById/{id}")
    public Department getDepartment(@PathVariable Long id){
        return deptService.findDepartment(id);
    }

    @DeleteMapping("/deleteDepartment/{id}")
    public String deleteDepartment(@PathVariable Long id){
        return deptService.deleteDepartment(id);
    }
}
