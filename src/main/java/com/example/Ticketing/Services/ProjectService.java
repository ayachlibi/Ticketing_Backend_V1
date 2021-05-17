package com.example.Ticketing.Services;

import com.example.Ticketing.Models.Project;

import java.util.List;
import java.util.Optional;

public interface ProjectService {

    Project create( Project project);

    Project update(Project project);

    Optional<Project> findById (Long id);

    List<Project> findAll();

    void delete(Long id);


}
