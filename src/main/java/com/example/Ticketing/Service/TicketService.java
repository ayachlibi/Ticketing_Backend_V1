package com.example.Ticketing.Service;

import com.example.Ticketing.Models.Ticket;

import java.util.List;
import java.util.Optional;

public interface TicketService {

    public Ticket addTicket(Ticket ticket);

    public void deleteTicket(Long id);

    public Ticket modifyTicket(Ticket ticket);

    public List<Ticket> findAllTickets();

    public Optional<Ticket> findTicketById(Long id);
}
