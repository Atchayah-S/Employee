package com.example.employeecrud.controllers;

import com.example.employeecrud.dao.Project;
import com.example.employeecrud.dto.GenericResponseEntity;
import com.example.employeecrud.services.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/project")
public class ProjectController {
    public final String PROJECT_ADDED_SUCCESSFULLY="Project Added successfully";

    public final String PROJECT_FETCHED_SUCCESSFULLY="Project fetched successfully";
    public final String PROJECTS_FETCHED_SUCCESSFULLY="Projects fetched successfully";
    public final String PROJECT_UPDATED_SUCCESSFULLY="Project updated successfully";
    @Autowired
    ProjectService projService;
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public GenericResponseEntity<Project>  createProject(@RequestBody Project project){

        return GenericResponseEntity.<Project>builder()
                .message(PROJECT_ADDED_SUCCESSFULLY)
                .data(projService.createProject(project))
                .statusCode(201)
                .status(HttpStatus.CREATED)
                .success(true)
                .build();

    }
    @GetMapping("/{id}")
    public GenericResponseEntity<Project>  fetchProject(@PathVariable long id){
        return GenericResponseEntity.<Project>builder()
                .message(PROJECT_FETCHED_SUCCESSFULLY)
                .data(projService.fetchProject(id))
                .statusCode(200)
                .status(HttpStatus.OK)
                .success(true)
                .build();
    }

    @GetMapping
    public GenericResponseEntity<List<Project>> fetchAllProject(){
        return GenericResponseEntity.<List<Project>>builder()
                .message(PROJECTS_FETCHED_SUCCESSFULLY)
                .data(projService.fetchAllProjects())
                .statusCode(200)
                .status(HttpStatus.OK)
                .success(true)
                .build();

    }
    @DeleteMapping("/{id}")
    public GenericResponseEntity<String> delProject(@PathVariable long id){
        return GenericResponseEntity.<String>builder()
                .message(projService.deleteProject(id))
                .data(null)
                .statusCode(200)
                .status(HttpStatus.OK)
                .success(true)
                .build();

    }

    @PutMapping("/{id}")
    public GenericResponseEntity<Project> updateProject(@PathVariable long id,@RequestBody Project existingProject){
        return GenericResponseEntity.<Project>builder()
                .message(PROJECT_UPDATED_SUCCESSFULLY)
                .data(projService.updateProject(id,existingProject))
                .statusCode(200)
                .status(HttpStatus.OK)
                .success(true)
                .build();

    }
}
