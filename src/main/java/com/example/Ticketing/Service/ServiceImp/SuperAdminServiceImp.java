package com.example.Ticketing.Service.ServiceImp;

import com.example.Ticketing.Config.SequenceGeneratorService;
import com.example.Ticketing.Exceptions.EntityNotFoundException;
import com.example.Ticketing.Exceptions.ErrorCodes;
import com.example.Ticketing.Exceptions.InvalidEntityException;
import com.example.Ticketing.Models.Client;
import com.example.Ticketing.Models.SuperAdmin;
import com.example.Ticketing.Models.User;
import com.example.Ticketing.Repository.SuperAdminRepository;
import com.example.Ticketing.Repository.UserRepository;
import com.example.Ticketing.Service.SuperAdminService;
import com.example.Ticketing.Service.UserService;
import com.example.Ticketing.Validators.SuperAdminValidator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service

@Slf4j
public class SuperAdminServiceImp implements SuperAdminService {

    private UserRepository userRepository;

    private SuperAdminRepository superAdminRepository;

    private SequenceGeneratorService sequenceGeneratorService;

    private UserService userService;
    @Autowired
    public SuperAdminServiceImp(
             UserRepository userRepository,
             SuperAdminRepository superAdminRepository,
             SequenceGeneratorService sequenceGeneratorService,
             UserService userService){
        this.superAdminRepository=superAdminRepository;
        this.userRepository=userRepository;
        this.sequenceGeneratorService=sequenceGeneratorService;
        this.userService=userService;
    }

    @Override
    public SuperAdmin addSuperAdmin(SuperAdmin superAdmin) {
        List<String> errors = SuperAdminValidator.validator(superAdmin);
        //All necessary information filled
        if (!errors.isEmpty()){
            log.error("Super Admin is not valid {}", superAdmin);
            throw new InvalidEntityException("This Super Admin is Invalid", ErrorCodes.SUPER_ADMIN_NOT_VALID);
        }
        //Check if the username is already used
        if (userRepository.existsByUsername(superAdmin.getUsername())){
            log.error("This Username exists");
            return null;
        }
        //Check if the email is already used
        if (userRepository.existsByEmail(superAdmin.getEmail())){
            log.error("This Email Address exists");
            return null;
        }
        superAdmin.setId(sequenceGeneratorService.generateSequence(superAdmin.SEQUENCE_NAME));
        superAdmin.setRole("Super Admin");
        User u= new User();
        u.setEmail(superAdmin.getEmail());
        u.setName(superAdmin.getName());
        u.setFamilyname(superAdmin.getFamilyname());
        u.setPhone_number(superAdmin.getPhone_number());
        u.setPassword(superAdmin.getPassword());
        u.setRole("Super Admin");
        u.setUsername(superAdmin.getUsername());
        userService.saveUser(u);
        return superAdminRepository.save(superAdmin);
    }

    @Override
    public void deleteSuperAdmin(Long id) {
        if (id == null){
            log.error("Super Admin ID is null");

        }
        if (!superAdminRepository.existsById(id)){
            throw new EntityNotFoundException("This Super Admin does not exist", ErrorCodes.SUPER_ADMIN_NOT_FOUND);
        }
        superAdminRepository.deleteById(id);

    }

    @Override
    public SuperAdmin updateAdmin(SuperAdmin superAdmin) {

        if (!userRepository.existsById(superAdmin.getId())){
            throw new EntityNotFoundException("The Super Admin you are trying to Update does not exist",ErrorCodes.SUPER_ADMIN_NOT_FOUND);
        }
        if(userRepository.existsByEmail(superAdmin.getEmail())){
            log.error("This Email Address Exist ");
        }
        if(userRepository.existsByUsername(superAdmin.getUsername())){
            log.error("This UserName Exist ");
        }
        return superAdminRepository.save(superAdmin);

    }

    @Override
    public List<SuperAdmin> findAllSuperAdmins() {
        return superAdminRepository.findAll().stream()
                .collect(Collectors.toList());
    }

    @Override
    public Optional<SuperAdmin> findSuperAdminById(Long id) {
        if (id == null){
            log.error("Super Admin ID is null");
            return null;
        }
        Optional<SuperAdmin> superAdmin= superAdminRepository.findById(id);
        return Optional.of(superAdmin).orElseThrow(()-> new EntityNotFoundException
                ("No Super Admin Found with the ID"+ id,ErrorCodes.SUPER_ADMIN_NOT_FOUND));

    }
}
