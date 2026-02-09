package com.tutorial.mongo.model.full;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "users")
public class UserMongo {
    @Id
    private Long id;
    private String username;
    private String email;
    private UserDetailMongo detail;
}
