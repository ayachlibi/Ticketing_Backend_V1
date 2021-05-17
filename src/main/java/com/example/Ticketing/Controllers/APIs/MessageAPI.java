package com.example.Ticketing.Controllers.APIs;

import com.example.Ticketing.Models.Message;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

public interface MessageAPI {

    @PostMapping(value = "/Message/AddMessage", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    Message saveMessage(@RequestBody Message message);

    @DeleteMapping(value = "/Message/Delete/{idMessage}", produces = MediaType.APPLICATION_JSON_VALUE)
    void deleteMessage(@PathVariable("idMessage") Long id);

    @GetMapping(value = "/Message/{idMessage}")
    Optional<Message> findMessageById(@PathVariable("idMessage") Long id);

    @GetMapping(value = "/Message/all")
    List<Message> findAllMessages();



}
