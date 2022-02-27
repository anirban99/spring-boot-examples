## Spring Boot Inventory

## Getting Started
This is a REST API that accepts CSV-formatted inventory list and discount prices as an input and returns the inventory
list with current and previous prices of the inventory.

### Requirement

This application is built using the following technologies:

* Kotlin 1.6.10
* Spring Boot 2.6.3
* Thymeleaf
* Gradle
* Jackson Dataformat CSV
* H2 Database
* Jacoco
* JUnit
* Mockito

### Building and running the application

To build and run the application using Gradle run this command:
```
./gradlew clean build

./gradlew bootRun
```

API endpoint
```
GET /cars - to provide the data from the .csv file

/errors admin endpoint that can be used for debugging
```

GET request to fetch a list of inventories:
```
curl --location --request GET 'http://localhost:8080/cars'
```

### Integration with IntelliJ IDEA

You can import the project into your IDE using the following steps:
```
File > New > Project from Existing Sources > {Choose directory and Gradle as Build Tool}
```
