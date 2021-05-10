package com.example.Ticketing.Validators;

import com.example.Ticketing.Models.Message;
import com.example.Ticketing.Models.Project;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class ProjectValidator {
    public static List<String> validator (Project project){

        List<String> errors= new ArrayList<>();

        if (project == null || !StringUtils.hasLength(project.getName())) {
            errors.add("Please fill the Name Field");
        }

        if (project == null || project.getClient()== null ) {
            errors.add("Please add a Client to this Project");
        }

        if (project == null || project.getCse()== null ) {
            errors.add("Please add a CSE to this Project");
        }

        List<String> error= PriorityValidator.validator(project);

        errors.addAll(error);

        return errors;
    }
}
