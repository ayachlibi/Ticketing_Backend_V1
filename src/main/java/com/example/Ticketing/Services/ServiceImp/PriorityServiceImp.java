package com.example.Ticketing.Services.ServiceImp;

import com.example.Ticketing.Config.SequenceGeneratorService;
import com.example.Ticketing.Exceptions.InvalidEntityException;
import com.example.Ticketing.Models.Priority;
import com.example.Ticketing.Models.Project;
import com.example.Ticketing.Repository.PriorityRepository;
import com.example.Ticketing.Repository.ProjectRepository;
import com.example.Ticketing.Services.PriorityService;
import com.example.Ticketing.Validators.PriorityValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service

public class PriorityServiceImp implements PriorityService {

    @Autowired
    PriorityRepository priorityRepository;

    @Autowired
    SequenceGeneratorService service;

    @Override
    public Priority createPriority(Project project) {
        if (!PriorityValidator.validator(project).isEmpty()){
            throw new InvalidEntityException("Please fill the Priorities");
        }
        Priority priority= new Priority();
        priority.setSla_high(project.getPriorities().getSla_high());
        priority.setSla_very_high(project.getPriorities().getSla_very_high());
        priority.setSla_medium(project.getPriorities().getSla_medium());
        priority.setSla_low(project.getPriorities().getSla_low());

        return priorityRepository.save(priority);

    }


    @Override
    public Priority findByProject(Project project) {
        return project.getPriorities();
    }
}
