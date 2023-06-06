### Wind Power Park Service

### Getting Started

This is a REST API service for exposing and correcting power data. On a frequent basis we aggregate data from our
wind power parks informing us how much power was produced at a certain plant in a given time frame. This data is then
further used on algorithms to provide value to our customers.The data we receive can potentially contain errors
(e.g. malfunctioning sensors, errors in monitoring systems), hence we’d like the ability to manually overwrite certain
data points in the process.

The `powerProduced.json` file is used as our “in-memory database” to build:

1. A REST endpoint exposing data points (with their potentially corrected values) from all wind parks
2. A REST endpoint exposing data points of a given wind park
3. A REST endpoint to allow manual correction of the power produced of a given wind park
4. A REST endpoint to allow manual deletion of the data points of a given wind park

### Requirement

This application is built using the following technologies:

* Kotlin 1.7.21
* Java 11
* Spring Boot 2.7.6
* Spring Data JPA
* Gradle
* Jackson
* H2 Database
* JUnit 5
* Mockk
* RestAssured
* Jacoco
* Docker
* Bash
* OpenAPI
* Swagger UI

### Building and running the application

To build and run the application using gradle and docker-compose run this command:
```
./gradlew clean build

docker-compose up --build
```

To build and run the application using gradle and Docker image run this command:
```
./gradlew clean build

docker build --tag=spring-boot-power:latest .

docker run -d --add-host host.docker.internal:host-gateway -p 8080:8080 spring-boot-power:latest
```

To build and run the application using Bash script run this command:
```
bash run.sh
```

To build and run the application using gradle run this command:
```
./gradlew clean build

./gradlew bootRun
```

The H2 database console is available at ```http://localhost:8080/h2-console```, which will present us with a login page.
On the login page, we need provide the same credentials that we used in the ```application.yml```

### REST APIs
```
GET /wind-parks

GET /wind-parks/{id}

PATCH /wind-parks/{id}

DELETE /wind-parks/{id}
```

### API Documentation

The OpenAPI UI is available under the url: ```http://localhost:8080/swagger-ui/index.html```

Note: If the API descriptions doesn't show up in the above url, search using `/v3/api-docs` in the Explore option.

The OpenAPI descriptions is available under the url: ```http://localhost:8080/v3/api-docs/```

### GET request to fetch a list of all wind parks:
```
curl --location 'localhost:8080/wind-parks'
```
The response returns a list of all wind parks in the following format :
```
[
    {
        "id": "aff8c6b2-3919-4847-b41d-9c4182832841",
        "windPark": "DKX",
        "powerProduced": 6.75,
        "period": "30m",
        "timestamp": "2020-05-17T00:00:00Z",
        "createdOn": "2020-05-20T13:17:00Z",
        "updatedOn": "2020-05-20T13:17:00Z"
    },
    {
        "id": "20e1cb09-5403-4bed-86f4-19da202f2dbe",
        "windPark": "PPA",
        "powerProduced": 6.88,
        "period": "30m",
        "timestamp": "2020-05-17T00:30:00Z",
        "createdOn": "2020-05-20T13:17:00Z",
        "updatedOn": "2020-05-20T13:17:00Z"
    }
]    
```

### GET request to fetch the data points of the given wind park:
```
curl --location 'http://localhost:8080/wind-parks/6270b6f0-e966-4d15-85e8-f200a1adfde7'
```
The response returns the data points of wind park in the following format :
```
{
    "id": "6270b6f0-e966-4d15-85e8-f200a1adfde7",
    "windPark": "QMA",
    "powerProduced": 3.58,
    "period": "30m",
    "timestamp": "2020-05-17T07:00:00Z",
    "createdOn": "2020-05-20T13:17:00Z",
    "updatedOn": "2020-05-20T13:17:00Z"
}
```

### PATCH request to update the power produced of a given wind park:
```
curl --location --request PATCH 'http://localhost:8080/wind-parks/6270b6f0-e966-4d15-85e8-f200a1adfde7' \
--header 'Content-Type: application/json' \
--data '{
  "operation": "update",
  "key": "powerProduced",
  "value": 9.75
}'
```
The response returns the updated data points of wind park in the following format :
```
{
    "id": "6270b6f0-e966-4d15-85e8-f200a1adfde7",
    "windPark": "QMA",
    "powerProduced": 9.75,
    "period": "30m",
    "timestamp": "2020-05-17T07:00:00Z",
    "createdOn": "2020-05-20T13:17:00Z",
    "updatedOn": "2023-05-01T09:10:00Z"
}
```

### DELETE request to delete the data points of the given wind park:
````
curl --location --request DELETE 'http://localhost:8080/wind-parks/aff8c6b2-3919-4847-b41d-9c4182832841'
````

### Integration with IntelliJ IDEA

You can import the project into your IDE using the following steps:
```
File > New > Project from Existing Sources > {Choose directory and Gradle as Build Tool}
```