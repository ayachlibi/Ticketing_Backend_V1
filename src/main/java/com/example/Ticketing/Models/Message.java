package com.example.Ticketing.Models;

import lombok.*;
import org.springframework.data.annotation.Transient;
import org.springframework.data.annotation.TypeAlias;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;


@Data

@Document(collection = "Messages")

@NoArgsConstructor

@AllArgsConstructor

@EqualsAndHashCode

@Getter

@Setter

@ToString


@TypeAlias("Message")

public class Message extends AbstractEntity implements Serializable {

    @Transient
    public static final String SEQUENCE_NAME = "Message_sequence";


    private String Description;

    @DBRef(lazy = true)
    private Ticket ticket;

    @DBRef(lazy = true)
    private User user;

}
