package com.example.Ticketing.Controllers.API;

import com.example.Ticketing.Models.Client;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

public interface ClientAPI {
    @PostMapping(value = "/Client/AddaNewCSE", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Client saveClient(@RequestBody Client client);

    @DeleteMapping(value = "/Client/Delete/{idClient}", produces = MediaType.APPLICATION_JSON_VALUE)
    public void deleteClient(@PathVariable("idClient") Long id);

    @PutMapping(value = "Client/Update", produces = MediaType.APPLICATION_JSON_VALUE)
    public Client updateClient(@RequestBody Client client);

    @GetMapping(value = "Client/{idClient}")
    public Optional<Client> findClientById(@PathVariable("idClient") Long id);

    @GetMapping(value = "Client/all")
    public List<Client> findAllClients();


}
