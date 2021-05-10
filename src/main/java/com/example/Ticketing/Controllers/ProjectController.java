package com.example.Ticketing.Controllers;

import com.example.Ticketing.Controllers.APIs.ProjectAPI;
import com.example.Ticketing.Models.Project;
import com.example.Ticketing.Services.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController

public class ProjectController implements ProjectAPI {
    private ProjectService projectService;

    //Getter Injection
    @Autowired
    public ProjectService getProjectService() {
        return projectService;
    }

    @Autowired
    public ProjectController(ProjectService projectService) {
        this.projectService= projectService;
    }


    @Override
    public Project saveProject(Project project) {
        return projectService.createProject(project);
    }

    @Override
    public void deleteProject(Long id) {
        projectService.deleteProject(id);
    }

    @Override
    public Project updateProject(Project project) {
        return projectService.updateProject(project);
    }

    @Override
    public Optional<Project> findProjectById(Long id) {
        return projectService.findById(id);
    }

    @Override
    public List<Project> findAllProjects() {
        return projectService.findAll();
    }
}
