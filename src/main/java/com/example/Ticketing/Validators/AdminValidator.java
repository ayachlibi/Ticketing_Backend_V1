package com.example.Ticketing.Validators;

import com.example.Ticketing.Models.Admin;
import com.example.Ticketing.Models.CSE;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class AdminValidator {
    public static List<String> validator(Admin admin) {
        List<String> errors = new ArrayList<>();
        if (admin == null || !StringUtils.hasLength(admin.getName())) {
            errors.add("Please fill the Name Field");
        }
        if (admin == null || !StringUtils.hasLength(admin.getFamilyname())) {
            errors.add("Please fill the Family name Field");
        }
        if (admin == null || !StringUtils.hasLength(admin.getEmail())) {
            errors.add("Please fill the Email Field");
        }
        if (admin == null || !StringUtils.hasLength(admin.getUsername())) {
            errors.add("Please fill the Username Field");
        }
        if (admin == null || !StringUtils.hasLength(admin.getPassword())) {
            errors.add("Please fill the Password Field");
        }
        return errors;
    }
}
