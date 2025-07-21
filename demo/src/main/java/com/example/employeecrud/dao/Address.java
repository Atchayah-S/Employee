package com.example.employeecrud.dao;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String doorNo;
    private String streetName;
    private String city;
    private int zipCode;
    @ManyToOne
    @JoinColumn(name = "empId", referencedColumnName = "emp_id")
    private Employees employee;
}