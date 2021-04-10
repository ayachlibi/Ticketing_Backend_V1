package com.example.Ticketing.Models;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.authority.SimpleGrantedAuthority;
//import org.springframework.security.core.userdetails.UserDetails;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Data

@Document (collection = "Users")

@NoArgsConstructor

@AllArgsConstructor

@EqualsAndHashCode

@Getter

@Setter

@ToString


public class User extends AbstractEntity  {

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

    private String role;

}
