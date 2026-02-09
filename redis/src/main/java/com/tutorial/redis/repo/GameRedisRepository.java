package com.tutorial.redis.repo;

import com.tutorial.redis.model.GameRedis;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GameRedisRepository extends CrudRepository<GameRedis, String> {
}