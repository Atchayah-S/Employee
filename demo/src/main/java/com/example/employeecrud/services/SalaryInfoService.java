package com.example.employeecrud.services;

import com.example.employeecrud.dao.SalaryInfo;
import com.example.employeecrud.dto.SalaryInfoDto;

public interface SalaryInfoService {
    public SalaryInfoDto addSalaryInfo(Long empId,SalaryInfo salaryInfo);
    public SalaryInfoDto updateSalaryInfo(Long empId,SalaryInfo updatedSalaryInfo);
    public SalaryInfoDto fetchSalaryInfo(Long empId);
    public String deleteSalaryInfo(Long salaryInfoId);
}
