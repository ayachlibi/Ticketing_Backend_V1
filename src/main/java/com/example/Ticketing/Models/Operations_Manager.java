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

@Document(collection = "Operations Managers")

@NoArgsConstructor


@EqualsAndHashCode

@Getter

@Setter

@ToString


@TypeAlias("Operations_Manager")

public class Operations_Manager extends User implements Serializable {

    @Transient
    public static final String SEQUENCE_NAME = "Operations_Manager_sequence";

    public Operations_Manager(String firstname,
                              String lastname,
                              String email,
                              String phone_number,
                              String username,
                              String password,
                              UserRole role) {
        super(firstname, lastname, email, phone_number, username, password, role);
    }
}
