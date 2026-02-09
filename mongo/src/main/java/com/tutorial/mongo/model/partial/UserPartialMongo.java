package com.tutorial.mongo.model.partial;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "users-partial")
@Data
public class UserPartialMongo {
    @Id
    private Long id;
    private String username;
    private String email;

    @DBRef
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private UserPartialDetailMongo detail;
}
