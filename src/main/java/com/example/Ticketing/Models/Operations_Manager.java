package com.example.Ticketing.Models;

import lombok.*;
import org.springframework.data.annotation.Transient;
import org.springframework.data.annotation.TypeAlias;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.util.List;


@Data

@Document(collection = "Admins")

@NoArgsConstructor

@AllArgsConstructor

@EqualsAndHashCode

@Getter

@Setter

@ToString


@TypeAlias("Operations_Manager")

public class Operations_Manager extends User implements Serializable {

    @Transient
    public static final String SEQUENCE_NAME = "Operations_Manager_sequence";

    private List<CSE> cses;


}
