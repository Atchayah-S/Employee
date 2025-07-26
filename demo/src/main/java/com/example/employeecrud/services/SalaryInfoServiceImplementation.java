package com.example.employeecrud.services;

import com.example.employeecrud.dao.Employees;
import com.example.employeecrud.dao.SalaryInfo;
import com.example.employeecrud.dto.SalaryInfoDto;
import com.example.employeecrud.exceptions.ResourceNotFoundException;
import com.example.employeecrud.mapper.SalaryInfoMapper;
import com.example.employeecrud.repository.EmployeesRepo;
import com.example.employeecrud.repository.SalaryInfoRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SalaryInfoServiceImplementation implements SalaryInfoService{
    @Autowired
    private EmployeesRepo employeesRepo;
    @Autowired
    private SalaryInfoRepo salaryInfoRepo;
    @Override
    public SalaryInfoDto addSalaryInfo(Long empId, SalaryInfo salaryInfo) {
        Employees employee=employeesRepo.findById(empId).
                orElseThrow(()->new ResourceNotFoundException("No employee found with id: "+empId));
        salaryInfo.setEmployee(employee);
        employee.setSalaryInfo(salaryInfo);
        return SalaryInfoMapper.toSalaryInfoDto(salaryInfoRepo.save(salaryInfo));
    }

    @Override
    public SalaryInfoDto updateSalaryInfo(Long empId, SalaryInfo updatedSalaryInfo) {
        SalaryInfo existingData=employeesRepo.findById(empId).
                orElseThrow(()->new ResourceNotFoundException("No employee found with id: "+empId)).
                getSalaryInfo();
        existingData.setSalaryType(updatedSalaryInfo.getSalaryType());
        existingData.setAccountNumber(updatedSalaryInfo.getAccountNumber());
        existingData.setBankName(updatedSalaryInfo.getBankName());
        existingData.setBasePay(updatedSalaryInfo.getBasePay());
        return SalaryInfoMapper.toSalaryInfoDto(salaryInfoRepo.save(existingData));
    }

    @Override
    public SalaryInfoDto fetchSalaryInfo(Long empId) {
        Employees employee=employeesRepo.findById(empId).
                orElseThrow(()->new ResourceNotFoundException("No employee found with id: "+empId));
        if(employee.getSalaryInfo()==null){ throw new ResourceNotFoundException("Salary info not found");}
        long id=employee.getSalaryInfo().getSalaryInfoId();

        return SalaryInfoMapper.toSalaryInfoDto(salaryInfoRepo.findById(id).
                orElseThrow(()->new ResourceNotFoundException("No Salary Info found with id: "+id)));


    }

    @Override
    public String deleteSalaryInfo(Long salaryInfoId) {
        SalaryInfo salaryInfo=salaryInfoRepo.findById(salaryInfoId).
                orElseThrow(()->new ResourceNotFoundException("No Salary Info found with id: "+salaryInfoId));
        long empId=salaryInfo.getEmployee().getEmp_id();
        employeesRepo.findById(empId).get().setSalaryInfo(null);
        salaryInfoRepo.deleteById(salaryInfoId);
        return "Salary Info deleted successfully";
    }
}
