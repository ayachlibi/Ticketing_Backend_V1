package com.example.Ticketing.Repository;

import com.example.Ticketing.Models.Operations_Manager;
import org.springframework.data.mongodb.repository.MongoRepository;


public interface AdminRepository extends MongoRepository <Operations_Manager,Long> {

}
