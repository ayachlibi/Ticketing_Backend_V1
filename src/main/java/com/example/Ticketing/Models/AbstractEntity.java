package com.example.Ticketing.Models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.io.Serializable;
import java.time.LocalDate;

@Data

@Document

// @entitylisteners(AuditingEntityListener.class)
 public abstract class AbstractEntity implements Serializable {


    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    @Id
    @Field("my_object_id")
    private Long id;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    @CreatedDate
    private LocalDate Created_At;

    @LastModifiedDate
    private LocalDate Updated_At;


}
