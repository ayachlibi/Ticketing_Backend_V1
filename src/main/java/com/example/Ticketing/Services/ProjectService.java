package com.example.Ticketing.Services;

import com.example.Ticketing.Models.Project;

import java.util.List;
import java.util.Optional;

public interface ProjectService {

    Project createProject( Project project);

    Project updateProject(Project project);

    Optional<Project> findById (Long id);

    List<Project> findAll();

    void deleteProject(Long id);


}
