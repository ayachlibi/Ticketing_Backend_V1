package com.example.Ticketing.Repository;

import com.example.Ticketing.Models.CSE;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface CSERepository extends MongoRepository<CSE,Long> {

}
