# Puzzle Solver API

## Purpose

This project is a web service that provides information for solving a [fifteen-puzzle](https://en.wikipedia.org/wiki/15_puzzle):

1. Is a given state of a fifteen-puzzle solvable?

2. What steps can be taken to solve the given fifteen-puzzle state?

See a web front end that uses this api: [puzzlesolver-frontend](https://joyldp.com/gogs/brandon/puzzlesolver-frontend)

## Run

### Install `dependencies/puzzlesolver-1.0.jar` into Local Repository

`./mvnw install:install-file`

### Build a Docker Image

`docker build -t puzzlesolver-api-spring-boot .`

### Run as a Docker Container

`docker run --rm --name psolver -p 4477:8080 puzzlesolver-api-spring-boot`

### Run via the Spring Boot Maven Plugin

`./mvnw spring-boot:run`

### Run via Executable Jar

`java -jar target/puzzlesolver-api-0.0.1-SNAPSHOT.jar`
