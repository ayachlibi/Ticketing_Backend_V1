package com.example.Ticketing.Repository;

import com.example.Ticketing.Models.Priority;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PriorityRepository extends MongoRepository<Priority, Long> {

}
