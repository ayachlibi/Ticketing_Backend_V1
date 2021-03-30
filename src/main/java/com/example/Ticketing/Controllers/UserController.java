package com.example.Ticketing.Controllers;

import com.example.Ticketing.Controllers.API.UserAPI;
import com.example.Ticketing.Models.User;
import com.example.Ticketing.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController

public class UserController implements UserAPI {

    //Field Injection

    private UserService userService;

    //Getter Injection
    @Autowired
    public UserService getUserService(){
        return userService;
    }

    //Constructor Injection
    @Autowired
    public UserController(UserService userService){
        this.userService= userService;
    }

    @Override
    public User saveUser(User user) {
        return userService.saveUser(user);
    }

    @Override
    public void deleteUser(Long id) {
        userService.deleteUser(id);
    }

    @Override
    public User updateUser(User user) {
        return userService.updateUser(user);
    }

    @Override
    public Optional<User> findUserById(Long id) {
        return userService.findUserById(id);
    }

    @Override
    public List<User> findAllUsers() {
        return userService.findAllUsers();
    }
}
