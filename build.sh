#!/bin/bash

./mvnw install:install-file && \
./mvnw clean package && \
docker build -t puzzlesolver-api-spring-boot .
