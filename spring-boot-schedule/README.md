## Spring Boot Scheduler

## Getting Started
This is a REST API that accepts JSON-formatted opening hours of a restaurant as an input and returns the rendered
human-readable format as a text output.

### Requirement

This application is built using the following technologies:

* Kotlin 1.5.10
* Spring Boot 2.6.3
* Gradle
* Jackson
* Jacoco
* JUnit
* Mockito

### Building and running the application

To build and run the application using Gradle run this command:
```
./gradlew clean build

./gradlew bootRun
```

API Endpoint 
```
POST /restaurants
```

POST request to create a human-readable version of the opening hours data:
```
curl --location --request POST 'http://localhost:8080/restaurants' \
--header 'Content-Type: application/json' \
--data-raw '{
  "monday" : [],
  "tuesday" : [
    {
      "type" : "open",
      "value" : 36000
    },
    {
      "type" : "close",
      "value" : 64800
    }
  ],
  "wednesday" : [],
  "thursday" : [
    {
      "type" : "open",
      "value" : 37800
    },
    {
      "type" : "close",
      "value" : 64800
    }
  ],
  "friday" : [
    {
      "type" : "open",
      "value" : 36000
    }
  ],
  "saturday" : [
    {
      "type" : "close",
      "value" : 3600
    },
    {
      "type" : "open",
      "value" : 36000
    }
  ],
  "sunday" : [
    {
      "type" : "close",
      "value" : 3600
    },
    {
      "type" : "open",
      "value" : 43200
    },
    {
      "type" : "close",
      "value" : 75600
    }
  ]
}'
```

Response from the POST /restaurants request:
```
Monday: Closed
Tuesday: 10 AM - 6 PM
Wednesday: Closed
Thursday: 10:30 AM - 6 PM
Friday: 10 AM - 1 AM
Saturday: 10 AM - 1 AM
Sunday: 12 PM - 9 PM
```


### Integration with IntelliJ IDEA

You can import the project into your IDE using the following steps:
```
File > New > Project from Existing Sources > {Choose directory and Gradle as Build Tool}
```
