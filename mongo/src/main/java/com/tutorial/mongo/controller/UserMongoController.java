package com.tutorial.mongo.controller;

import com.tutorial.mongo.model.full.UserMongo;
import com.tutorial.mongo.service.UserMongoService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/mongo/users")
public class UserMongoController {

    private final UserMongoService userMongoService;

    public UserMongoController(UserMongoService userMongoService) {
        this.userMongoService = userMongoService;
    }

    @GetMapping
    public List<UserMongo> getAllUsers() {
        return userMongoService.getAllUsers();
    }

    @GetMapping("/{id}")
    public UserMongo getUserById(@PathVariable String id) {
        return userMongoService.getUserById(id);
    }

    @PostMapping
    public UserMongo saveUser(@RequestBody UserMongo user) {
        return userMongoService.saveUser(user);
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable String id) {
        userMongoService.deleteUser(id);
    }
}
