package com.example.Ticketing.Service;

import com.example.Ticketing.Models.User;

import java.util.List;
import java.util.Optional;

public interface UserService {

    public User saveUser(User user);

    public void deleteUser(Long id);

    public User updateUser(User user);

    public Optional<User> findUserById(Long id);

    public List<User> findAllUsers();

    public User login(String username, String password);

}
