package com.tutorial.service.redis;

import com.tutorial.model.redis.GameRedis;
import com.tutorial.repo.GameRedisRepository;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
public class GameRedisService {

    private final GameRedisRepository gameRepository;

    public GameRedisService(GameRedisRepository gameRepository) {
        this.gameRepository = gameRepository;
    }

    public GameRedis saveGame(GameRedis game) {
        return gameRepository.save(game);
    }

    @Cacheable(value = "games", key = "#gameId")
    public GameRedis getGame(String gameId) {
        return gameRepository.findById(gameId).orElse(null);
    }

    public GameRedis updateGame(String gameId, GameRedis updatedGame) {
        updatedGame.setId(gameId);
        return gameRepository.save(updatedGame);
    }

    @CacheEvict(value = "games", key = "#gameId")
    public void deleteGame(String gameId) {
        gameRepository.deleteById(gameId);
    }
}
