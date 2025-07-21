package com.example.employeecrud.dao;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class SalaryInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long salaryInfoId;
    private long accountNumber;
    private double basePay;
    private String bankName;
    private String salaryType;
    @OneToOne
    @JoinColumn(name = "empId",referencedColumnName = "emp_id")
   @JsonBackReference
    private Employees employee;
}
