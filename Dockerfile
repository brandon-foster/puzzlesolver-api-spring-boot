FROM openjdk:11
WORKDIR /usr/src/myapp
COPY ./target/puzzlesolver-api-0.0.1-SNAPSHOT.jar ./
CMD ["java", "-jar", "puzzlesolver-api-0.0.1-SNAPSHOT.jar"]
