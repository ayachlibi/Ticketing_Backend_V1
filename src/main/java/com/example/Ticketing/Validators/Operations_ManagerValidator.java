package com.example.Ticketing.Validators;

import com.example.Ticketing.Models.Operations_Manager;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class AdminValidator {
    public static List<String> validator(Operations_Manager operationsManager) {
        List<String> errors = new ArrayList<>();
        if (operationsManager == null || !StringUtils.hasLength(operationsManager.getName())) {
            errors.add("Please fill the Name Field");
        }
        if (operationsManager == null || !StringUtils.hasLength(operationsManager.getFamilyname())) {
            errors.add("Please fill the Family name Field");
        }
        if (operationsManager == null || !StringUtils.hasLength(operationsManager.getEmail())) {
            errors.add("Please fill the Email Field");
        }
        if (operationsManager == null || !StringUtils.hasLength(operationsManager.getUsername())) {
            errors.add("Please fill the Username Field");
        }
        if (operationsManager == null || !StringUtils.hasLength(operationsManager.getPassword())) {
            errors.add("Please fill the Password Field");
        }
        return errors;
    }
}
