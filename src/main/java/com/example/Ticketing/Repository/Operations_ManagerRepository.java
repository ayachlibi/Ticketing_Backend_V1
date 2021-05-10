package com.example.Ticketing.Repository;

import com.example.Ticketing.Models.Operations_Manager;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface Operations_ManagerRepository extends MongoRepository <Operations_Manager,Long> {

}
