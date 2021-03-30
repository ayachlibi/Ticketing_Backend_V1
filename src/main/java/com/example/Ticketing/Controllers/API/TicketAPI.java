package com.example.Ticketing.Controllers.API;

import com.example.Ticketing.Models.CSE;
import com.example.Ticketing.Models.Ticket;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.net.URL;
import java.util.List;
import java.util.Optional;

public interface TicketAPI {
    @PostMapping(value = "/Ticket/AddaNewTicket", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Ticket addTicket(@RequestBody Ticket ticket);

    @DeleteMapping (value = "/Ticket/Delete/{idTicket}", produces = MediaType.APPLICATION_JSON_VALUE)
    public void deleteTicket(@PathVariable("idTicket") Long id);

    @PutMapping(value = "Ticket/Update", produces = MediaType.APPLICATION_JSON_VALUE)
    public Ticket updateTicket(@RequestBody Ticket ticket);

    @GetMapping (value = "/Ticket/all", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Ticket> findAllTickets();

    @GetMapping (value = "/Ticket/{idTicket}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Optional<Ticket> findTicketById(@PathVariable("idTicket") Long id);

}
