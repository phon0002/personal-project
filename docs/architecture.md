# Architecture Diagram

## High-Level Overview

```mermaid
graph TB
    HTTP["HTTP Clients"]

    subgraph SpringBootApplication["Spring Boot Application"]
        REDIS_SUB["Redis Sub-System"]
        MONGO_SUB["MongoDB Sub-System"]
        MYSQL_SUB["MySQL Sub-System"]
        DYNAMO_SUB["DynamoDB Sub-System"]
        KAFKA_SUB["Kafka Sub-System"]
        SHARED["Shared Configuration"]
    end

    subgraph Infrastructure["Infrastructure - Docker"]
        REDIS[("Redis :6379")]
        MONGO[("MongoDB :27017")]
        MYSQL[("MySQL :3306")]
        DYNAMO[("DynamoDB :8000")]
        KAFKA["Kafka :9092"]
    end

    HTTP --> REDIS_SUB
    HTTP --> MONGO_SUB
    HTTP --> MYSQL_SUB

    REDIS_SUB --> REDIS
    MONGO_SUB --> MONGO
    MYSQL_SUB --> MYSQL
    DYNAMO_SUB --> DYNAMO
    KAFKA_SUB --> KAFKA
```

---

## Redis Sub-System

```mermaid
graph TB
    HTTP["HTTP Clients"]

    subgraph RedisSubSystem["Redis Sub-System"]
        subgraph Controllers
            URC["UserRedisController /users"]
            GRC["GameRedisController /games"]
        end

        subgraph Services
            URS["UserRedisService - Cacheable, CacheEvict, CachePut"]
            GRS["GameRedisService - Cacheable, CacheEvict"]
        end

        subgraph Repositories
            URR["UserRedisRepository - CrudRepository"]
            GRR["GameRedisRepository - CrudRepository"]
        end

        subgraph Models
            UR["UserRedis - RedisHash"]
            GR["GameRedis - RedisHash"]
        end

        subgraph Config
            RC["RedisConfig - LettuceConnectionFactory"]
            CM["RedisCacheManager - trackCache, customerCache, default"]
        end
    end

    subgraph Infrastructure
        REDIS[("Redis :6379")]
        REDIS_STACK[("Redis Stack UI :8001")]
    end

    HTTP --> URC
    HTTP --> GRC

    URC --> URS
    GRC --> GRS

    URS --> URR
    GRS --> GRR

    URR --> UR
    GRR --> GR

    URS -.->|cache| CM
    GRS -.->|cache| CM

    RC --> REDIS
    CM --> REDIS
    URR --> REDIS
    GRR --> REDIS
    REDIS_STACK -.-> REDIS
```

---

## MongoDB Sub-System

```mermaid
graph TB
    subgraph MongoSubSystem["MongoDB Sub-System"]
        subgraph Repositories
            UMR["UserMongoRepository - MongoRepository"]
        end

        subgraph FullModels["Full Document Models"]
            UM["UserMongo - collection: users"]
            UDM["UserDetailMongo - embedded"]
        end

        subgraph PartialModels["Partial Document Models"]
            UPM["UserPartialMongo - collection: users-partial"]
            UPDM["UserPartialDetailMongo - DBRef"]
        end
    end

    subgraph Infrastructure
        MONGO[("MongoDB :27017")]
    end

    UMR --> UM
    UM --> UDM
    UPM --> UPDM
    UMR --> MONGO
```

---

## MySQL Sub-System

```mermaid
graph TB
    subgraph MySQLSubSystem["MySQL Sub-System"]
        subgraph Repositories
            UMYSQLR["UserMysqlRepository - JpaRepository"]
        end

        subgraph Models
            UMYSQL["UserMysql - JPA Entity"]
        end

        subgraph Schema["Persons Table"]
            PID["PersonID - int, PK"]
            LN["LastName - varchar 255"]
            FN["FirstName - varchar 255"]
            ADDR["Address - varchar 255"]
            CITY["City - varchar 255"]
        end
    end

    subgraph Infrastructure
        MYSQL[("MySQL 8.0 :3306 - gogz DB")]
    end

    UMYSQLR --> UMYSQL
    UMYSQL --> Schema
    UMYSQLR --> MYSQL
```

---

## DynamoDB Sub-System

```mermaid
graph TB
    subgraph DynamoSubSystem["DynamoDB Sub-System"]
        subgraph Models
            UDB["UserDB - DynamoDBTable"]
        end
    end

    subgraph Infrastructure
        DYNAMO[("DynamoDB Local :8000")]
    end

    UDB -.-> DYNAMO
```

---

## Kafka Sub-System

```mermaid
graph TB
    subgraph KafkaSubSystem["Kafka Sub-System"]
        subgraph Utilities
            UTIL["Util - TopicCreator"]
            RAND["Randomizer - Fake Data Producer"]
        end
    end

    subgraph Infrastructure
        KAFKA["Kafka :9092"]
        ZK["Zookeeper :2181"]
    end

    UTIL --> KAFKA
    RAND --> KAFKA
    KAFKA --> ZK
```

---

## Shared Configuration

```mermaid
graph TB
    subgraph SharedConfig["Shared Configuration"]
        TA["TutorialApplication - EnableCaching, EnableAsync"]
        AC["AppConfig - ComponentScan"]
        R4J["Resilience4j hello-retry - 3 attempts, exp backoff"]
        OAG["OpenAPI Generator - Helloworld.yaml"]
    end

    TA --> AC
    TA --> R4J
    TA --> OAG
```

---

## Data Flow - Redis Caching

```mermaid
sequenceDiagram
    participant C as Client
    participant Ctrl as Controller
    participant Svc as Service
    participant Cache as Redis Cache
    participant Repo as Repository
    participant DB as Redis Store

    Note over C,DB: GET /games/{id} — Cache Hit
    C->>Ctrl: GET /games/1
    Ctrl->>Svc: getGame("1")
    Svc->>Cache: lookup games::1
    Cache-->>Svc: cached result
    Svc-->>Ctrl: GameRedis
    Ctrl-->>C: 200 OK

    Note over C,DB: GET /games/{id} — Cache Miss
    C->>Ctrl: GET /games/2
    Ctrl->>Svc: getGame("2")
    Svc->>Cache: lookup games::2
    Cache-->>Svc: miss
    Svc->>Repo: findById("2")
    Repo->>DB: HGETALL Game:2
    DB-->>Repo: data
    Repo-->>Svc: GameRedis
    Svc->>Cache: store games::2
    Svc-->>Ctrl: GameRedis
    Ctrl-->>C: 200 OK

    Note over C,DB: DELETE /games/{id} — Cache Eviction
    C->>Ctrl: DELETE /games/1
    Ctrl->>Svc: deleteGame("1")
    Svc->>Cache: evict games::1
    Svc->>Repo: deleteById("1")
    Repo->>DB: DEL Game:1
    Ctrl-->>C: 200 OK
```

---

## Component Summary

| Sub-System | Component | Data Store | Port |
|------------|-----------|------------|------|
| Redis | UserRedisController `/users` | Redis | 6379 |
| Redis | GameRedisController `/games` | Redis | 6379 |
| Redis | RedisCacheManager | Redis | 6379 |
| MongoDB | UserMongoRepository | MongoDB | 27017 |
| MySQL | UserMysqlRepository | MySQL | 3306 |
| DynamoDB | UserDB | DynamoDB Local | 8000 |
| Kafka | Util / Randomizer | Kafka | 9092 |
