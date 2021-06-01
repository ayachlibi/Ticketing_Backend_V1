package com.example.Ticketing.Controllers;

import com.example.Ticketing.Controllers.APIs.AuthAPI;
import com.example.Ticketing.Models.User;
import com.example.Ticketing.RequestModel.LoginRequest;
import com.example.Ticketing.Services.ServiceImp.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController implements AuthAPI {

    @Autowired
    UserService userService;

    @Override
    public User authenticateUser(LoginRequest loginRequest) {
        return null;
    }


}
