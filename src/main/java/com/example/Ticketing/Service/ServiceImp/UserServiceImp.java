package com.example.Ticketing.Service.ServiceImp;

import com.example.Ticketing.Config.SequenceGeneratorService;
import com.example.Ticketing.Exceptions.EntityNotFoundException;
import com.example.Ticketing.Exceptions.ErrorCodes;
import com.example.Ticketing.Exceptions.InvalidEntityException;
import com.example.Ticketing.Models.User;
import com.example.Ticketing.Repository.UserRepository;
import com.example.Ticketing.Service.UserService;
import com.example.Ticketing.Validators.UserValidator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service

@Slf4j

public class UserServiceImp implements UserService {

    private UserRepository userRepository;

    private SequenceGeneratorService sequenceGeneratorService;
    @Autowired
    public UserServiceImp(UserRepository userRepository,
                          SequenceGeneratorService sequenceGeneratorService){
        this.userRepository=userRepository;
        this.sequenceGeneratorService=sequenceGeneratorService;
    }

    //Add A new User
    @Override
    public User saveUser(User user) {
        List<String> errors = UserValidator.validator(user);

        //All necessary information filled
        if(!errors.isEmpty()){
            log.error("User is not valid {}",user);
            throw new InvalidEntityException("The User is Invalid", ErrorCodes.USER_NOT_VALID);
        }

        if(userRepository.existsByEmail(user.getEmail())){
            log.error("This Email Address Exist ");
        }

        if(userRepository.existsByUsername(user.getUsername())){
            log.error("This UserName Exist ");
        }

        user.setId(sequenceGeneratorService.generateSequence(user.SEQUENCE_NAME));
        return userRepository.save(user);
    }

    //Delete A User

    @Override
    public void deleteUser(Long id) {
        if (id == null){
            log.error("User ID is null");
        }

        if (!userRepository.existsById(id)){
            throw new EntityNotFoundException("This Client does not exist", ErrorCodes.USER_NOT_FOUND);
        }
        userRepository.deleteById(id);
    }

    // update A user

    @Override
    public User updateUser(User user) {

        if (!userRepository.existsById(user.getId())){
            throw new EntityNotFoundException("The user you are trying to Update does not exist",
                    ErrorCodes.USER_NOT_FOUND);
        }

        if(userRepository.existsByEmail(user.getEmail())){
            log.error("This Email Address Exist ");
        }

        if(userRepository.existsByUsername(user.getUsername())){
            log.error("This Username Exist ");
        }
        user.setId(sequenceGeneratorService.generateSequence(user.SEQUENCE_NAME));
        return userRepository.save(user);
    }

    //Find a User by id
    @Override
    public Optional<User> findUserById(Long id) {
        if (id == null){
            log.error("User ID is null");
            return null;
        }
        Optional<User> user= userRepository.findById(id);
        return Optional.of(user).orElseThrow(()-> new EntityNotFoundException
                ("No User Found with the ID"+ id,ErrorCodes.USER_NOT_FOUND));
    }

    //Find All

    @Override
    public List<User> findAllUsers() {
        return userRepository.findAll().stream()
                .collect(Collectors.toList());
    }

    @Override
    public User login(String username, String password)  {
        User user = userRepository.findByUsernameAndPassword(username,password);
        if (user == null) {
            if (userRepository.findByUsername(username) == null) {
                throw new EntityNotFoundException("Please verify your username and password", ErrorCodes.USER_NOT_FOUND);
            }
            else{
                throw new EntityNotFoundException("Please verify your password", ErrorCodes.USER_NOT_FOUND);
            }
        }
            return user;
        }

}
