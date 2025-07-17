package com.example.employeecrud.services;

import com.example.employeecrud.dao.Project;
import com.example.employeecrud.exceptions.ResourceNotFoundException;
import com.example.employeecrud.repository.ProjectRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProjServiceImplementation implements ProjService{
    @Autowired
    private ProjectRepo projectRepo;

    @Override
    public Project createProject(Project project) {
        return projectRepo.save(project);
    }

    @Override
    public Project updateProject(Long projId,Project updatedProject) {
        Project existingProject=projectRepo.findById(projId).
                orElseThrow(()-> new ResourceNotFoundException("Project "+projId+" not found"));
        existingProject.setTitle(updatedProject.getTitle());
        existingProject.setDuration(updatedProject.getDuration());
        existingProject.setStartDate(updatedProject.getStartDate());
        return projectRepo.save(existingProject);
    }

    @Override
    public String deleteProject(Long projId) {
        if(!projectRepo.existsByProjId(projId))
            throw new ResourceNotFoundException("Project "+projId+" not found");
        projectRepo.deleteById(projId);
        return "Deleted Successfully";
    }

    @Override
    public Project fetchProject(Long projId) {
        return projectRepo.findById(projId).orElseThrow(()-> new ResourceNotFoundException("Project "+projId+" not found"));
    }

    @Override
    public List<Project> fetchAllProjects() {
        return projectRepo.findAll();
    }
}
