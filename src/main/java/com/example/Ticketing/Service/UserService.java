package com.example.Ticketing.Service;

import com.example.Ticketing.Models.User;

import java.util.List;
import java.util.Optional;

public interface UserService {

    public User saveUser(User user);

    public void deleteUser(Long id);

    //TODO See if these implementations are necessary or not

    //public User findByUsernameAndPassword(String UserName, String Password);

    //public User findByUsername(String UserName);

    //public User findByNameAndFamilyName(String Name,String FamilyName);

    public User updateUser(User user);

    public Optional<User> findUserById(Long id);

    public List<User> findAllUsers();

}
