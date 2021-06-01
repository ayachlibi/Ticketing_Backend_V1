package com.example.Ticketing.Repository;

import com.example.Ticketing.Models.CSE;
import com.example.Ticketing.Models.Client;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository

public interface CSERepository extends MongoRepository<CSE,String> {

    Optional<CSE> findByCostumeid(Long costumeid);

    boolean existsByCostumeid(Long costumeid);

    void deleteByCostumeid(Long costumeid);

    boolean existsByEmail(String email);


}
