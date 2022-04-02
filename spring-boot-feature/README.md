## Spring Boot Features

## Getting Started
This is a REST API that exposes several endpoints to allow clients to access features representing an actual image with
its associated metadata.

### Requirement

This application is built using the following technologies:

* Kotlin 1.6.10
* Spring Boot 2.6.1
* Gradle
* Jackson
* JUnit
* Mockk
* RestAssured

### Building and running the application

To build and run the application using Gradle run this command:
```
./gradlew clean build

./gradlew bootRun
```

### API Endpoint
```
GET /features

GET /features/{featureId}/quicklook
```

#### GET request to fetch a list of all features:
```
curl --location --request GET 'http://localhost:8080/features'
```

The response returns a list of all features in the following format :
```
{
"id": "39c2f29e-c0f8-4a39-a98b-deed547d6aea",
"timestamp": 1554831167697,
"beginViewingDate": 1554831167697,
"endViewingDate": 1554831202043,
"missionName": "Sentinel-1B"
}
```

#### GET request to fetch an image for the given feature id:
```
curl --location --request GET 'http://localhost:8080/features/39c2f29e-c0f8-4a39-a98b-deed547d6aea/quicklook'
```

The response returns an image/png as a content type

### Integration with IntelliJ IDEA

You can import the project into your IDE using the following steps:
```
File > New > Project from Existing Sources > {Choose directory and Gradle as Build Tool}
```
