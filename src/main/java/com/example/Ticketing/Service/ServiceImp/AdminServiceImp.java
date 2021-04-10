package com.example.Ticketing.Service.ServiceImp;

import com.example.Ticketing.Config.SequenceGeneratorService;
import com.example.Ticketing.Exceptions.EntityNotFoundException;
import com.example.Ticketing.Exceptions.ErrorCodes;
import com.example.Ticketing.Exceptions.InvalidEntityException;
import com.example.Ticketing.Models.Admin;
import com.example.Ticketing.Models.CSE;
import com.example.Ticketing.Models.User;
import com.example.Ticketing.Repository.AdminRepository;
import com.example.Ticketing.Repository.CSERepository;
import com.example.Ticketing.Repository.UserRepository;
import com.example.Ticketing.Service.AdminService;
import com.example.Ticketing.Service.UserService;
import com.example.Ticketing.Validators.AdminValidator;
import com.example.Ticketing.Validators.CSEValidator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service

@Slf4j

public class AdminServiceImp implements AdminService {

    private UserRepository userRepository;

    private AdminRepository adminRepository;

    private CSERepository cseRepository;

    private SequenceGeneratorService sequenceGeneratorService;

    private UserService userService;

    public AdminServiceImp (UserRepository userRepository,
                            AdminRepository adminRepository,
                            CSERepository cseRepository,
                            SequenceGeneratorService sequenceGeneratorService,
                            UserService userService){

        this.adminRepository=adminRepository;

        this.sequenceGeneratorService=sequenceGeneratorService;

        this.cseRepository=cseRepository;

        this.userRepository=userRepository;

        this.userService=userService;
    }

    @Override
    public Admin addAdmin(Admin admin) {
        List<String> errors = AdminValidator.validator(admin);

        //All necessary information filled
        if(!errors.isEmpty()){
            log.error("Admin is not valid {}",admin);
            throw new InvalidEntityException("The Admin is Invalid", ErrorCodes.ADMIN_NOT_VALID);
        }

        //Check if the username is already used
        if (userRepository.existsByUsername(admin.getUsername())){
            log.error("This Username exists");
            return null;
        }

        //Check if the email is already used
        if (userRepository.existsByEmail(admin.getEmail())){
            log.error("This Email Address exists");
            return null;
        }

        admin.setId(sequenceGeneratorService.generateSequence(admin.SEQUENCE_NAME));
        admin.setRole("Admin");
        Admin SavedAdmin =adminRepository.save(admin);

        User u= new User();
        u.setEmail(admin.getEmail());
        u.setName(admin.getName());
        u.setFamilyname(admin.getFamilyname());
        u.setPhone_number(admin.getPhone_number());
        u.setPassword(admin.getPassword());
        u.setRole("Admin");
        u.setUsername(admin.getUsername());
        userService.saveUser(u);
        return SavedAdmin;


    }

    @Override
    public void deleteAdmin(Long id) {

        if (!adminRepository.existsById(id)){
            throw new EntityNotFoundException("This Admin does not Exist",ErrorCodes.ADMIN_NOT_FOUND);
        }

        adminRepository.deleteById(id);

    }

    @Override
    public Admin updateAdmin(Admin admin) {

        if (!adminRepository.existsById(admin.getId())){
            throw new EntityNotFoundException("The Admin you are trying to Update does not exist",ErrorCodes.ADMIN_NOT_FOUND);
        }

        if(userRepository.existsByEmail(admin.getEmail())){
            log.error("This Email Address Exist ");
        }

        if(userRepository.existsByUsername(admin.getUsername())){
            log.error("This UserName Exist ");
        }

        return adminRepository.save(admin);

    }

    @Override
    public List<Admin> findAllAdmins() {

        return adminRepository.findAll().stream()
                .collect(Collectors.toList());

    }

    @Override
    public Optional<Admin> findAdminById(Long id) {

        if (id == null){
            log.error("Admin ID is null");
            return null;
        }

        Optional<Admin> admin= adminRepository.findById(id);
        return Optional.of(admin).orElseThrow(()-> new EntityNotFoundException
                ("No Admin Found with the ID"+ id,ErrorCodes.ADMIN_NOT_FOUND));
    }

}

