package com.example.Ticketing.Models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.MongoId;

import java.io.Serializable;
import java.time.LocalDate;

@Data

@Document

// @entitylisteners(AuditingEntityListener.class)
 public abstract class AbstractEntity implements Serializable {


    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    @Id
    @Field("mongo_id")
    private String mongoid;

   @JsonProperty(access = JsonProperty.Access.READ_ONLY)
   private Long costumeid;

   @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    @CreatedDate
    private LocalDate Created_At;

    @LastModifiedDate
    private LocalDate Updated_At;


}
