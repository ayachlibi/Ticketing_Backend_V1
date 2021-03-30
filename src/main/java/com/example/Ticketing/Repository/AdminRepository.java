package com.example.Ticketing.Repository;

import com.example.Ticketing.Models.Admin;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository


public interface AdminRepository extends MongoRepository <Admin,Long> {

}
