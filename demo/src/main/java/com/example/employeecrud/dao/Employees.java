package com.example.employeecrud.dao;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Getter @Setter
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

    @OneToOne(mappedBy = "employee",cascade = CascadeType.ALL)
    @JsonManagedReference
    private EmployeeProfile employeeProfile;
    @ManyToMany
    @JoinTable(name = "Employee-Project", joinColumns = @JoinColumn(name = "emp_id"),inverseJoinColumns = @JoinColumn(name = "projId"))
    @JsonIgnore
    private List<Project> projects;
    @OneToMany(mappedBy = "employee",cascade = CascadeType.ALL)
    private List<Address>   addressList;
}
