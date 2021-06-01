package com.example.Ticketing.Services.ServiceImp;

import com.example.Ticketing.Config.SequenceGeneratorService;
import com.example.Ticketing.Exceptions.EntityNotFoundException;
import com.example.Ticketing.Exceptions.ErrorCodes;
import com.example.Ticketing.Models.Project;
import com.example.Ticketing.Repository.ProjectRepository;
import com.example.Ticketing.Services.PriorityService;
import com.example.Ticketing.Services.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service

public class ProjectServiceImp implements ProjectService {

    @Autowired
    ProjectRepository projectRepository;

    @Autowired
    SequenceGeneratorService service;

    @Autowired
    PriorityService priorityService;

    @Override
    public Project create(Project project) {

        if(projectRepository.existsByName(project.getName())){
            throw new IllegalStateException("This name already exists");
        }

        priorityService.createPriority(project);

        project.setCostumeid(service.generateSequence(project.SEQUENCE_NAME));

        return projectRepository.save(project);

    }

    @Override
    public Project update(Project project) {

        if(!projectRepository.existsById(project.getMongoid())){
            throw new EntityNotFoundException("The project you are trying to update does not exist",
                    ErrorCodes.PROJECT_NOT_FOUND);
        }

        if(projectRepository.existsByName(project.getName())){
            throw new IllegalStateException("This name already exists");
        }

        return projectRepository.save(project);
    }

    @Override
    public Optional<Project> findById(Long id) {
       Optional< Project > project = projectRepository.findByCostumeid(id);
        return Optional.of(project).orElseThrow(()-> new EntityNotFoundException
                ("No Project Found with the ID"+ id,ErrorCodes.PROJECT_NOT_FOUND));

    }

    @Override
    public List<Project> findAll() {
        return projectRepository.findAll();
    }

    @Override
    public void delete(Long id) {

        if (!projectRepository.existsByCostumeid(id)){
            throw new EntityNotFoundException("This Project does not exist", ErrorCodes.PROJECT_NOT_FOUND);
        }
        projectRepository.deleteByCostumeid(id);
    }
}
