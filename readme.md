# Pokemon Service

## Requirements

For building and running the application you need:

- [JDK 1.8](http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html)
- [Gradle 7.3](https://gradle.org)

## Running the application locally

### Using an IDE
There are several ways to run a Spring Boot application on your local machine. One way is to execute the `main` method in the `io.app.pokedexservice.PokedexServiceApplication` class from your IDE.

### Using gradlew command from shell
Alternatively you can use the below command [Run Springboot application using gradle](https://spring.io/guides/gs/spring-boot/) like so:
```shell
./gradlew bootRun
```

### Running the Jar
Alternatively you can use the below command [Run Springboot application using gradle](https://spring.io/guides/gs/spring-boot/) like so:

```shell
java -jar D:\learning\springboot\pokedex-service\build\libs\pokedex-service-0.0.1-SNAPSHOT.jar
```

Tomcat server starts on port : ```5000```. Update ```server.port``` property of **Application.properties** file for changing the port number.
## Copyright

Released under the Apache License 2.0. See the [LICENSE](https://github.com/codecentric/springboot-sample-app/blob/master/LICENSE) file.