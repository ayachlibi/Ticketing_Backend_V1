package com.example.Ticketing.Models;

import lombok.*;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;


@AllArgsConstructor

@Data

@Document(collection = "Super_Admins")

@EqualsAndHashCode

@Getter

@Setter

@ToString



public class SuperAdmin extends User implements Serializable {

    @Transient
    public static final String SEQUENCE_NAME = "SuperAdmin_sequence";


}
