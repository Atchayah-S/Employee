package com.example.employeecrud.controllers;

import com.example.employeecrud.Security.JwtUtils;
import com.example.employeecrud.dao.Employees;
import com.example.employeecrud.dto.EmployeeDto;
import com.example.employeecrud.dto.GenericResponseEntity;
import com.example.employeecrud.repository.EmployeesRepo;
import com.example.employeecrud.services.EmpServiceImplementation;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public GenericResponseEntity<EmployeeDto> createEmployee(@RequestBody Employees employee){
    EmployeeDto info=empSer.CreateEmployee(employee);

        return GenericResponseEntity.<EmployeeDto>builder()
                .message("Employee Added successfully")
                .data(info)
                .statusCode(201)
                .status(HttpStatus.CREATED)
                .success(true)
                .build();
    }

    @GetMapping
    public GenericResponseEntity<List<EmployeeDto>> getAllEmployees(){

        return  GenericResponseEntity.<List<EmployeeDto>>builder()
                .message("All Employees Fetched Successfully")
                .data(empSer.fetchAllEmployee())
                .statusCode(200)
                .status(HttpStatus.OK)
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
                .statusCode(200)
                .status(HttpStatus.OK)
                .success(true)
                .build();

    }

    @PostMapping("/addAll")
    @ResponseStatus(HttpStatus.CREATED)
    public GenericResponseEntity<List<EmployeeDto>> addAllEmployees(@RequestBody List<Employees> employeesList){
        List<EmployeeDto> employeeDtoList=empSer.addAllEmployees(employeesList);
        return GenericResponseEntity.<List<EmployeeDto>>builder()
                .message("All Employees added Successfully")
                .data(employeeDtoList)
                .statusCode(201)
                .status(HttpStatus.CREATED)
                .success(true)
                .build();
    }

    @PutMapping
    public GenericResponseEntity<EmployeeDto> update(@RequestBody Employees updatedData, HttpServletRequest request){
        String authHeader = request.getHeader("Authorization");
        String token = authHeader.substring(7);
        long id=jwtUtils.extractEmployeeId(token);
        return GenericResponseEntity.<EmployeeDto>builder()
                .message("Employee information updated Successfully")
                .data(empSer.updateEmployee(id,updatedData))
                .statusCode(200)
                .status(HttpStatus.OK)
                .success(true)
                .build();

    }

    @DeleteMapping("/{id}")
    public GenericResponseEntity<String> deleteEmployee(@PathVariable long id){
        return GenericResponseEntity.<String>builder()
                .message(empSer.deleteEmployee(id))
                .data(null)
                .statusCode(200)
                .status(HttpStatus.OK)
                .success(true)
                .build();
    }

    @PutMapping("/assignProject/{projId}")
    @ResponseStatus(HttpStatus.CREATED)
    public GenericResponseEntity<EmployeeDto> assignProject(HttpServletRequest request, @PathVariable long projId){
        String authHeader = request.getHeader("Authorization");
        String token = authHeader.substring(7);
        long empId=jwtUtils.extractEmployeeId(token);

        return GenericResponseEntity.<EmployeeDto>builder()
                .message("Project Assigned Successfully")
                .data(empSer.assignProject(empId,projId))
                .status(HttpStatus.CREATED)
                .statusCode(201)
                .success(true)
                .build();
    }
}
