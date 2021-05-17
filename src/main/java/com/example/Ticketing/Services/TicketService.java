package com.example.Ticketing.Services;

import com.example.Ticketing.Models.Ticket;

import java.util.List;
import java.util.Optional;

public interface TicketService {

    Ticket add(Ticket ticket);

    Ticket update(Ticket ticket);

    void delete(Long id);

    Optional<Ticket> findById(Long id);

    List<Ticket> findAll();
}
