package com.example.employeecrud.controllers;

import com.example.employeecrud.Security.JwtUtils;
import com.example.employeecrud.dao.Employees;
import com.example.employeecrud.dto.EmployeeDto;
import com.example.employeecrud.repository.EmployeesRepo;
import com.example.employeecrud.services.EmpServiceImplementation;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/Employee")
public class EmployeesController {
    @Autowired
    private EmployeesRepo emprepo;
    @Autowired
    private EmpServiceImplementation empSer;
    @Autowired
    private JwtUtils jwtUtils;
    @PostMapping("/addEmployee")
    public ResponseEntity<EmployeeDto> createEmployee(@RequestBody Employees employee){
    EmployeeDto info=empSer.CreateEmployee(employee);

    return ResponseEntity.ok(info);
    }

    @GetMapping("/fetchall")
    public List<EmployeeDto> getAllEmployees(){
        return empSer.fetchAllEmployee();
    }

    @GetMapping("/fetchEmpById")
    public EmployeeDto getEmployeeById(HttpServletRequest request){
        String authHeader = request.getHeader("Authorization");
        String token = authHeader.substring(7);
        long id=jwtUtils.extractEmployeeId(token);
        return empSer.FetchById(id);
    }

    @PostMapping("/addAll")
    public List<EmployeeDto> addAllEmployees(@RequestBody List<Employees> employeesList){

        return empSer.addAllEmployees(employeesList);
    }

    @PutMapping("/update")
    public EmployeeDto update(@RequestBody Employees updatedData, HttpServletRequest request){
        String authHeader = request.getHeader("Authorization");
        String token = authHeader.substring(7);
        long id=jwtUtils.extractEmployeeId(token);
        return empSer.updateEmployee(id,updatedData);
    }

    @DeleteMapping("/del/{id}")
    public String deleteEmployee(@PathVariable long id){
        return empSer.deleteEmployee(id);
    }

    @PutMapping("/assignProject/{projId}")
    public EmployeeDto assignProject(HttpServletRequest request,@PathVariable long projId){
        String authHeader = request.getHeader("Authorization");
        String token = authHeader.substring(7);
        long empId=jwtUtils.extractEmployeeId(token);
        return empSer.assignProject(empId,projId);
    }
}
