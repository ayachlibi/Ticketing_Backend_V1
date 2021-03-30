package com.example.Ticketing.Service;

import com.example.Ticketing.Models.Client;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


public interface ClientService {

    public Client addClient(Client client);

    public void deleteClient(Long id);

    public Client updateClient(Client client);

    public List<Client> findAll();

    public Optional <Client> findById(Long id);
}
