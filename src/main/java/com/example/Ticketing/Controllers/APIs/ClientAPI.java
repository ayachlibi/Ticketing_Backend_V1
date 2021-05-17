package com.example.Ticketing.Controllers.APIs;


import com.example.Ticketing.Models.Client;
import com.example.Ticketing.RequestModel.ClientRequestModel;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

public interface ClientAPI {

    @PostMapping(value = "/Client/RegisterClient", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    Client registerClient(@RequestBody ClientRequestModel client);

    @PutMapping(value = "/Client/Accept", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<?> acceptClient(@RequestBody Client client);

    @DeleteMapping(value = "/Client/Delete/{idClient}", produces = MediaType.APPLICATION_JSON_VALUE)
    void deleteClient(@PathVariable("idClient") Long id);

    @PutMapping(value = "/Client/Update", produces = MediaType.APPLICATION_JSON_VALUE)
    Client updateClient(@RequestBody Client client);

    @GetMapping(value = "/Client/{idClient}")
    Optional<Client> findClientById(@PathVariable("idClient") Long id);

    @GetMapping(value = "/Client/all")
    List<Client> findAllClients();
}
