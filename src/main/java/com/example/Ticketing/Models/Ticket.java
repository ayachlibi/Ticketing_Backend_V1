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

    private String title;

    private String description;

    private String category;

    private String type;

    private String status;

    private String priority;

    private int sla;

    private LocalDate start_Date;

    private LocalDate end_Date;

    private LocalDate deadLine;

    private LocalDate update_at;

    private Float rating;

    @DBRef(lazy = true)
    private Project project;

    private List<Message> messages;


}
