package com.example.Ticketing.Models;

import lombok.*;
import org.apache.catalina.LifecycleState;
import org.springframework.data.annotation.Transient;
import org.springframework.data.annotation.TypeAlias;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.util.List;

@Data

@Document(collection = "Projects")

@Setter

@Getter

@ToString

@NoArgsConstructor

@AllArgsConstructor

@TypeAlias("Project")

public class Project extends AbstractEntity implements Serializable {

    @Transient
    public static final String SEQUENCE_NAME = "Project_sequence";

    private String name;

    @DBRef(lazy = true)
    private Client client;

    private CSE cse;

    private List<Ticket> tickets;

    @DBRef(lazy = true)
    private Priority priorities;

}
