package com.example.Ticketing.Services.ServiceImp;

import com.example.Ticketing.Config.Security.jwt.JwtResponse;
import com.example.Ticketing.Config.Security.jwt.JwtUtils;
import com.example.Ticketing.Exceptions.EntityNotFoundException;
import com.example.Ticketing.Exceptions.ErrorCodes;
import com.example.Ticketing.Models.User;
import com.example.Ticketing.Repository.UserRepository;
import com.example.Ticketing.RequestModel.LoginRequest;
import com.example.Ticketing.Services.UserDetailsService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.management.relation.Role;
import java.util.stream.Collectors;

@Service

@AllArgsConstructor

public class UserService implements UserDetailsService  {

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    JwtUtils jwtUtils;

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUsername(username)
                .orElseThrow(()->
                        new EntityNotFoundException("User with %s as a username not found"+ username,
                                ErrorCodes.USER_NOT_FOUND));

    }

    public User authenticateUser(LoginRequest loginRequest) {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);

        User user = (User) authentication.getPrincipal();
        Role roles = (Role) user.getAuthorities();

        return user;
    }


}
