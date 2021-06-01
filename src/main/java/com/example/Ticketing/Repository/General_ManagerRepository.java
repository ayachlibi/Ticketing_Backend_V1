package com.example.Ticketing.Repository;

import com.example.Ticketing.Models.Client;
import com.example.Ticketing.Models.General_Manager;
import org.springframework.data.domain.Example;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository

public interface General_ManagerRepository extends MongoRepository<General_Manager,String> {

    Optional<General_Manager> findByCostumeid(Long costumeid);

    boolean existsByCostumeid(Long costumeid);

    void deleteByCostumeid(Long costumeid);


}
