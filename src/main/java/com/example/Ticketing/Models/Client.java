package com.example.Ticketing.Models;

import com.example.Ticketing.Role.UserRole;
import lombok.*;
import org.springframework.data.annotation.Transient;
import org.springframework.data.annotation.TypeAlias;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.util.List;

@Data

@Document(collection = "Clients")

@NoArgsConstructor

@AllArgsConstructor

@EqualsAndHashCode

@Getter

@Setter

@ToString

@TypeAlias("Client")

public class Client extends User implements Serializable {

    @Transient
    public static final String SEQUENCE_NAME = "Client_sequence";

    private String enterprise;

    private boolean accepted = false ;

    @DBRef(lazy = true)
    private List<Project> projects;

    public Client(String firstname,
                  String lastname,
                  String email,
                  String phone_number,
                  String username,
                  String password,
                  UserRole role,
                  String enterprise) {
        super(firstname, lastname, email, phone_number, username, password, role);
        this.enterprise = enterprise;

    }
}
