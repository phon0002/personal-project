package com.tutorial.mongo.repo;

import com.tutorial.mongo.model.full.UserMongo;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserMongoRepository extends MongoRepository<UserMongo, String> {
    UserMongo findByUsername(String username);
}
