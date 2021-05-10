package com.example.Ticketing.Services;

import com.example.Ticketing.Models.Priority;
import com.example.Ticketing.Models.Project;

public interface PriorityService {

    Priority createPriority(Project project);

    Priority findByProject (Project project);
}
