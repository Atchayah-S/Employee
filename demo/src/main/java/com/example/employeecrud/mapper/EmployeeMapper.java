package com.example.employeecrud.mapper;

import com.example.employeecrud.dao.Employees;
import com.example.employeecrud.dto.EmployeeDto;

import java.util.List;

public class EmployeeMapper {
    public static EmployeeDto EmployeesToEmployeeDto(Employees employee){
        EmployeeDto empdto=new EmployeeDto();
        empdto.setId(employee.getEmp_id());
        empdto.setName(employee.getName());
        empdto.setPhone(employee.getPhone());
        empdto.setEmail(employee.getEmail());
        if(employee.getDepartment()!=null){
        empdto.setDepartment(DepartmentMapper.toDepartmentDto(employee.getDepartment()));}
        if(employee.getEmployeeProfile()!=null){
       empdto.setProfile(ProfileMapper.toProfileDto(employee.getEmployeeProfile()));}
        if(!employee.getAddressList().isEmpty()){
            empdto.setAddress(AddressMapper.toAddressDtoList(employee.getAddressList()));
        }
       if(employee.getProjects()!=null){
       empdto.setProject(ProjectMapper.toProjectDtoList(employee.getProjects()));}
       if(employee.getSalaryInfo()!=null){
           empdto.setSalaryInfoDto(SalaryInfoMapper.toSalaryInfoDto(employee.getSalaryInfo()));
       }
        return empdto;
    }
    public static List<EmployeeDto> EmployeesToEmployeeDtoList(List<Employees> employeesList) {
        return employeesList.stream()
                .map(EmployeeMapper::EmployeesToEmployeeDto)
                .toList();
    }
}
