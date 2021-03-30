package com.example.Ticketing.Controllers;

import com.example.Ticketing.Controllers.API.MessageAPI;
import com.example.Ticketing.Models.Message;
import com.example.Ticketing.Service.MessageService;
import com.example.Ticketing.Service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController

public class MessageController implements MessageAPI {

    //Field Injection

    private MessageService messageService;

    //Getter Injection
    @Autowired
    public MessageService getMessageService(){
        return messageService;
    }

    //Constructor Injection
    @Autowired
    public MessageController(MessageService messageService){
        this.messageService= messageService;
    }

    @Override
    public Message addMessage(Message message) {
        return messageService.addMessage(message);
    }

    @Override
    public void deleteMessage(Long id) {
        messageService.deleteMessage(id);
    }

    @Override
    public Message updateMessage(Message message) {
        return messageService.modifyMessage(message);
    }

    @Override
    public List<Message> findAllMessages() {
        return messageService.findAll();
    }

    @Override
    public Optional<Message> findMessageById(Long id) {
        return messageService.findById(id);
    }
}
