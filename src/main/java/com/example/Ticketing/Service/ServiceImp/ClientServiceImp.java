package com.example.Ticketing.Service.ServiceImp;

import com.example.Ticketing.Config.SequenceGeneratorService;
import com.example.Ticketing.Exceptions.EntityNotFoundException;
import com.example.Ticketing.Exceptions.ErrorCodes;
import com.example.Ticketing.Exceptions.InvalidEntityException;
import com.example.Ticketing.Models.CSE;
import com.example.Ticketing.Models.Client;
import com.example.Ticketing.Models.User;
import com.example.Ticketing.Repository.CSERepository;
import com.example.Ticketing.Repository.ClientRepository;
import com.example.Ticketing.Repository.TicketRepository;
import com.example.Ticketing.Repository.UserRepository;
import com.example.Ticketing.Service.ClientService;
import com.example.Ticketing.Validators.UserValidator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service

@Slf4j

public class    ClientServiceImp implements ClientService {


    private  ClientRepository clientRepository;

    private UserRepository userRepository;

    private CSERepository cseRepository;

    private TicketRepository ticketRepository;

    private SequenceGeneratorService sequenceGeneratorService;

    @Autowired
    public ClientServiceImp(ClientRepository clientRepository,
                            UserRepository userRepository,
                            TicketRepository ticketRepository,
                            CSERepository cseRepository,
                            SequenceGeneratorService sequenceGeneratorService){

        this.clientRepository=clientRepository;
        this.cseRepository=cseRepository;
        this.userRepository=userRepository;
        this.ticketRepository=ticketRepository;
        this.sequenceGeneratorService=sequenceGeneratorService;
    }

    //Add a Client
    @Override
    public Client addClient(Client client) {

        List<String> errors = UserValidator.validator(client);
        //All necessary information filled
        if(!errors.isEmpty()){
            log.error("Client is not valid {}",client);
            throw new InvalidEntityException("The Client is Invalid", ErrorCodes.CLIENT_NOT_VALID);
        }

        //Check if the username is already used
        if (userRepository.existsByUsername(client.getUsername())){
            log.error("This Username exists");
            return null;
        }

        //Check if the email is already used
        if (userRepository.existsByEmail(client.getEmail())){
            log.error("This Email Address exists");
            return null;
        }

        client.setId(sequenceGeneratorService.generateSequence(client.SEQUENCE_NAME));

        return clientRepository.save(client);
/*
        //In case a Client has a ticket
        if (client.getTickets()!= null){
            client.getTickets().forEach(tckt->{
                tckt.setClient(SavedClient);
                ticketRepository.save(tckt);
            });
        }

        //In case a Client has a cse
        if (client.getCses()!= null){
            client.getCses().forEach(cse->{
                cse.setClient(SavedClient);
                cseRepository.save(cse);
            });
        }
*/

    }

    //Delete a Client
    @Override
    public void deleteClient(Long id) {

        if (id == null){
            log.error("Client ID is null");

        }
        if (!clientRepository.existsById(id)){
            throw new EntityNotFoundException("This Client does not exist", ErrorCodes.CLIENT_NOT_FOUND);
        }
        clientRepository.deleteById(id);
    }

    //Update a client
    @Override
    public Client updateClient(Client client) {

        if (!userRepository.existsById(client.getId())){
            throw new EntityNotFoundException("The Client you are trying to Update does not exist",ErrorCodes.CLIENT_NOT_FOUND);
        }
        if(userRepository.existsByEmail(client.getEmail())){
            log.error("This Email Address Exist ");
        }
        if(userRepository.existsByUsername(client.getUsername())){
            log.error("This UserName Exist ");
        }
        return clientRepository.save(client);

    }

    @Override
    public List<Client> findAll() {
        return clientRepository.findAll().stream()
                .collect(Collectors.toList());
    }

    @Override
    public Optional<Client> findById(Long id) {
        if (id == null){
            log.error("Client ID is null");
            return null;
        }
        Optional<Client> client= clientRepository.findById(id);
        return Optional.of(client).orElseThrow(()-> new EntityNotFoundException
                ("No Client Found with the ID"+ id,ErrorCodes.CLIENT_NOT_FOUND));
    }
}
