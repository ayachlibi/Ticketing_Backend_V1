package com.example.Ticketing.Controllers.APIs;

import com.example.Ticketing.RequestModel.LoginRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


public interface AuthAPI {
    @PostMapping(value = "/signin")
    public ResponseEntity<?> authenticateUser(@RequestBody LoginRequest loginRequest);

}
