package com.tutorial.model.redis;

import lombok.Data;
import org.springframework.data.redis.core.RedisHash;

@Data
@RedisHash("Game")
public class GameRedis {
    private String id;
    private String title;
    private String genre;
    private String platform;
}
