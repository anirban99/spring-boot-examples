## Spring Boot Mobility

## Getting Started
This is a microservice which calculates the position of vehicles inside strategic geojson-polygons and serve the cars
and polygons via a REST API.

### Requirement

This application is built using the following technologies:

* Kotlin 1.6.10
* Spring Boot 2.6.2
* Spring Webflux 2.6.2
* Gradle
* Jackson
* Jacoco 
* JUnit
* Mockk
* Git
* Docker
* Bash
* Open API

### Building and running the application

Fetch the docker container to access the car2go vehicles API:
```
docker pull car2godeveloper/api-for-coding-challenge 
```

To build and run the application using Bash script run this command:
```
bash run.sh
```
To build and run the application using Docker compose run this command:
```
./gradlew clean build

docker-compose up --build
```
To build and run the application using Docker image run this command:
```
./gradlew clean build

docker run -d -p 3000:3000 car2godeveloper/api-for-coding-challenge

docker build --tag=spring-boot-mobility:latest .

docker run -d --add-host host.docker.internal:host-gateway -p 8080:8080 spring-boot-mobility:latest
```
The car2go API specification is available under this url:
```
http://localhost:3000/documentation/
```
The spring boot mobility OpenAPI UI is available under the url:
```
http://localhost:8080/swagger-ui/index.html
```
Note: If the API descriptions doesn't show up in the above url, search `/v3/api-docs` in the Explore option.

The OpenAPI descriptions is available under the url:
```
http://localhost:8080/v3/api-docs/
```
The spring boot mobility API is available under the url:
```
http://localhost:8080/
```
GET request to fetch a list of vehicles based on Polygon Id
```
curl --location --request GET 'http://localhost:8080/vehicles/58a58bdb766d51540f7794e4'
```
GET request to fetch a polygon based on the VIN of the vehicles
```
curl --location --request GET 'http://localhost:8080/locations/4T1BE30K16S9GP3AA'
```

#### Note:
I'm using `host.docker.internal` to establish localhost connection between the docker containers.
The `host.docker.internal` is specified in the `docker-compose` file. The `host.docker.internal` 
can also be passed as argument to run the Docker image.

Alternatively, you can use ipconfig or ifconfig on your host and make note of all IP addresses. At least 
two of them can be used by the container.

https://stackoverflow.com/a/70870433/6289982

The url for the localhost can be updated in the application.yml properties `car2go.baseUrl`

### Integration with IntelliJ IDEA

You can import the project into your IDE using the following steps:
```
File > New > Project from Existing Sources > {Choose directory and Gradle as Build Tool}
```
