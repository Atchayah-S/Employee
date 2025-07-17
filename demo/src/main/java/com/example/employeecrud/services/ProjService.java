package com.example.employeecrud.services;

import com.example.employeecrud.dao.Project;

import java.util.List;

public interface ProjService {
    public Project createProject(Project project);
    public Project updateProject(Long projId,Project updatedProject);
    public String deleteProject(Long projId);
    public Project fetchProject(Long projId);
    public List<Project> fetchAllProjects();
}
