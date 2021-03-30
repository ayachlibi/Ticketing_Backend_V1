package com.example.Ticketing.Service.ServiceImp;

import com.example.Ticketing.Config.SequenceGeneratorService;
import com.example.Ticketing.Exceptions.EntityNotFoundException;
import com.example.Ticketing.Exceptions.ErrorCodes;
import com.example.Ticketing.Exceptions.InvalidEntityException;
import com.example.Ticketing.Models.CSE;
import com.example.Ticketing.Repository.*;
import com.example.Ticketing.Service.CSEService;
import com.example.Ticketing.Validators.CSEValidator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service

@Slf4j

public class CSEServiceImp implements CSEService {

    private UserRepository userRepository;

    private CSERepository cseRepository;

    private ClientRepository clientRepository;

    private TicketRepository ticketRepository;

    private AdminRepository adminRepository;

    private SequenceGeneratorService sequenceGeneratorService;

    @Autowired
    public CSEServiceImp(
             UserRepository userRepository,
             CSERepository cseRepository,
             ClientRepository clientRepository,
             TicketRepository ticketRepository,
             AdminRepository adminRepository,
             SequenceGeneratorService sequenceGeneratorService) {
        this.cseRepository=cseRepository;
        this.userRepository=userRepository;
        this.clientRepository=clientRepository;
        this.ticketRepository=ticketRepository;
        this.adminRepository=adminRepository;
        this.sequenceGeneratorService=sequenceGeneratorService;
    }

        //Add a CSE
    @Override
    public CSE addCSE(CSE cse) {

        List<String> errors = CSEValidator.validator(cse);
        //All necessary information filled
        if(!errors.isEmpty()){
            log.error("CSE is not valid {}",cse);
            throw new InvalidEntityException("The CSE is Invalid", ErrorCodes.CSE_NOT_VALID, errors);
        }
        //Check if the username is already used
        if (userRepository.existsByUsername(cse.getUsername())){
            log.error("This Username exists");
            return null;
        }
        //Check if the email is already used
        if (userRepository.existsByEmail(cse.getEmail())){
            log.error("This Email Address exists");
            return null;
        }

        //Check if the client exists
        if (!clientRepository.existsById(cse.getClient().getId())){
            throw new EntityNotFoundException("This Client does not Exist",ErrorCodes.CLIENT_NOT_FOUND);
        }

        //Check if the Admin exists
        if (!adminRepository.existsById(cse.getAdmin().getId())){
            throw new EntityNotFoundException("This Admin does not Exist",ErrorCodes.ADMIN_NOT_FOUND);
        }

        cse.setId(sequenceGeneratorService.generateSequence(cse.SEQUENCE_NAME));
        CSE SavedCse=cseRepository.save(cse);

        //In case a CSE has a ticket
        if (cse.getTickets()!= null){
            cse.getTickets().forEach(tckt->{
                tckt.setCse(SavedCse);
                ticketRepository.save(tckt);
            });
        }

        return SavedCse;

    }

        //Delete a CSE
    @Override
    public void deleteCSE(Long id) {
        if (id==null){
            log.error("ID is null");
        }
        if (!cseRepository.existsById(id)){
            throw new EntityNotFoundException("This CSE does not Exist",ErrorCodes.CSE_NOT_FOUND);
        }
        cseRepository.deleteById(id);
    }

        //Update A CSE
    @Override
    public CSE updateCSE(CSE cse) {

        if (!userRepository.existsById(cse.getId())){
            throw new EntityNotFoundException("The CSE you are trying to Update does not exist",ErrorCodes.CSE_NOT_FOUND);
        }
        if(userRepository.existsByEmail(cse.getEmail())){
            log.error("This Email Address Exist ");
        }
        if(userRepository.existsByUsername(cse.getUsername())){
            log.error("This UserName Exist ");
        }
        return cseRepository.save(cse);

    }

    @Override
    public List<CSE> findAllCSEs() {
        return cseRepository.findAll().stream()
                .collect(Collectors.toList());
    }

    @Override
    public Optional<CSE> findCSEById(Long id) {
        if (id == null){
            log.error("CSE ID is null");
            return null;
        }
        Optional<CSE> cse= cseRepository.findById(id);
        return Optional.of(cse).orElseThrow(()-> new EntityNotFoundException
                ("No CSE Found with the ID"+ id,ErrorCodes.CSE_NOT_FOUND));
    }
}
