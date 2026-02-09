package com.tutorial.controller;

import com.tutorial.model.redis.GameRedis;
import com.tutorial.service.redis.GameRedisService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/games")
public class GameRedisController {

    private final GameRedisService gameService;

    public GameRedisController(GameRedisService gameService) {
        this.gameService = gameService;
    }

    // Create a new game and persist it to Redis
    @PostMapping
    public GameRedis saveGame(@RequestBody GameRedis game) {
        return gameService.saveGame(game);
    }

    // Retrieve a game by its ID, returns cached result if available
    @GetMapping("/{gameId}")
    public GameRedis getGame(@PathVariable String gameId) {
        return gameService.getGame(gameId);
    }

    // Update an existing game by its ID
    @PutMapping("/{gameId}")
    public GameRedis updateGame(@PathVariable String gameId, @RequestBody GameRedis updatedGame) {
        return gameService.updateGame(gameId, updatedGame);
    }

    // Delete a game by its ID and evict it from cache
    @DeleteMapping("/{gameId}")
    public void deleteGame(@PathVariable String gameId) {
        gameService.deleteGame(gameId);
    }
}
