package com.example.Ticketing.Controllers;

import com.example.Ticketing.Controllers.API.TicketAPI;
import com.example.Ticketing.Models.Ticket;
import com.example.Ticketing.Service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController


public class TicketController implements TicketAPI {

    //Field Injection

    private TicketService ticketService;

    //Getter Injection
    @Autowired
    public TicketService getTicketService(){
        return ticketService;
    }

    //Constructor Injection
    @Autowired
    public TicketController(TicketService ticketService){
        this.ticketService= ticketService;
    }

    @Override
    public Ticket addTicket(Ticket ticket) {
        return ticketService.addTicket(ticket);
    }

    @Override
    public void deleteTicket(Long id) {
        ticketService.deleteTicket(id);
    }

    @Override
    public Ticket updateTicket(Ticket ticket) {
        return ticketService.modifyTicket(ticket);
    }

    @Override
    public List<Ticket> findAllTickets() {
        return ticketService.findAllTickets();
    }

    @Override
    public Optional<Ticket> findTicketById(Long id) {
        return ticketService.findTicketById(id);
    }
}
