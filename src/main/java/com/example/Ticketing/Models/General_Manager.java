package com.example.Ticketing.Models;

import lombok.*;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;


@AllArgsConstructor

@Data

@Document(collection = "General Manager")

@EqualsAndHashCode

@Getter

@Setter

@ToString


public class General_Manager extends User implements Serializable {

    @Transient
    public static final String SEQUENCE_NAME = "General_Manager_sequence";


}
