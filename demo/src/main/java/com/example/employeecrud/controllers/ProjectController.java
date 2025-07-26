package com.example.employeecrud.controllers;

import com.example.employeecrud.dao.Project;
import com.example.employeecrud.dto.EmployeeDto;
import com.example.employeecrud.dto.GenericResponseEntity;
import com.example.employeecrud.dto.ProfileDto;
import com.example.employeecrud.services.ProjService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ProjectController {

    @Autowired
    ProjService projService;
    @PostMapping("/createProject")
    public GenericResponseEntity<Project>  createProject(@RequestBody Project project){

        return GenericResponseEntity.<Project>builder()
                .message("Project created successfully")
                .data(projService.createProject(project))
                .success(true)
                .build();

    }

    @GetMapping("/fetchProject/{id}")
    public GenericResponseEntity<Project>  fetchProject(@PathVariable long id){
        return GenericResponseEntity.<Project>builder()
                .message("Project fetched successfully")
                .data(projService.fetchProject(id))
                .success(true)
                .build();
    }

    @GetMapping("/fetchAllProject")
    public GenericResponseEntity<List<Project>> fetchAllProject(){
        return GenericResponseEntity.<List<Project>>builder()
                .message("All Project fetched successfully")
                .data(projService.fetchAllProjects())
                .success(true)
                .build();

    }

    @DeleteMapping("/deleteProject/{id}")
    public GenericResponseEntity<String> delProject(@PathVariable long id){
        return GenericResponseEntity.<String>builder()
                .message(projService.deleteProject(id))
                .data(null)
                .success(true)
                .build();

    }

    @PutMapping("/updateProject/{id}")
    public GenericResponseEntity<Project> updateProject(@PathVariable long id,@RequestBody Project existingProject){
        return GenericResponseEntity.<Project>builder()
                .message("All Employees Profile fetched successfully")
                .data(projService.updateProject(id,existingProject))
                .success(true)
                .build();

    }
}
