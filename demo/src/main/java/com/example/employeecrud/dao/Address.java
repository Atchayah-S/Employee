package com.example.employeecrud.dao;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
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
    @JsonIgnore
    private Employees employee;
}