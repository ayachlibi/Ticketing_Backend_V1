package com.example.Ticketing.Services;

import com.example.Ticketing.Models.CSE;
import com.example.Ticketing.Models.Client;
import com.example.Ticketing.RequestModel.CSERequestModel;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

public interface CSEService {

    ResponseEntity<?> register(CSERequestModel cseRequestModel);

    ResponseEntity<?> accept(CSE cse);

    void delete(Long id);

    CSE update(CSE cse);

    List<CSE> findAll();

    Optional<CSE> findById(Long id);
}
