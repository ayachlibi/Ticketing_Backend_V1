package com.example.Ticketing.Service.ServiceImp;

import com.example.Ticketing.Config.SequenceGeneratorService;
import com.example.Ticketing.Exceptions.EntityNotFoundException;
import com.example.Ticketing.Exceptions.ErrorCodes;
import com.example.Ticketing.Exceptions.InvalidEntityException;
import com.example.Ticketing.Models.CSE;
import com.example.Ticketing.Models.Client;
import com.example.Ticketing.Models.Message;
import com.example.Ticketing.Models.Ticket;
import com.example.Ticketing.Repository.CSERepository;
import com.example.Ticketing.Repository.ClientRepository;
import com.example.Ticketing.Repository.MessageRepository;
import com.example.Ticketing.Repository.TicketRepository;
import com.example.Ticketing.Service.TicketService;
import com.example.Ticketing.Validators.TicketValidator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service

@Slf4j

public class TicketServiceImp implements TicketService {

    private TicketRepository ticketRepository;
    private CSERepository cseRepository;
    private ClientRepository clientRepository;
    private MessageRepository messageRepository;

    private SequenceGeneratorService sequenceGeneratorService;
    @Autowired
    public TicketServiceImp(TicketRepository ticketRepository,
                            CSERepository cseRepository,
                            ClientRepository clientRepository,
                            MessageRepository messageRepository,
                            SequenceGeneratorService sequenceGeneratorService) {
        this.clientRepository=clientRepository;
        this.ticketRepository=ticketRepository;
        this.cseRepository=cseRepository;
        this.messageRepository=messageRepository;
        this.sequenceGeneratorService=sequenceGeneratorService;
    }

    //Add A Ticket
    @Override
    public Ticket addTicket(Ticket ticket) {
        List<String> errors = TicketValidator.validator(ticket);
        // All necessary Information filled
        if (!errors.isEmpty()) {
            log.error("Ticket is not valid {}", ticket);
            throw new InvalidEntityException("The Ticket is Invalid ", ErrorCodes.TICKET_NOT_VALID, errors);
        }
/*
        //Make sure that the Client Exists
        Optional<Client> client = clientRepository.findById(ticket.getClient().getId());
        if (client.isEmpty()) {
            log.warn("This Client Does not Exist");
            throw new EntityNotFoundException("No Client with the ID" + client.get().getId(), ErrorCodes.CLIENT_NOT_FOUND);
        }

        //Make sure that the CSE Exists
        Optional<CSE> cse = cseRepository.findById(ticket.getCse().getId());
        if (cse.isEmpty()) {
            log.warn("This CSE Does not Exist");
            throw new EntityNotFoundException("No CSE with the ID" + cse.get().getId(), ErrorCodes.CSE_NOT_FOUND);
        }

*/
        ticket.setId(sequenceGeneratorService.generateSequence(ticket.SEQUENCE_NAME));
        Ticket SavedTicket=ticketRepository.save(ticket);

        //In case a ticket contains a Message
        if (ticket.getMessages()!= null){
            ticket.getMessages().forEach(msg->{
                msg.setTicket(SavedTicket);
                messageRepository.save(msg);
            });
        }

        return SavedTicket;

    }

    @Override
    public void deleteTicket(Long id) {
        if (id == null){
            log.error("Ticket ID is null");
            return ;
        }
        if(!ticketRepository.existsById(id)){
            throw new EntityNotFoundException("this Ticket does nt exist",ErrorCodes.TICKET_NOT_FOUND);
        }
        ticketRepository.deleteById(id);
    }

    @Override
    public Ticket modifyTicket(Ticket ticket) {
        if (!ticketRepository.existsById(ticket.getId())){
            throw new EntityNotFoundException("This Ticket Does not Exist",ErrorCodes.TICKET_NOT_FOUND);
        }
        return ticketRepository.save(ticket);
    }


    @Override
    public List<Ticket> findAllTickets() {
        return ticketRepository.findAll().stream()
                .collect(Collectors.toList());
    }

    @Override
    public Optional <Ticket> findTicketById(Long id) {
        if (id == null){
            log.error("Ticket ID is null");
            return null;
        }
        Optional<Ticket> ticket= ticketRepository.findById(id);
        return Optional.of(ticket).orElseThrow(()-> new EntityNotFoundException
                        ("No Ticket Found with the ID"+ id,ErrorCodes.TICKET_NOT_FOUND));
    }
}
