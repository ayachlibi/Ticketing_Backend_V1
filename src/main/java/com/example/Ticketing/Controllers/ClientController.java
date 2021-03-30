package com.example.Ticketing.Controllers;

import com.example.Ticketing.Controllers.API.ClientAPI;
import com.example.Ticketing.Models.Client;
import com.example.Ticketing.Service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController

public class ClientController implements ClientAPI {
    //Field Injection

    private ClientService clientService;

    //Getter Injection
    @Autowired
    public ClientService getClientService(){
        return clientService;
    }

    //Constructor Injection
    @Autowired
    public ClientController(ClientService clientService){
        this.clientService= clientService;
    }


    @Override
    public Client saveClient(Client client) {
        return clientService.addClient(client);
    }

    @Override
    public void deleteClient(Long id) {
        clientService.deleteClient(id);
    }

    @Override
    public Client updateClient(Client client) {
        return clientService.updateClient(client);
    }

    @Override
    public Optional<Client> findClientById(Long id) {
        return clientService.findById(id);
    }

    @Override
    public List<Client> findAllClients() {
        return clientService.findAll();
    }
}
