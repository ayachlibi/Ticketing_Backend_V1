package com.example.Ticketing.Services;

import com.example.Ticketing.Models.User;
import com.example.Ticketing.RequestModel.LoginRequest;
import org.springframework.http.ResponseEntity;

public interface UserService {

    ResponseEntity<?> authenticateUser(LoginRequest loginRequest);

}
