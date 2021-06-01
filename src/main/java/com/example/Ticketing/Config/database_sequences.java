package com.example.Ticketing.Config;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data

@Document(collection = "Database_Sequence")

@Getter

@Setter

@AllArgsConstructor

@NoArgsConstructor

public class database_sequences {

    @Id
    private String id;

    private long seq;


}
