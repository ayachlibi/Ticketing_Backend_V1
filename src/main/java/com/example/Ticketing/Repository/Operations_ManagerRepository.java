package com.example.Ticketing.Repository;

import com.example.Ticketing.Models.Client;
import com.example.Ticketing.Models.Operations_Manager;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository

public interface Operations_ManagerRepository extends MongoRepository <Operations_Manager,String> {

    Optional<Operations_Manager> findByCostumeid(Long costumeid);

    boolean existsByCostumeid(Long costumeid);

    void deleteByCostumeid(Long costumeid);

}
