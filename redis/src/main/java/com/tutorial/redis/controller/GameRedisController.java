package com.tutorial.redis.controller;

import com.tutorial.redis.model.GameRedis;
import com.tutorial.redis.service.GameRedisService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/games")
public class GameRedisController {

    private final GameRedisService gameService;

    public GameRedisController(GameRedisService gameService) {
        this.gameService = gameService;
    }

    @PostMapping
    public GameRedis saveGame(@RequestBody GameRedis game) {
        return gameService.saveGame(game);
    }

    @GetMapping("/{gameId}")
    public GameRedis getGame(@PathVariable String gameId) {
        return gameService.getGame(gameId);
    }

    @PutMapping("/{gameId}")
    public GameRedis updateGame(@PathVariable String gameId, @RequestBody GameRedis updatedGame) {
        return gameService.updateGame(gameId, updatedGame);
    }

    @DeleteMapping("/{gameId}")
    public void deleteGame(@PathVariable String gameId) {
        gameService.deleteGame(gameId);
    }
}
