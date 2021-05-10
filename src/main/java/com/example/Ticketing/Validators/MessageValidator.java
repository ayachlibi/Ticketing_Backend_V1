package com.example.Ticketing.Validators;

import com.example.Ticketing.Models.Message;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class MessageValidator {
    public static List<String> validator (Message message){

        List<String> errors= new ArrayList<>();

        if (message == null || !StringUtils.hasLength(message.getDescription())) {
                errors.add("Please fill the Description Field");
            }

        return errors;
    }
}
