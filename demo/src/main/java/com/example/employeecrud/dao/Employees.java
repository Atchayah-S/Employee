package com.example.employeecrud.dao;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;

@Entity
@Data
public class Employees {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long emp_id;
    private String name;
    private String email;
    private String phone;
    private String password;
    @ManyToOne
    @JoinColumn(name = "deptID",referencedColumnName = "deptID")
    @JsonBackReference
    private Department department;
}
