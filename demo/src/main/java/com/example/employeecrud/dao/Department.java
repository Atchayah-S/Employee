package com.example.employeecrud.dao;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
public class Department {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long deptID;
    private String deptName;
    @OneToMany(mappedBy = "department",cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<Employees> employees;
}
