package com.example.Ticketing.Controllers.APIs;

import com.example.Ticketing.Models.CSE;
import com.example.Ticketing.Models.Client;
import com.example.Ticketing.RequestModel.CSERequestModel;
import com.example.Ticketing.RequestModel.ClientRequestModel;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

public interface CSEAPI {

    @PostMapping(value = "/CSE/RegisterCSE", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    String registerCSE(@RequestBody CSERequestModel cse);

    @PutMapping(value = "/CSE/Accept", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<?> acceptCSE(@RequestBody CSE cse);

    @DeleteMapping(value = "/CSE/Delete/{idCSE}", produces = MediaType.APPLICATION_JSON_VALUE)
    void deleteCSE(@PathVariable("idCSE") Long id);

    @PutMapping(value = "/CSE/Update", produces = MediaType.APPLICATION_JSON_VALUE)
    CSE updateCSE(@RequestBody CSE cse);

    @GetMapping(value = "/CSE/{idCSE}")
    Optional<CSE> findCSEById(@PathVariable("idCSE") Long id);

    @GetMapping(value = "/CSE/all")
    List<CSE> findAllCSEs();

}
