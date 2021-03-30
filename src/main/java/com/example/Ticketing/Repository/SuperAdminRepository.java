package com.example.Ticketing.Repository;

import com.example.Ticketing.Models.SuperAdmin;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface SuperAdminRepository extends MongoRepository<SuperAdmin,Long> {

}
