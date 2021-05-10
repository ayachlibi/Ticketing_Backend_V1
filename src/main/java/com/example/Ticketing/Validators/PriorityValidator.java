package com.example.Ticketing.Validators;

import com.example.Ticketing.Models.Project;

import java.util.ArrayList;
import java.util.List;

public class PriorityValidator {
    public static List<String> validator(Project project) {

        List<String> errors = new ArrayList<>();

        if(project.getPriorities().getSla_low() == 0){
            errors.add("Please fill the SLA of a low Priority");
        }

        if(project.getPriorities().getSla_high() == 0){
            errors.add("Please fill the SLA of a high Priority");
        }

        if(project.getPriorities().getSla_very_high() == 0){
            errors.add("Please fill the SLA of a very high Priority");
        }

        if(project.getPriorities().getSla_medium() == 0){
            errors.add("Please fill the SLA of a medium Priority");
        }

        return errors;
    }
}