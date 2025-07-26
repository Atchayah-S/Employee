package com.example.employeecrud.controllers;

import com.example.employeecrud.Security.JwtUtils;
import com.example.employeecrud.dao.Employees;
import com.example.employeecrud.dto.EmployeeDto;
import com.example.employeecrud.dto.GenericResponseEntity;
import com.example.employeecrud.repository.EmployeesRepo;
import com.example.employeecrud.services.EmpServiceImplementation;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
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
    public GenericResponseEntity<EmployeeDto> createEmployee(@RequestBody Employees employee){
    EmployeeDto info=empSer.CreateEmployee(employee);

        return GenericResponseEntity.<EmployeeDto>builder()
                .message("Employee Added successfully")
                .data(info)
                .success(true)
                .build();
    }

    @GetMapping("/fetchall")
    public GenericResponseEntity<List<EmployeeDto>> getAllEmployees(){

        return  GenericResponseEntity.<List<EmployeeDto>>builder()
                .message("All Employees Fetched Successfully")
                .data(empSer.fetchAllEmployee())
                .success(true)
                .build();
    }

    @GetMapping("/fetchEmpById")
    public GenericResponseEntity<EmployeeDto> getEmployeeById(HttpServletRequest request){
        String authHeader = request.getHeader("Authorization");
        String token = authHeader.substring(7);
        long id=jwtUtils.extractEmployeeId(token);
        return GenericResponseEntity.<EmployeeDto>builder()
                .message("Employee fetched successfully")
                .data(empSer.FetchById(id))
                .success(true)
                .build();

    }

    @PostMapping("/addAll")
    public GenericResponseEntity<List<EmployeeDto>> addAllEmployees(@RequestBody List<Employees> employeesList){
        List<EmployeeDto> employeeDtoList=empSer.addAllEmployees(employeesList);
        return GenericResponseEntity.<List<EmployeeDto>>builder()
                .message("All Employees added Successfully")
                .data(employeeDtoList)
                .success(true)
                .build();
    }

    @PutMapping("/update")
    public GenericResponseEntity<EmployeeDto> update(@RequestBody Employees updatedData, HttpServletRequest request){
        String authHeader = request.getHeader("Authorization");
        String token = authHeader.substring(7);
        long id=jwtUtils.extractEmployeeId(token);
        return GenericResponseEntity.<EmployeeDto>builder()
                .message("Employee information updated Successfully")
                .data(empSer.updateEmployee(id,updatedData))
                .success(true)
                .build();

    }

    @DeleteMapping("/del/{id}")
    public GenericResponseEntity<String> deleteEmployee(@PathVariable long id){
        return GenericResponseEntity.<String>builder()
                .message(empSer.deleteEmployee(id))
                .data(null)
                .success(true)
                .build();
    }

    @PutMapping("/assignProject/{projId}")
    public GenericResponseEntity<EmployeeDto> assignProject(HttpServletRequest request, @PathVariable long projId){
        String authHeader = request.getHeader("Authorization");
        String token = authHeader.substring(7);
        long empId=jwtUtils.extractEmployeeId(token);

        return GenericResponseEntity.<EmployeeDto>builder()
                .message("Project Assigned Successfully")
                .data(empSer.assignProject(empId,projId))
                .success(true)
                .build();
    }
}
