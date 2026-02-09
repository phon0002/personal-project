package com.tutorial.repo;

import com.tutorial.model.redis.GameRedis;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GameRedisRepository extends CrudRepository<GameRedis, String> {
}
