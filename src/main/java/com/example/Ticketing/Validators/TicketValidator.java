package com.example.Ticketing.Validators;

import com.example.Ticketing.Models.Ticket;
import com.example.Ticketing.Models.User;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class TicketValidator {
    public static List<String> validator(Ticket ticket){
        List <String> errors= new ArrayList<>();
        if(ticket == null ||!StringUtils.hasLength(ticket.getTitle())){
            errors.add("Please fill the Title Field");
        }
        if(ticket == null ||!StringUtils.hasLength(ticket.getDescription())){
            errors.add("Please fill Description Field");
        }
        if(ticket == null ||!StringUtils.hasLength(ticket.getCategory())){
            errors.add("Please fill the Category Field");
        }
        if(ticket == null ||!StringUtils.hasLength(ticket.getPriority())){
            errors.add("Please fill the Priority Field");
        }
        if(ticket == null ||!StringUtils.hasLength(ticket.getType())){
            errors.add("Please fill the Type Field");
        }
        if(ticket == null ||ticket.getClient()==null){
            errors.add("Please add the Owner ");
        }
        if(ticket == null ||ticket.getCse()==null){
            errors.add("Please add the CSE ");
        }

        return errors;
    }
}
