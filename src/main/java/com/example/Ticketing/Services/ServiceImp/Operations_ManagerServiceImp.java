package com.example.Ticketing.Services.ServiceImp;

import com.example.Ticketing.Config.SequenceGeneratorService;
import com.example.Ticketing.Exceptions.EntityNotFoundException;
import com.example.Ticketing.Exceptions.ErrorCodes;
import com.example.Ticketing.Exceptions.InvalidEntityException;
import com.example.Ticketing.Models.Operations_Manager;
import com.example.Ticketing.Models.User;
import com.example.Ticketing.Repository.Operations_ManagerRepository;
import com.example.Ticketing.Repository.UserRepository;
import com.example.Ticketing.Role.UserRole;
import com.example.Ticketing.Services.Operations_ManagerService;
import com.example.Ticketing.Validators.Operations_ManagerValidator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service

@Slf4j

public class Operations_ManagerServiceImp implements Operations_ManagerService {
    @Autowired
    Operations_ManagerRepository operations_managerRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    private SequenceGeneratorService sequenceGeneratorService;

    @Override
    public Operations_Manager save(Operations_Manager om) {
        List<String> errors = Operations_ManagerValidator.validator(om);
        //All necessary information filled
        if (!errors.isEmpty()){
            log.error("Operations manager is not valid {}", om);
            throw new InvalidEntityException("This operations manager is Invalid", ErrorCodes.OPERATIONS_MANAGER_NOT_VALID);
        }

        //Check if the email is already used
        if (userRepository.existsByEmail(om.getEmail())){
            log.error("This Email Address exists");
            return null;
        }

        om.setUsername(om.getFirstname(),om.getLastname());
        om.setCostumeid(sequenceGeneratorService.generateSequence(om.SEQUENCE_NAME));
        om.setRole(UserRole.OPERATIONS_MANAGER);
        User u= new User();
        u.setEmail(om.getEmail());
        u.setFirstname(om.getFirstname());
        u.setLastname(om.getLastname());
        u.setPhone_number(om.getPhone_number());
        u.setPassword(om.getPassword());
        u.setRole(om.getRole());
        u.setUsername(om.getUsername());
        userRepository.save(u);
        return operations_managerRepository.save(om);
    }

    @Override
    public Operations_Manager update(Operations_Manager om) {

        if (!userRepository.existsById(om.getMongoid())){
            throw new EntityNotFoundException("OM Not Found",ErrorCodes.OPERATIONS_MANAGER_NOT_FOUND);
        }
        if(userRepository.existsByEmail(om.getEmail())){
            log.error("This Email Address Exist ");
        }
        return operations_managerRepository.save(om);

    }

    @Override
    public void delete(Long id) {

        if (id == null){
            log.error("OM ID is null");

        }
        if (!operations_managerRepository.existsByCostumeid(id)){
            throw new EntityNotFoundException("OM Not Found", ErrorCodes.OPERATIONS_MANAGER_NOT_FOUND);
        }
        operations_managerRepository.deleteByCostumeid(id);

    }

    @Override
    public Optional<Operations_Manager> findById(Long id) {
        if (id == null){
            log.error("ID is null");
            return null;
        }
        Optional<Operations_Manager> om= operations_managerRepository.findByCostumeid(id);
        return Optional.of(om).orElseThrow(()-> new EntityNotFoundException
                ("No OM Found with the ID"+ id,ErrorCodes.OPERATIONS_MANAGER_NOT_FOUND));

    }

    @Override
    public List<Operations_Manager> findAll() {
        return operations_managerRepository.findAll();
    }
}
