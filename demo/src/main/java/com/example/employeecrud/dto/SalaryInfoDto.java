package com.example.employeecrud.dto;

import lombok.Data;

@Data
public class SalaryInfoDto {
    private long salaryInfoId;
    private long accountNumber;
    private double basePay;
    private String bankName;
    private String salaryType;
    private long empId;
}
