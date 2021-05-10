package com.example.Ticketing.Repository;

import com.example.Ticketing.Models.Ticket;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import org.springframework.ui.Model;

import java.util.List;

@Repository

public interface TicketRepository extends MongoRepository<Ticket,Long> {

}
