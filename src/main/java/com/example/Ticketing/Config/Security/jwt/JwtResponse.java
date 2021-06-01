package com.example.Ticketing.Config.Security.jwt;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.management.relation.Role;

@Setter

@Getter

@NoArgsConstructor

public class JwtResponse {
    private String token;
    private String type = "Bearer";
    private String id;
    private String username;
    private String email;
    private Role roles;

    public JwtResponse(String token,
                       String id,
                       String username,
                       String email,
                       Role roles) {
        this.token = token;
        this.id = id;
        this.username = username;
        this.email = email;
        this.roles = roles;
    }



}
