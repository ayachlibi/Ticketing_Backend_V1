package com.example.Ticketing.Services;

import com.example.Ticketing.Models.Ticket;

import java.util.List;
import java.util.Optional;

public interface TicketService {

    Ticket addTicket(Ticket ticket);

    Ticket updateTicket(Ticket ticket);

    void deleteTicket(Long id);

    Optional<Ticket> findById(Long id);

    List<Ticket> findAll();
}
