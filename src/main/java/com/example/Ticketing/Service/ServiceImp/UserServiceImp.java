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

/*    @Override
    public User findByUsernameAndPassword(String UserName, String Password) {

        if(UserName==null){
            log.error("The Username is null");
            return null;
        }

        if(Password==null){
            log.error("The Password is null");
            return null;
        }

        if (!userRepository.existsByUserNameAndPassword(UserName,Password)){
            throw new EntityNotFoundException("Please verify your Username and Password",ErrorCodes.USER_NOT_FOUND);

        }

        return userRepository.findByUsernameAndPassword(UserName,Password);

    }*/


    //Find by Username

    /*@Override
    public User findByUsername(String UserName) {
        if(UserName == null){
            log.error("The Username is null");
            return null;
        }
        if(!userRepository.existsByUsername(UserName)){
            throw new EntityNotFoundException("No User Found with this Username",ErrorCodes.USER_NOT_FOUND);
        }
        return userRepository.findByUsername(UserName);
    }*/

    //Find by name and family name

    /*@Override
    public User findByNameAndFamilyName(String Name, String FamilyName) {
        if(Name==null){
            log.error("The Name is null");
            return null;
        }
        if(FamilyName==null){
            log.error("The Family name  is null");
            return null;
        }

        return userRepository.findByNameAndFamilyName(Name, FamilyName);
    }*/

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

}
