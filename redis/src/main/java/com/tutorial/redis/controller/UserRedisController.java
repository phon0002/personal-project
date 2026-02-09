package com.tutorial.redis.controller;

import com.tutorial.redis.model.UserRedis;
import com.tutorial.redis.service.UserRedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserRedisController {

    private final UserRedisService userService;

    @Autowired
    public UserRedisController(UserRedisService userService) {
        this.userService = userService;
    }

    @PostMapping
    public void saveUser(@RequestBody UserRedis user) {
        userService.saveUser(user);
    }

    @GetMapping("/{userId}")
    public UserRedis getUser(@PathVariable String userId) {
        return userService.getUser(userId);
    }

    @PutMapping("/{userId}")
    public void updateUser(@PathVariable String userId, @RequestBody UserRedis updatedUser) {
    }

    @DeleteMapping("/{userId}")
    public void deleteUser(@PathVariable String userId) {
    }
}
