package com.example.Ticketing.Controllers.APIs;

import com.example.Ticketing.Models.Ticket;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

public interface TicketAPI {
    @PostMapping(value = "/Ticket/Add", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    Ticket saveTicket(@RequestBody Ticket ticket);

    @DeleteMapping(value = "/Ticket/Delete/{idTicket}", produces = MediaType.APPLICATION_JSON_VALUE)
    void deleteTicket(@PathVariable("idTicket") Long id);

    @PutMapping(value = "/Ticket/Update", produces = MediaType.APPLICATION_JSON_VALUE)
    Ticket updateTicket(@RequestBody Ticket ticket);

    @GetMapping(value = "/Ticket/{idTicket}")
    Optional<Ticket> findTicketById(@PathVariable("idTicket") Long id);

    @GetMapping(value = "/Ticket/all")
    List<Ticket> findAllTickets();
}
