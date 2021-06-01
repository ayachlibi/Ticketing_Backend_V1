package com.example.Ticketing.Repository;

import com.example.Ticketing.Models.Client;
import com.example.Ticketing.Models.Project;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository

public interface ProjectRepository extends MongoRepository<Project, String> {

    Project findByName(String name);

    boolean existsByName(String name);

    Optional<Project> findByCostumeid(Long costumeid);

    boolean existsByCostumeid(Long costumeid);

    void deleteByCostumeid(Long costumeid);

}
