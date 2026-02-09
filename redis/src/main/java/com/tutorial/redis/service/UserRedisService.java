package com.tutorial.redis.service;

import com.tutorial.redis.model.UserRedis;
import com.tutorial.redis.repo.UserRedisRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class UserRedisService {

    @Autowired
    private UserRedisRepository userRepository;

    public void saveUser(UserRedis user) {
        userRepository.save(user);
    }

    @Cacheable(value = "users", key = "#userId")
    public UserRedis getUser(String userId) {
        return userRepository.findById(userId).orElse(null);
    }

    @CachePut(value="users")
    public UserRedis getUser2(String userId) {
        return userRepository.findById(userId).orElse(null);
    }

    @CacheEvict(value = "users", key = "#userId")
    public void evictUserCache(String userId) {
    }

    @CacheEvict(value = "users", allEntries = true)
    @Scheduled(fixedRateString = "3600")
    public void emptyUsersCache() {
    }

    public void updateUser(UserRedis updatedUser) {
        userRepository.save(updatedUser);
        evictUserCache(updatedUser.getId());
    }
}
