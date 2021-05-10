package com.example.Ticketing.Repository;

import com.example.Ticketing.Models.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository

public interface UserRepository extends MongoRepository<User,Long> {

    Optional<User> findByUsername(String username);

    User findByEmail(String email);

    boolean existsByUsername (String username);

    boolean existsByEmail (String email);

    boolean existsByUsernameAndPassword(String username,String password);

    User findByUsernameAndPassword (String username, String password);

    @Transactional
    @Query("UPDATE AppUser a " +
            "SET a.enabled = TRUE WHERE a.email = ?1")
    int enableUser(String email);
}
