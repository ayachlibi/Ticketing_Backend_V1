package com.example.Ticketing.Repository;

import com.example.Ticketing.Models.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface UserRepository extends MongoRepository<User,Long> {


    public User findByUsername(String username);


    public User findByEmail(String email);

    public boolean existsByUsername (String username);

    public boolean existsByEmail (String email);

    public boolean existsByUsernameAndPassword(String username,String password);
}
