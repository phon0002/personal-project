# CLAUDE.md

This file provides guidance to Claude Code (claude.ai/code) when working with code in this repository.

## Project Overview

Spring Boot 2.6.4 tutorial/playground project (Java 16, Gradle) exploring multiple data stores and messaging. The application integrates Redis, MongoDB, DynamoDB, Kafka, and MySQL with Spring Boot starters.

## Common Commands

```bash
# Build
./gradlew build                    # Full build (compiles, runs OpenAPI generator, tests)
./gradlew compileJava              # Compile only (triggers OpenAPI code generation first)
./gradlew clean build              # Clean rebuild

# Testing
./gradlew test                     # Run all tests (JUnit Platform)
./gradlew test --tests "com.tutorial.TutorialApplicationTests"  # Run a single test class

# Run
./gradlew bootRun                  # Start Spring Boot application

# Infrastructure (required for local development)
docker-compose up -d               # Start all backing services (MySQL, Redis, DynamoDB, Kafka, Zookeeper)
docker-compose up -d redis kafka zookeeper  # Start only specific services
```

## Architecture

Spring Boot application with multi-data-store integration:

- **Entry point:** `src/main/java/com/tutorial/TutorialApplication.java` — `@SpringBootApplication` with `@EnableCaching` and `@EnableAsync`, configures a thread pool executor (core=2, max=2, queue=500)
- **Configuration:** `src/main/java/com/tutorial/AppConfig.java` — `@ComponentScan("com.tutorial")`
- **Application config:** `src/main/resources/application.yml` — Kafka consumer settings, Resilience4j retry config

### Data Layer Organization

Models are organized by data store under `src/main/java/com/tutorial/model/`:
- `redis/UserRedis.java` — `@RedisHash("User")`
- `mongo/full/` and `mongo/partial/` — MongoDB documents for `users` and `users-partial` collections
- `UserDB.java` — DynamoDB table entity (`@DynamoDBTable`)
- `jpa/partial/` — JPA entities (placeholder)

### Key Integrations

- **Redis:** `RedisConfig` with LettuceConnectionFactory, named caches (`trackCache` 10min, `customerCache` 5min, default 60min). `UserRedisService` demonstrates `@Cacheable`, `@CachePut`, `@CacheEvict`, and scheduled cache eviction.
- **Kafka:** Consumer configured for `localhost:29092`, Kafka Streams support. `Util` class has helpers for topic creation and a `Randomizer` that publishes fake data.
- **MongoDB:** Repositories extend `MongoRepository` with custom queries.
- **Resilience4j:** Retry instance `hello-retry` configured with exponential backoff (3 attempts, 1s wait, 2x multiplier).

### OpenAPI Code Generation

The build generates Spring API interfaces from `src/main/resources/Helloworld.yaml` into `build/gen/`. The `compileJava` task depends on `openApiGenerate`.

## Infrastructure

`docker-compose.yml` provides all backing services:
- MySQL 8.0 on port 3306 (database: `gogz`, initialized from `db/createDatabase.sql`)
- DynamoDB Local on port 8000
- Redis on port 6379
- Redis Stack (management UI) on ports 6380/8001
- Zookeeper on port 2181, Kafka on port 9092

## Key Dependencies

- Lombok for boilerplate reduction (`@Data`, etc.)
- javafaker for test data generation
- springdoc-openapi-ui for API documentation
- Spring Cloud Resilience4j for circuit breaking/retry
- Log4j2 for logging (default Spring Boot logging excluded)
