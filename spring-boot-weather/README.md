## Spring Boot Weather

## Getting Started
This is a weather retrieval service that exposes an endpoint for current temperature retrieval for a city by user’s
choice. Temperature should be returned in 3 formats: Celsius, Fahrenheit, Kelvin.

### Requirement

This application is built using the following technologies:

* Kotlin 1.6.21
* Spring Boot 2.6.7
* Gradle
* Jackson

### Building and running the application

To build and run the application using Gradle run this command:
```
./gradlew clean build

./gradlew bootRun
```

### API Endpoint
```
GET /temperatures/{city}
```

#### GET request to fetch the current temperature of a city:
```
curl --location --request GET 'http://localhost:8080/temperatures/Berlin'
```

The response returns a list of all features in the following format :
```
{
  "celsius": 22.02,
  "fahrenheit": 71.636,
  "kelvin": 295.17
}
```

### Integration with IntelliJ IDEA

You can import the project into your IDE using the following steps:
```
File > New > Project from Existing Sources > {Choose directory and Gradle as Build Tool}
```
