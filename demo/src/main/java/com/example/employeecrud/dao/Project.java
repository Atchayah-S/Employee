package com.example.employeecrud.dao;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Entity
@Data
public class Project {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long projId;
    private String title;
    private LocalDate startDate;
    private String duration;
    @ManyToMany(mappedBy = "projects")
    private List<Employees> employeesList;
}
