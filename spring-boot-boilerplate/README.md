## Spring Boot boilerplate in Kotlin

### Linked articles:
- [How To Create a Spring Boot Project](https://theanirban.dev/how-to-create-a-spring-boot-project/)
- [Build REST API with Spring Boot and Kotlin](https://theanirban.dev/build-rest-api-spring-boot-kotlin/)

## Getting Started
This repository contains a boilerplate code for a Spring Boot application using Kotlin.

### Requirement

This application requires Kotlin 1.4, Spring Boot 2.4 or later, Gradle and H2 Database.

### Building and running the application

To build the application run this command in the project directory:
```
gradle build
```
To start the application run this command:
```
gradle bootRun
```
The endpoints can be accessed on:
```
http://localhost:8080/
```

### Integration with IntelliJ IDEA

After cloning this repository you can import the project into your IDE using the following steps:
```
File > New > Project from Existing Sources > {Choose directory and Gradle as Build Tool}
```
