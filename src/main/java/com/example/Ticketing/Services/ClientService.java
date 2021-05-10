package com.example.Ticketing.Services;

import com.example.Ticketing.Models.Client;
import com.example.Ticketing.RequestModel.ClientRequestModel;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

public interface ClientService {

    ResponseEntity<?> registerClient(ClientRequestModel clientRequestModel);

    ResponseEntity<?> acceptClient(Client client);

    void deleteClient(Long id);

    Client updateClient(Client client);

    List<Client> findAll();

    Optional<Client> findById(Long id);
}
