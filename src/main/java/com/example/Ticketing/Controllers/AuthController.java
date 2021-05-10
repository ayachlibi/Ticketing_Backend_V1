package com.example.Ticketing.Controllers;

import com.example.Ticketing.Controllers.APIs.AuthAPI;
import com.example.Ticketing.RequestModel.LoginRequest;
import com.example.Ticketing.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController implements AuthAPI {

    @Autowired
    UserService userService;

    @Override
    public ResponseEntity<?> authenticateUser(LoginRequest loginRequest) {
        return userService.authenticateUser(loginRequest);
    }


}
