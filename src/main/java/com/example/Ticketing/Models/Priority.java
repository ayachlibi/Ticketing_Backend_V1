package com.example.Ticketing.Models;


import lombok.*;
import org.springframework.data.annotation.Transient;
import org.springframework.data.annotation.TypeAlias;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;

@Data

@Document(collection = "Priorities")

@Setter

@Getter

@AllArgsConstructor

@NoArgsConstructor

@TypeAlias("Priority")

public class Priority extends AbstractEntity implements Serializable {

    @Transient
    public static final String SEQUENCE_NAME = "Priority_sequence";

    private Long sla_very_high;

    private Long sla_high;

    private Long sla_medium;

    private Long sla_low;

    @DBRef(lazy = true)
    private Project project;
}
