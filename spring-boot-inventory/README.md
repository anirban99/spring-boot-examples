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

Response from the GET request:
```
[
    {
        "id": 2,
        "make": "Mercedes",
        "model": "S-Class",
        "finalPrice": 30600.0,
        "condition": "NEW",
        "oldPrice": 34000.0,
        "date": "2021-03-02"
    },
    {
        "id": 5,
        "make": "Audi",
        "model": "A3",
        "finalPrice": 32450.0,
        "condition": null,
        "oldPrice": null,
        "date": "2021-03-02"
    },
    {
        "id": 6,
        "make": "VW",
        "model": "Golf 3",
        "finalPrice": 748.0,
        "condition": "USED",
        "oldPrice": 850.0,
        "date": "2021-03-05"
    },
    {
        "id": 7,
        "make": "Audi",
        "model": "Van type",
        "finalPrice": 8900.0,
        "condition": "NEW",
        "oldPrice": null,
        "date": null
    },
    {
        "id": 8,
        "make": "Trabant",
        "model": "601 Deluxe",
        "finalPrice": 1000.0,
        "condition": "NEW",
        "oldPrice": null,
        "date": "1989-12-09"
    },
    {
        "id": 23,
        "make": "Mercedes",
        "model": "E-Class",
        "finalPrice": 8000.0,
        "condition": "USED",
        "oldPrice": 9999.95,
        "date": "2021-04-04"
    },
    {
        "id": 25,
        "make": "Mercedes",
        "model": "S-Class",
        "finalPrice": 12000.0,
        "condition": null,
        "oldPrice": null,
        "date": "2021-03-02"
    },
    {
        "id": 65,
        "make": "BMW",
        "model": "354i",
        "finalPrice": 7999.0,
        "condition": "USED",
        "oldPrice": 8890.5,
        "date": "2007-06-21"
    },
    {
        "id": 67,
        "make": "Ford",
        "model": "Bronco Vintage Supreme",
        "finalPrice": 8950.3,
        "condition": "USED",
        "oldPrice": null,
        "date": "1995-08-26"
    },
    {
        "id": 71,
        "make": "Mini",
        "model": "Classic Saloon",
        "finalPrice": 2849.9525,
        "condition": null,
        "oldPrice": 2999.95,
        "date": "2001-04-01"
    },
    {
        "id": 72,
        "make": "Mini",
        "model": "Classic Saloon X3",
        "finalPrice": 2000.0,
        "condition": null,
        "oldPrice": null,
        "date": "2002-03-11"
    },
    {
        "id": 76,
        "make": "Toyota",
        "model": "Corolla",
        "finalPrice": 17380.425,
        "condition": "NEW",
        "oldPrice": 18995.0,
        "date": "2014-03-12"
    },
    {
        "id": 78,
        "make": "Jaguar",
        "model": "X-Type",
        "finalPrice": 56000.0,
        "condition": "USED",
        "oldPrice": null,
        "date": "2005-12-16"
    },
    {
        "id": 95,
        "make": "Toyota",
        "model": "Corolla",
        "finalPrice": 9200.0,
        "condition": "USED",
        "oldPrice": null,
        "date": "2014-09-15"
    },
    {
        "id": 98,
        "make": "Toyota",
        "model": "Corolla",
        "finalPrice": 21200.0,
        "condition": null,
        "oldPrice": null,
        "date": "2018-11-09"
    },
    {
        "id": 99,
        "make": "Toyota",
        "model": "Corolla",
        "finalPrice": 9200.0,
        "condition": "USED",
        "oldPrice": null,
        "date": "2014-09-16"
    }
]
```


### Integration with IntelliJ IDEA

You can import the project into your IDE using the following steps:
```
File > New > Project from Existing Sources > {Choose directory and Gradle as Build Tool}
```
