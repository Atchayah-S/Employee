package com.example.employeecrud.services;

import com.example.employeecrud.dao.Department;



import java.util.List;


public interface DepartmentService {
    public Department saveDepartment(Department department);
    public List<Department> getAllDepartments();
    public String deleteDepartment(Long id);
    public Department findDepartment(Long id);
    public List<Department> saveAllDepartments(List<Department> departmentList);
}
