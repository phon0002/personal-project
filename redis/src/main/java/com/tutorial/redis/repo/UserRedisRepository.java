package com.tutorial.redis.repo;

import com.tutorial.redis.model.UserRedis;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRedisRepository extends CrudRepository<UserRedis, String> {
}