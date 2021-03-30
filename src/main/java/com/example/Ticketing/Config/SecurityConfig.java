package com.example.Ticketing.Config;

import com.example.Ticketing.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {
   /* @Autowired
    private JwtAuthenticationFilter jwtAuthentificationFilter;
    @Autowired
    private JwtAuthorizationFilter jwtAuthorizationFilter;*/
    @Autowired
    private UserService userService ;
}
