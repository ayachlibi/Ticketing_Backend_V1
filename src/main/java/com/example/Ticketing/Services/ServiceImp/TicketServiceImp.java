package com.example.Ticketing.Services.ServiceImp;

import com.example.Ticketing.Config.SequenceGeneratorService;
import com.example.Ticketing.Exceptions.EntityNotFoundException;
import com.example.Ticketing.Exceptions.ErrorCodes;
import com.example.Ticketing.Exceptions.InvalidEntityException;
import com.example.Ticketing.Models.Ticket;
import com.example.Ticketing.Repository.TicketRepository;
import com.example.Ticketing.Services.TicketService;
import com.example.Ticketing.Validators.TicketValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service

public class TicketServiceImp implements TicketService {

    @Autowired
    TicketRepository ticketRepository;

    @Autowired
    SequenceGeneratorService service;

    @Override
    public Ticket add(Ticket ticket) {

        List<String> errors = TicketValidator.validator(ticket);

        if(!errors.isEmpty()){
            throw new InvalidEntityException("",(Throwable) errors, ErrorCodes.TICKET_NOT_VALID);
        }

        ticket.setCostumeid(service.generateSequence(ticket.SEQUENCE_NAME));

        return ticketRepository.save(ticket);
    }

    @Override
    public Ticket update(Ticket ticket) {

        return ticketRepository.save(ticket);
    }

    @Override
    public void delete(Long id) {
        if (!ticketRepository.existsByCostumeid(id)){
            throw new EntityNotFoundException("This Id doesn't match with any existing ticket ",ErrorCodes.TICKET_NOT_FOUND);
        }
        ticketRepository.deleteByCostumeid(id);
    }

    @Override
    public Optional<Ticket> findById(Long id) {
        Optional< Ticket > ticket= ticketRepository.findByCostumeid(id);
        return Optional.of(ticket).orElseThrow(()-> new EntityNotFoundException
                ("No ticket Found with the ID"+ id,ErrorCodes.TICKET_NOT_FOUND));
    }

    @Override
    public List<Ticket> findAll() {
        return ticketRepository.findAll();
    }
}
