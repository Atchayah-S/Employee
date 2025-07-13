package com.example.employeecrud.services;

import com.example.employeecrud.dao.Department;
import com.example.employeecrud.exceptions.DepartmentNotFoundException;
import com.example.employeecrud.repository.DepartmentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class DeptServiceImplementation implements DeptService{
    @Autowired
    private DepartmentRepo departmentRepo;
 public Department saveDepartment(Department department){
     return departmentRepo.save(department);
 }
    public List<Department> saveAllDepartments(List<Department> departmentList){
     return departmentRepo.saveAll(departmentList);
 }
    public List<Department> getAllDepartments(){
     return departmentRepo.findAll();
    }

    public Department findDepartment(Long id){
     return departmentRepo.findById(id).orElseThrow(()-> new DepartmentNotFoundException("Department with id: "+id+" not found"));
    }

    public String deleteDepartment(Long id){
     departmentRepo.deleteById(id);
     return "Successfully Deleted";
    }
}
