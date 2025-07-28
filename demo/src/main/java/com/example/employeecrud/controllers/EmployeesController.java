package com.example.employeecrud.controllers;

import com.example.employeecrud.Security.JwtUtils;
import com.example.employeecrud.dao.Employees;
import com.example.employeecrud.dto.EmployeeDto;
import com.example.employeecrud.dto.GenericResponseEntity;
import com.example.employeecrud.repository.EmployeesRepo;
import com.example.employeecrud.services.ServiceImpl.EmployeeServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/Employee")
public class EmployeesController {
    private final String EMPLOYEE_ADDED_SUCCESSFULLY="Employee Added successfully";
    private final String EMPLOYEES_ADDED_SUCCESSFULLY="Employees Added successfully";
    private final String EMPLOYEE_FETCHED_SUCCESSFULLY="Employee fetched successfully";
    private final String EMPLOYEES_FETCHED_SUCCESSFULLY="Employee fetched successfully";
    private final String EMPLOYEE_UPDATED_SUCCESSFULLY="Employee updated successfully";
    private final String PROJECT_ASSIGNED_SUCCESSFULLY="Project assigned successfully";
    @Autowired
    private EmployeesRepo emprepo;
    @Autowired
    private EmployeeServiceImpl empSer;
    @Autowired
    private JwtUtils jwtUtils;
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public GenericResponseEntity<EmployeeDto> createEmployee(@RequestBody Employees employee){
    EmployeeDto info=empSer.CreateEmployee(employee);

        return GenericResponseEntity.<EmployeeDto>builder()
                .message(EMPLOYEE_ADDED_SUCCESSFULLY)
                .data(info)
                .statusCode(201)
                .status(HttpStatus.CREATED)
                .success(true)
                .build();
    }

    @GetMapping
    public GenericResponseEntity<List<EmployeeDto>> getAllEmployees(){

        return  GenericResponseEntity.<List<EmployeeDto>>builder()
                .message(EMPLOYEES_FETCHED_SUCCESSFULLY)
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
                .message(EMPLOYEE_FETCHED_SUCCESSFULLY)
                .data(empSer.FetchById(id))
                .statusCode(200)
                .status(HttpStatus.OK)
                .success(true)
                .build();

    }
    @PostMapping("/all")
    @ResponseStatus(HttpStatus.CREATED)
    public GenericResponseEntity<List<EmployeeDto>> addAllEmployees(@RequestBody List<Employees> employeesList){
        List<EmployeeDto> employeeDtoList=empSer.addAllEmployees(employeesList);
        return GenericResponseEntity.<List<EmployeeDto>>builder()
                .message(EMPLOYEES_ADDED_SUCCESSFULLY)
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
                .message(EMPLOYEE_UPDATED_SUCCESSFULLY)
                .data(empSer.updateEmployee(id,updatedData))
                .statusCode(200)
                .status(HttpStatus.OK)
                .success(true)
                .build();

    }

    @DeleteMapping
    public GenericResponseEntity<String> deleteEmployee(HttpServletRequest request){
        String authHeader = request.getHeader("Authorization");
        String token = authHeader.substring(7);
        long id=jwtUtils.extractEmployeeId(token);
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
                .message(PROJECT_ASSIGNED_SUCCESSFULLY)
                .data(empSer.assignProject(empId,projId))
                .status(HttpStatus.CREATED)
                .statusCode(201)
                .success(true)
                .build();
    }
}
