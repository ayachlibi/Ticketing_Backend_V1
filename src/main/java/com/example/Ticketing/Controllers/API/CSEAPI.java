package com.example.Ticketing.Controllers.API;


import com.example.Ticketing.Models.CSE;
import com.example.Ticketing.Models.Client;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

public interface CSEAPI {

    @PostMapping(value = "/CSE/AddaNewCSE", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public CSE saveCSE(@RequestBody CSE cse);

    @DeleteMapping(value = "/CSE/Delete/{idCSE}", produces = MediaType.APPLICATION_JSON_VALUE)
    public void deleteCSE(@PathVariable("idCSE") Long id);

    @PutMapping(value = "CSE/Update", produces = MediaType.APPLICATION_JSON_VALUE)
    public CSE updateCSE(@RequestBody CSE cse);

    @GetMapping(value = "CSE/{idCSE}")
    public Optional<CSE> findCSEById(@PathVariable("idCSE") Long id);

    @GetMapping(value = "CSE/all")
    public List<CSE> findAllCSEs();

}
