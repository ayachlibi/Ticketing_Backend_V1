package com.example.Ticketing.Validators;


import com.example.Ticketing.RequestModel.CSERequestModel;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class CSEValidator {
    public static List<String> validator(CSERequestModel cse) {
        List<String> errors = new ArrayList<>();
        if (cse == null || !StringUtils.hasLength(cse.getName())) {
            errors.add("Please fill the Name Field");
        }
        if (cse == null || !StringUtils.hasLength(cse.getFamilyname())) {
            errors.add("Please fill the Family name Field");
        }
        if (cse == null || !StringUtils.hasLength(cse.getEmail())) {
            errors.add("Please fill the Email Field");
        }
        if (cse == null || !StringUtils.hasLength(cse.getPassword())) {
            errors.add("Please fill the Password Field");
        }
        if (cse == null || !StringUtils.hasLength(cse.getPhone_number())) {
            errors.add("Please fill the phone number Field");
        }

        return errors;
    }
}
