package com.example.Ticketing.Repository;

import com.example.Ticketing.Models.Project;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface ProjectRepository extends MongoRepository<Project, Long> {

    Project findByName(String name);

    boolean existsByName(String name);
}
