package com.example.Ticketing.Controllers;

import com.example.Ticketing.Controllers.APIs.ClientAPI;
import com.example.Ticketing.Models.Client;
import com.example.Ticketing.RequestModel.ClientRequestModel;
import com.example.Ticketing.Services.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
public class ClientController implements ClientAPI {
    //Field Injection

    private ClientService clientService;

    //Getter Injection
    @Autowired
    public ClientService getClientService() {
        return clientService;
    }

    @Autowired
    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    @Override
    public Client registerClient(ClientRequestModel client) {
        return clientService.register(client);
    }

    @Override
    public ResponseEntity<?> acceptClient(Client client) {
        return clientService.accept(client);
    }

    @Override
    public void deleteClient(Long id) {
        clientService.delete(id);
    }

    @Override
    public Client updateClient(Client client) {
        return clientService.update(client);
    }

    @Override
    public Optional<Client> findClientById(Long id) {
        return clientService.findById(id);
    }

    @Override
    public List<Client> findAllClients() {
        return null;
    }
}
