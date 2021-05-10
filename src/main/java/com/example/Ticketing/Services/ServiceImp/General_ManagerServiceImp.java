package com.example.Ticketing.Services.ServiceImp;

import com.example.Ticketing.Config.SequenceGeneratorService;
import com.example.Ticketing.Exceptions.EntityNotFoundException;
import com.example.Ticketing.Exceptions.ErrorCodes;
import com.example.Ticketing.Exceptions.InvalidEntityException;
import com.example.Ticketing.Models.General_Manager;
import com.example.Ticketing.Models.User;
import com.example.Ticketing.Repository.General_ManagerRepository;
import com.example.Ticketing.Repository.UserRepository;
import com.example.Ticketing.Role.Role;
import com.example.Ticketing.Role.RoleRepository;
import com.example.Ticketing.Role.UserRole;
import com.example.Ticketing.Services.General_ManagerService;
import com.example.Ticketing.Validators.General_ManagerValidator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service

@Slf4j

public class General_ManagerServiceImp implements General_ManagerService {

    @Autowired
    General_ManagerRepository general_managerRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    private SequenceGeneratorService sequenceGeneratorService;

    @Autowired
    private RoleRepository roleRepository;

    @Override
    public General_Manager save(General_Manager gm) {
        List<String> errors = General_ManagerValidator.validator(gm);
        //All necessary information filled
        if (!errors.isEmpty()){
            log.error("General manager is not valid {}", gm);
            throw new InvalidEntityException("This general manager is Invalid", ErrorCodes.GENERAL_MANAGER_NOT_VALID);
        }

        //Check if the email is already used
        if (userRepository.existsByEmail(gm.getEmail())){
            log.error("This Email Address exists");
            return null;
        }

        gm.setUsername(gm.getName(),gm.getFamilyname());
        gm.setId(sequenceGeneratorService.generateSequence(gm.SEQUENCE_NAME));
        Set<Role> roles = new HashSet<>();
        Role role = roleRepository.findByName(UserRole.GENERAL_DIRECTOR);
        roles.add(role);
        gm.setRoles(roles);

        User u= new User();
        u.setEmail(gm.getEmail());
        u.setName(gm.getName());
        u.setFamilyname(gm.getFamilyname());
        u.setPhone_number(gm.getPhone_number());
        u.setPassword(gm.getPassword());
        u.setRoles(gm.getRoles());
        u.setUsername(gm.getUsername());
        userRepository.save(u);
        return general_managerRepository.save(gm);
    }

    @Override
    public General_Manager update(General_Manager gm) {

        if (!userRepository.existsById(gm.getId())){
            throw new EntityNotFoundException("GM Not Found",ErrorCodes.GENERAL_MANAGER_NOT_FOUND);
        }
        if(userRepository.existsByEmail(gm.getEmail())){
            log.error("This Email Address Exist ");
        }
        return general_managerRepository.save(gm);

    }

    @Override
    public void delete(Long id) {

        if (id == null){
            log.error("GM ID is null");

        }
        if (!general_managerRepository.existsById(id)){
            throw new EntityNotFoundException("GM Not Found", ErrorCodes.GENERAL_MANAGER_NOT_FOUND);
        }
        general_managerRepository.deleteById(id);

    }

    @Override
    public Optional<General_Manager> findById(Long id) {
        if (id == null){
            log.error("ID is null");
            return null;
        }
        Optional<General_Manager> gm= general_managerRepository.findById(id);
        return Optional.of(gm).orElseThrow(()-> new EntityNotFoundException
                ("No GM Found with the ID"+ id,ErrorCodes.GENERAL_MANAGER_NOT_FOUND));
    }

    @Override
    public List<General_Manager> findAll() {
        return general_managerRepository.findAll();
    }
}
