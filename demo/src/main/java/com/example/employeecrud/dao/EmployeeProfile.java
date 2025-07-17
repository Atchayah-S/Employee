package com.example.employeecrud.dao;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter @Setter
public class EmployeeProfile {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long profileId;
    private String joinDate;
    private String workLocationType;
    private String role;
    @OneToOne
    @JoinColumn(name = "empId",referencedColumnName = "emp_id")
    @JsonBackReference
    private Employees employee;
}
