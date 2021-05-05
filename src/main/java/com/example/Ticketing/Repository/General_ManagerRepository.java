package com.example.Ticketing.Repository;

import com.example.Ticketing.Models.General_Manager;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface SuperAdminRepository extends MongoRepository<General_Manager,Long> {

}
