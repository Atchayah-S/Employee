package com.example.employeecrud.dto;

import lombok.Data;

import java.time.LocalDate;
@Data
public class ProjectDto {
    private long projId;
    private String title;
    private LocalDate startDate;
    private String duration;
}
