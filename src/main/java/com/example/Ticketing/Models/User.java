package com.example.Ticketing.Models;

import com.example.Ticketing.Role.Role;
import lombok.*;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.HashSet;
import java.util.Set;

@Data

@Document (collection = "Users")

@EqualsAndHashCode

@Getter

@Setter

@ToString

@NoArgsConstructor


public class User extends AbstractEntity {

    @Transient
    public static final String SEQUENCE_NAME = "User_sequence";

    private String name;

    private String familyname;

    @Indexed(unique = true)
    private String email;

    private long phone_number;

    @Indexed(unique = true)
    private String username;

    private String password;

    @DBRef
    private Set<Role> roles= new HashSet<>();



    public User(String name,
                String familyname,
                String email,
                long phone_number,
                String username,
                String password) {
        this.name = name;
        this.familyname = familyname;
        this.email = email;
        this.phone_number = phone_number;
        this.username = username;
        this.password = password;

    }

    public void setUsername(String name, String familyname) {
        this.username = name + familyname;
    }


}
