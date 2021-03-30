package com.example.Ticketing.Validators;

import com.example.Ticketing.Models.Client;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class ClientValidator {
    public static List<String> validator(Client client){
        List <String> errors= new ArrayList<>();
        if(client == null ||!StringUtils.hasLength(client.getName())){
            errors.add("Please fill the Name Field");
        }
        if(client == null ||!StringUtils.hasLength(client.getFamilyname())){
            errors.add("Please fill the Family name Field");
        }
        if(client == null ||!StringUtils.hasLength(client.getEmail())){
            errors.add("Please fill the Email Field");
        }
        if(client == null ||!StringUtils.hasLength(client.getUsername())){
            errors.add("Please fill the Username Field");
        }
        if(client == null ||!StringUtils.hasLength(client.getPassword())){
            errors.add("Please fill the Password Field");
        }
        if(client == null ||!StringUtils.hasLength(client.getEnterprise())){
            errors.add("Please fill the Enterprise Field");
        }


        return errors;
    }
}
