package com.example.Ticketing.Validators;

import com.example.Ticketing.Models.User;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class UserValidator {
    public static List<String> validator(User user){
        List <String> errors= new ArrayList<>();
        if(user == null ||!StringUtils.hasLength(user.getFirstname())){
            errors.add("Please fill the Name Field");
        }
        if(user == null ||!StringUtils.hasLength(user.getLastname())){
            errors.add("Please fill the Family name Field");
        }
        if(user == null ||!StringUtils.hasLength(user.getEmail())){
            errors.add("Please fill the Email Field");
        }
        if(user == null ||!StringUtils.hasLength(user.getUsername())){
            errors.add("Please fill the Username Field");
        }
        if(user == null ||!StringUtils.hasLength(user.getPassword())){
            errors.add("Please fill the Password Field");
        }
        return errors;
    }
}
