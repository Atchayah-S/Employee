package com.example.employeecrud.controllers;

import com.example.employeecrud.dao.Employees;
import com.example.employeecrud.dto.EmployeeDto;
import com.example.employeecrud.repository.EmployeesRepo;
import com.example.employeecrud.services.EmpServiceImplementation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class EmployeesController {
    @Autowired
    private EmployeesRepo emprepo;
    @Autowired
    private EmpServiceImplementation empSer;

    @PostMapping("/addEmployee")
    public ResponseEntity<EmployeeDto> createEmployee(@RequestBody Employees employee){
    EmployeeDto info=empSer.CreateEmployee(employee);

    return ResponseEntity.ok(info);
    }

    @GetMapping("/fetchall")
    public List<EmployeeDto> getAllEmployees(){
        return empSer.fetchAllEmployee();
    }

    @GetMapping("/fetchEmpById/{id}")
    public EmployeeDto getEmployeeById(@PathVariable Long id){
        return empSer.FetchById(id);
    }

    @PostMapping("/addAll")
    public List<EmployeeDto> addAllEmployees(@RequestBody List<Employees> employeesList){

        return empSer.addAllEmployees(employeesList);
    }

    @PutMapping("/update/{id}")
    public EmployeeDto update(@PathVariable Long id, @RequestBody Employees updatedData){
        return empSer.updateEmployee(id,updatedData);
    }

    @DeleteMapping("/del/{id}")
    public String deleteEmployee(@PathVariable long id){
        return empSer.deleteEmployee(id);
    }

    @PutMapping("/assignProject/{projId}/toEmployee/{empId}")
    public EmployeeDto assignProject(@PathVariable long empId,@PathVariable long projId){
        return empSer.assignProject(empId,projId);
    }
}
