package com.example.Ticketing.Repository;

import com.example.Ticketing.Models.Client;
import com.example.Ticketing.Models.Priority;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PriorityRepository extends MongoRepository<Priority, String> {

    Optional<Priority> findByCostumeid(Long costumeid);

    boolean existsByCostumeid(Long costumeid);

    void deleteByCostumeid(Long costumeid);

}
