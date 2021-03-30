package com.example.Ticketing.Handlers;

import com.example.Ticketing.Exceptions.ErrorCodes;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter

@Setter

@NoArgsConstructor

@AllArgsConstructor

@Builder

public class ErrorDTO {

    private Integer httpCode;

    private ErrorCodes errorCode;

    private String message;

    private List<String> errors= new ArrayList<>();

}
