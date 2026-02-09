package com.tutorial.mongo.service;

import com.tutorial.mongo.model.full.UserMongo;
import com.tutorial.mongo.repo.UserMongoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserMongoService {

    private final UserMongoRepository userMongoRepository;

    public UserMongoService(UserMongoRepository userMongoRepository) {
        this.userMongoRepository = userMongoRepository;
    }

    public List<UserMongo> getAllUsers() {
        return userMongoRepository.findAll();
    }

    public UserMongo getUserById(String id) {
        return userMongoRepository.findById(id).orElse(null);
    }

    public UserMongo getUserByUsername(String username) {
        return userMongoRepository.findByUsername(username);
    }

    public UserMongo saveUser(UserMongo user) {
        return userMongoRepository.save(user);
    }

    public void deleteUser(String id) {
        userMongoRepository.deleteById(id);
    }
}
