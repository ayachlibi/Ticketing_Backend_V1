package com.example.Ticketing.Service;

import com.example.Ticketing.Models.Message;

import java.util.List;
import java.util.Optional;

public interface MessageService {

    public Message addMessage(Message message);

    public Message modifyMessage(Message message);

    public void deleteMessage(Long id);

    public List<Message> findAll();

    public Optional<Message> findById(Long id);
}
