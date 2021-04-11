package com.example.Ticketing.Controllers.API;

import com.example.Ticketing.Models.User;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


public interface UserAPI {
    @PostMapping(value = "/User/AddaNewUser", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public User saveUser(@RequestBody User user);

    @DeleteMapping(value = "/User/Delete/{idUser}", produces = MediaType.APPLICATION_JSON_VALUE)
    public void deleteUser(@PathVariable("idUser") Long id);

    @PutMapping(value = "/User/Update", produces = MediaType.APPLICATION_JSON_VALUE)
    public User updateUser(@RequestBody User user);

    @GetMapping(value = "/User/{idUser}")
    public Optional<User> findUserById(@PathVariable("idUser") Long id);

    @GetMapping(value = "/User/all")
    public List<User> findAllUsers();

    @GetMapping(value = "/User/{username}/{password}")
    public User login(@PathVariable String username , @PathVariable String password);

}
