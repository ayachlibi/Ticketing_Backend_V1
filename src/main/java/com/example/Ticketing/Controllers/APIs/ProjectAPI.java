package com.example.Ticketing.Controllers.APIs;

import com.example.Ticketing.Models.Operations_Manager;
import com.example.Ticketing.Models.Project;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

public interface ProjectAPI {
    @PostMapping(value = "/Project/AddProject", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    Project saveProject(@RequestBody Project project);

    @DeleteMapping(value = "/Project/Delete/{idProject}", produces = MediaType.APPLICATION_JSON_VALUE)
    void deleteProject(@PathVariable("idProject") Long id);

    @PutMapping(value = "/Project/Update", produces = MediaType.APPLICATION_JSON_VALUE)
    Project updateProject(@RequestBody Project project);

    @GetMapping(value = "/Project/{idProject}")
    Optional<Project> findProjectById(@PathVariable("idProject") Long id);

    @GetMapping(value = "/Project/all")
    List<Project> findAllProjects();
}
