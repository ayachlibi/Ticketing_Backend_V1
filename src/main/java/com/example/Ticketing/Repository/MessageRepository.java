package com.example.Ticketing.Repository;

import com.example.Ticketing.Models.Message;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface MessageRepository extends MongoRepository<Message,Long> {
}
