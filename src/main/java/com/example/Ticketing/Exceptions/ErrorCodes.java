package com.example.Ticketing.Exceptions;

public enum ErrorCodes {
    
    USER_NOT_FOUND(1000),
    USER_NOT_VALID(1001),
    CLIENT_NOT_FOUND(2000),
    CLIENT_NOT_VALID(2001),
    //TODO complete the rest of the code
    CSE_NOT_VALID(4001),
    CSE_NOT_FOUND(4000),
    ADMIN_NOT_VALID(5001),
    ADMIN_NOT_FOUND(5000),
    SUPER_ADMIN_NOT_VALID(6001),
    SUPER_ADMIN_NOT_FOUND(6000),
    TICKET_NOT_FOUND(7000),
    TICKET_NOT_VALID(7001),
    MESSAGE_NOT_VALID(8001),
    MESSAGE_NOT_FOUND(8000),

    ;

    private int Code;

    ErrorCodes(int Code) {
        this.Code= Code;
    }

    public int getCode(){
        return Code;
    }
}
