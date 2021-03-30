package com.example.Ticketing.Validators;

import com.example.Ticketing.Models.Admin;
import com.example.Ticketing.Models.SuperAdmin;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class SuperAdminValidator {
    public static List<String> validator(SuperAdmin superAdmin) {
        List<String> errors = new ArrayList<>();
        if (superAdmin == null || !StringUtils.hasLength(superAdmin.getName())) {
            errors.add("Please fill the Name Field");
        }
        if (superAdmin == null || !StringUtils.hasLength(superAdmin.getFamilyname())) {
            errors.add("Please fill the Family name Field");
        }
        if (superAdmin == null || !StringUtils.hasLength(superAdmin.getEmail())) {
            errors.add("Please fill the Email Field");
        }
        if (superAdmin == null || !StringUtils.hasLength(superAdmin.getUsername())) {
            errors.add("Please fill the Username Field");
        }
        if (superAdmin == null || !StringUtils.hasLength(superAdmin.getPassword())) {
            errors.add("Please fill the Password Field");
        }
        return errors;
    }
}