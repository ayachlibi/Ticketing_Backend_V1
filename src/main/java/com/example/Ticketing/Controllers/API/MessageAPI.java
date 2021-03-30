package com.example.Ticketing.Controllers.API;

import com.example.Ticketing.Models.CSE;
import com.example.Ticketing.Models.Message;
import com.example.Ticketing.Models.Ticket;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

public interface MessageAPI {

    @PostMapping(value = "/Message/AddaNewMessage", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Message addMessage(@RequestBody Message message);

    @DeleteMapping(value = "/Message/Delete/{idMessage}", produces = MediaType.APPLICATION_JSON_VALUE)
    public void deleteMessage(@PathVariable("idMessage") Long id);

    @PutMapping(value = "Message/Update", produces = MediaType.APPLICATION_JSON_VALUE)
    public Message updateMessage(@RequestBody Message message);

    @GetMapping(value = "/Message/all", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Message> findAllMessages();

    @GetMapping (value = "/Message/{idMessage}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Optional<Message> findMessageById(@PathVariable("idMessage") Long id);

}
