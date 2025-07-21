package com.example.employeecrud.mapper;

import com.example.employeecrud.dao.SalaryInfo;
import com.example.employeecrud.dto.SalaryInfoDto;

public class SalaryInfoMapper {
    public static SalaryInfoDto toSalaryInfoDto(SalaryInfo salaryInfo){
        SalaryInfoDto salaryInfoDto=new SalaryInfoDto();
        salaryInfoDto.setSalaryInfoId(salaryInfo.getSalaryInfoId());
        salaryInfoDto.setEmpId(salaryInfo.getEmployee().getEmp_id());
        salaryInfoDto.setAccountNumber(salaryInfo.getAccountNumber());
        salaryInfoDto.setBankName(salaryInfo.getBankName());
        salaryInfoDto.setBasePay(salaryInfo.getBasePay());
        salaryInfoDto.setSalaryType(salaryInfo.getSalaryType());
        return salaryInfoDto;
    }
}
