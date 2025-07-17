package com.example.employeecrud.services;

import com.example.employeecrud.dao.Employees;
import com.example.employeecrud.dto.EmployeeDto;

import java.util.List;

public interface EmpService {
   EmployeeDto CreateEmployee(Employees employee);
   List<EmployeeDto> addAllEmployees(List<Employees> employeesList);
   EmployeeDto FetchById(Long id);
   String deleteEmployee(Long id);
   EmployeeDto updateEmployee(Long id, Employees updatedData);
   List<EmployeeDto> fetchAllEmployee();
   EmployeeDto assignProject(Long empId,Long projId);
}
