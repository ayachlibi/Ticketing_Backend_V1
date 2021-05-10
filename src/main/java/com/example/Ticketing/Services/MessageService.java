package com.example.Ticketing.Services;

import com.example.Ticketing.Models.Message;

import java.util.List;
import java.util.Optional;

public interface MessageService {
    Message save(Message message);

    Message update(Message message);

    void delete(Long id);

    Optional<Message> findById(Long id);

    List<Message> findAll();
}
