package com.example.Ticketing.Repository;

import com.example.Ticketing.Models.Client;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository

public interface ClientRepository extends MongoRepository<Client,Long> {


}
