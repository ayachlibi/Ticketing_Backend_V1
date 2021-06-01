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

@Document(collection = "CSEs")

@NoArgsConstructor

@AllArgsConstructor

@EqualsAndHashCode

@Getter

@Setter

@ToString

@TypeAlias("CSE")

public class CSE extends User implements Serializable {

    @Transient
    public static final String SEQUENCE_NAME = "CSE_sequence";

    private boolean accepted = false;

    private List<Project> projects;

    public CSE(String firstname,
               String lastname,
               String email,
               String phone_number,
               String username,
               String password,
               UserRole role) {
        super(firstname, lastname, email, phone_number, username, password, role);

    }
}
