package com.example.Ticketing.Services.ServiceImp;

import com.example.Ticketing.Config.SequenceGeneratorService;
import com.example.Ticketing.Exceptions.EntityNotFoundException;
import com.example.Ticketing.Exceptions.ErrorCodes;
import com.example.Ticketing.Exceptions.InvalidEntityException;
import com.example.Ticketing.Models.Client;
import com.example.Ticketing.Models.User;
import com.example.Ticketing.Repository.ClientRepository;
import com.example.Ticketing.Repository.UserRepository;
import com.example.Ticketing.RequestModel.ClientRequestModel;
import com.example.Ticketing.Role.Role;
import com.example.Ticketing.Role.RoleRepository;
import com.example.Ticketing.Role.UserRole;
import com.example.Ticketing.Services.ClientService;
import com.example.Ticketing.Validators.ClientValidator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;

@Service

@Slf4j
public class ClientServiceImp implements ClientService {

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    ClientRepository clientRepository;

    @Autowired
    SequenceGeneratorService service;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Override
    public Client register(ClientRequestModel clientRequestModel) {

        List<String> errors = ClientValidator.validator(clientRequestModel);
        //All necessary information filled
        if (!errors.isEmpty()){
            throw new InvalidEntityException("This client is Invalid", (Throwable) errors,ErrorCodes.CLIENT_NOT_VALID);
        }
        if (userRepository.existsByEmail(clientRequestModel.getEmail())){
            throw new IllegalStateException("This email already Exists!");
        }

        Client client= new Client();
        client.setEnterprise(clientRequestModel.getEnterprise());
        client.setPhone_number(clientRequestModel.getPhone_number());
        client.setName(clientRequestModel.getName());
        client.setFamilyname(clientRequestModel.getFamilyname());
        client.setEmail(clientRequestModel.getEmail());
        client.setPassword(passwordEncoder.encode(clientRequestModel.getPassword()));
        client.setUsername(client.getName(),client.getFamilyname());
        client.setId(service.generateSequence(client.SEQUENCE_NAME));

        Set<Role> roles = new HashSet<>();

        Role role = roleRepository.findByName(UserRole.CLIENT);
        roles.add(role);

        client.setRoles(roles);

        // TODO: Change the return type

        return clientRepository.save(client);
    }

    @Override
    public ResponseEntity<?> accept(Client client) {

        client.setAccepted(true);

        User user=new User();
        user.setUsername(client.getUsername());
        user.setName(client.getName());
        user.setFamilyname(client.getFamilyname());
        user.setPassword(client.getPassword());
        user.setPhone_number(client.getPhone_number());
        user.setEmail(client.getEmail());
        user.setId(service.generateSequence(user.SEQUENCE_NAME));
        user.setRoles(client.getRoles());
        userRepository.save(user);
        return ResponseEntity.ok("Client Accepted");

    }

    @Override
    public void delete(Long id) {

        if (id == null){
            throw new IllegalStateException("Client ID is null");

        }
        if (!clientRepository.existsById(id)){
            throw new EntityNotFoundException("This Client does not exist", ErrorCodes.CLIENT_NOT_FOUND);
        }
        clientRepository.deleteById(id);
    }

    @Override
    public Client update(Client client) {

        if (!clientRepository.existsById(client.getId())){
            throw new EntityNotFoundException("The Client you are trying to Update does not exist",ErrorCodes.CLIENT_NOT_FOUND);
        }

        if(userRepository.existsByEmail(client.getEmail())){
            throw new IllegalStateException("This Email Address Exist ");
        }

        User user = userRepository.findByUsername(client.getUsername())
                .orElseThrow(() -> new UsernameNotFoundException("User Not Found" ));

        if(!user.getEmail().equals(client.getEmail())){
            user.setEmail(client.getEmail());
        }

        if(!user.getName().equals(client.getName())){
            user.setName(client.getName());
        }

        if(!user.getFamilyname().equals(client.getFamilyname())){
            user.setFamilyname(client.getFamilyname());
        }

        if(user.getPhone_number() != client.getPhone_number()){
            user.setPhone_number(client.getPhone_number());
        }

        return clientRepository.save(client);

    }

    @Override
    public List<Client> findAll() {
        return new ArrayList<>(clientRepository.findAll());
    }

    @Override
    public Optional<Client> findById(Long id) {
        if (id == null){
            throw new IllegalStateException("Client ID is null");

        }
        Optional<Client> client= clientRepository.findById(id);
        return Optional.of(client).orElseThrow(()-> new EntityNotFoundException
                ("No Client Found with the ID"+ id,ErrorCodes.CLIENT_NOT_FOUND));
    }

}
