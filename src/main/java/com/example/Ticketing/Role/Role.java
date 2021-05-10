package com.example.Ticketing.Role;

import com.example.Ticketing.Config.SequenceGeneratorService;
import lombok.*;
import lombok.experimental.FieldNameConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Getter

@Setter

@ToString

@AllArgsConstructor

@NoArgsConstructor

@Data

@Document(collection = "Roles")

public class Role {

    @Autowired
    private SequenceGeneratorService service;

    @Transient
    public static final String SEQUENCE_NAME = "Role_sequence";

    @Id
    private Long id;

    @Enumerated(EnumType.STRING)
    private UserRole name;

    public void setId(String SEQUENCE_NAME) {
        this.id =service.generateSequence(SEQUENCE_NAME) ;
    }
}
