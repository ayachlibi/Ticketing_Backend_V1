package com.example.Ticketing.Services.ServiceImp;

import com.example.Ticketing.Config.SequenceGeneratorService;
import com.example.Ticketing.Exceptions.EntityNotFoundException;
import com.example.Ticketing.Exceptions.ErrorCodes;
import com.example.Ticketing.Exceptions.InvalidEntityException;
import com.example.Ticketing.Models.CSE;
import com.example.Ticketing.Models.User;
import com.example.Ticketing.Repository.CSERepository;
import com.example.Ticketing.Repository.UserRepository;
import com.example.Ticketing.RequestModel.CSERequestModel;
import com.example.Ticketing.Role.UserRole;
import com.example.Ticketing.Services.CSEService;
import com.example.Ticketing.Validators.CSEValidator;
import com.example.Ticketing.Validators.EmailValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;

@Service

public class CSEServiceImp implements CSEService {

    @Autowired
    EmailValidator emailValidator;

    @Autowired
    UserRepository userRepository;

    @Autowired
    CSERepository cseRepository;

    @Autowired
    SequenceGeneratorService service;

    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    UserService userService;


    @Override
    public String register (CSERequestModel cseRequestModel) {

        List<String> errors = CSEValidator.validator(cseRequestModel);
        //All necessary information filled

        if (!errors.isEmpty()){
            throw new InvalidEntityException("This cse is Invalid",
                    (Throwable) errors,
                    ErrorCodes.CSE_NOT_VALID);
        }

        //Validate the email address
        Boolean isValidEmail = emailValidator.test(cseRequestModel.getEmail());

        if (!isValidEmail) {
            throw new IllegalStateException("Email not valid");
        }

        if (cseRepository.existsByEmail(cseRequestModel.getEmail())){
            throw new IllegalStateException("This email already Exists!");
        }

        CSE cse= new CSE(
                cseRequestModel.getName(),
                cseRequestModel.getFamilyname(),
                cseRequestModel.getEmail(),
                cseRequestModel.getPhone_number(),
                cseRequestModel.getName()+ cseRequestModel.getFamilyname(),
                bCryptPasswordEncoder.encode(cseRequestModel.getPassword()),
                UserRole.CSE
                );

        cse.setCostumeid(service.generateSequence(cse.SEQUENCE_NAME));

        cseRepository.save(cse);
        // TODO: Change the return type

        return null;
    }

    @Override
    public ResponseEntity<?> accept(CSE cse) {

        //Accept the client
        cse.setAccepted(true);

        //Create the account
        User user= new User(
                cse.getFirstname(),
                cse.getLastname(),
                cse.getEmail(),
                cse.getPhone_number(),
                cse.getUsername(),
                cse.getPassword(),
                cse.getRole()
        );

        user.setCostumeid(service.generateSequence(user.SEQUENCE_NAME));
        userRepository.save(user);
        return ResponseEntity.ok("CSE Accepted");
    }

    @Override
    public void delete(Long id) {

        if (!cseRepository.existsByCostumeid(id)){
            throw new EntityNotFoundException("This CSE does not exist", ErrorCodes.CLIENT_NOT_FOUND);
        }
        cseRepository.deleteByCostumeid(id);


    }

    @Override
    public CSE update(CSE cse) {
        if (!cseRepository.existsByCostumeid(cse.getCostumeid())){
            throw new EntityNotFoundException("The Client you are trying to Update does not exist",ErrorCodes.CLIENT_NOT_FOUND);
        }
        if(userRepository.existsByEmail(cse.getEmail())){
            throw new IllegalStateException("This Email Address Exist ");
        }
        User user= userRepository.findByEmail(cse.getEmail());

        return cseRepository.save(cse);
    }

    @Override
    public List<CSE> findAll() {
        return new ArrayList<>(cseRepository.findAll());
    }

    @Override
    public Optional<CSE> findById(Long id) {


        Optional<CSE> cse= cseRepository.findByCostumeid(id);
        return Optional.of(cse).orElseThrow(()-> new EntityNotFoundException
                ("No CSE Found with the ID"+ id,ErrorCodes.CSE_NOT_FOUND));
    }

}

