package com.example.Ticketing.Models;

import lombok.*;
import org.springframework.data.annotation.Transient;
import org.springframework.data.annotation.TypeAlias;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

@Data

@Document(collection = "Tickets")

@NoArgsConstructor

@AllArgsConstructor

@EqualsAndHashCode

@Getter

@Setter

@ToString



@TypeAlias("Ticket")
public class Ticket extends AbstractEntity implements Serializable {


    @Transient
    public static final String SEQUENCE_NAME = "Ticket_sequence";

    private String Title;

    private String Description;

    private String Category;

    private String Type;

    private String Status;

    private String Priority ;

    private boolean SLA;

    private LocalDate Start_Date;

    private LocalDate End_Date;

    private LocalDate DeadLine;

    private Float Rating;

    @DBRef(lazy = true)
    private Client client;

    @DBRef(lazy = true)
    private CSE cse;

    private List<Message> messages;


}
