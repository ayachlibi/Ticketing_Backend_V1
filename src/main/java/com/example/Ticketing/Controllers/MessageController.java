package com.example.Ticketing.Controllers;

import com.example.Ticketing.Controllers.APIs.MessageAPI;
import com.example.Ticketing.Models.Message;
import com.example.Ticketing.Services.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
public class MessageController implements MessageAPI {

    private MessageService messageService;

    @Autowired
    public MessageService getMessageService() {
        return messageService;
    }

    @Autowired
    public MessageController(MessageService messageService) {
        this.messageService = messageService;
    }

    @Override
    public Message saveMessage(Message message) {
        return messageService.save(message);
    }

    @Override
    public void deleteMessage(Long id) {
        messageService.delete(id);
    }


    @Override
    public Optional<Message> findMessageById(Long id) {
        return messageService.findById(id);
    }

    @Override
    public List<Message> findAllMessages() {
        return messageService.findAll();
    }
}
