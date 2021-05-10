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
import com.example.Ticketing.Role.Role;
import com.example.Ticketing.Role.RoleRepository;
import com.example.Ticketing.Role.UserRole;
import com.example.Ticketing.Services.CSEService;
import com.example.Ticketing.Validators.CSEValidator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;

@Service

@Slf4j

public class CSEServiceImp implements CSEService {


    @Autowired
    RoleRepository roleRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    CSERepository cseRepository;

    @Autowired
    SequenceGeneratorService service;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Override
    public ResponseEntity<?> registerCSE(CSERequestModel cseRequestModel) {
        List<String> errors = CSEValidator.validator(cseRequestModel);
        //All necessary information filled
        if (!errors.isEmpty()){
            throw new InvalidEntityException("This cse is Invalid", (Throwable) errors,ErrorCodes.CSE_NOT_VALID);
        }
        if (userRepository.existsByEmail(cseRequestModel.getEmail())){
            throw new IllegalStateException("This email already Exists!");
        }

        CSE cse= new CSE();
        cse.setPhone_number(cseRequestModel.getPhone_number());
        cse.setName(cseRequestModel.getName());
        cse.setFamilyname(cseRequestModel.getFamilyname());
        cse.setEmail(cseRequestModel.getEmail());
        cse.setPassword(passwordEncoder.encode(cseRequestModel.getPassword()));
        cse.setUsername(cse.getName(),cseRequestModel.getFamilyname());
        cse.setId(service.generateSequence(cse.SEQUENCE_NAME));

        Set<Role> roles = new HashSet<>();

        Role role = roleRepository.findByName(UserRole.CSE);
        roles.add(role);

        cse.setRoles(roles);

        cseRepository.save(cse);

        // TODO: Change the return type

        return ResponseEntity.ok("User registered successfully!");
    }

    @Override
    public ResponseEntity<?> acceptCse(CSE cse) {
        //Accept the client
        cse.setAccepted(true);

        //Create a new user( for the Login )
        User user=new User();
        user.setUsername(cse.getUsername());
        user.setName(cse.getName());
        user.setFamilyname(cse.getFamilyname());
        user.setPassword(cse.getPassword());
        user.setPhone_number(cse.getPhone_number());
        user.setEmail(cse.getEmail());
        user.setId(service.generateSequence(user.SEQUENCE_NAME));
        user.setRoles(cse.getRoles());
        userRepository.save(user);
        return ResponseEntity.ok("CSE Accepted");
    }

    @Override
    public void deleteCse(Long id) {

        if (id == null){
            log.error("CSE ID is null");

        }
        if (!cseRepository.existsById(id)){
            throw new EntityNotFoundException("This CSE does not exist", ErrorCodes.CLIENT_NOT_FOUND);
        }
        cseRepository.deleteById(id);


    }

    @Override
    public CSE updateCse(CSE cse) {
        if (!userRepository.existsById(cse.getId())){
            throw new EntityNotFoundException("The Client you are trying to Update does not exist",ErrorCodes.CLIENT_NOT_FOUND);
        }
        if(userRepository.existsByEmail(cse.getEmail())){
            log.error("This Email Address Exist ");
        }
        if(userRepository.existsByUsername(cse.getUsername())){
            log.error("This UserName Exist ");
        }
        return cseRepository.save(cse);    }

    @Override
    public List<CSE> findAll() {
        return new ArrayList<>(cseRepository.findAll());
    }

    @Override
    public Optional<CSE> findById(Long id) {
        if (id == null){
            log.error("Client ID is null");
            return null;
        }
        Optional<CSE> cse= cseRepository.findById(id);
        return Optional.of(cse).orElseThrow(()-> new EntityNotFoundException
                ("No CSE Found with the ID"+ id,ErrorCodes.CSE_NOT_FOUND));
    }

}

