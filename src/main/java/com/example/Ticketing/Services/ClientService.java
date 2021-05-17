package com.example.Ticketing.Services;

import com.example.Ticketing.Models.Client;
import com.example.Ticketing.RequestModel.ClientRequestModel;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

public interface ClientService {

    Client register(ClientRequestModel clientRequestModel);

    ResponseEntity<?> accept(Client client);

    void delete(Long id);

    Client update(Client client);

    List<Client> findAll();

    Optional<Client> findById(Long id);
}
