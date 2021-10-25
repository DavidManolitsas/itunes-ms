# iTunes Microservice

A microservice & Spring Boot 2 application to query the iTunes API to retrieve data
regarding an Artist and their albums.

## Endpoints

* [GET] `/artists/{term}`
* [GET] `/albums/{artistId}`

Can view more information through the [Swagger](swagger.yml)

## Build Project

```bash
mvn clean sortpom:sort fmt:format install
```

## Test the project

```bash
mvn clean sortpom:sort fmt:format test
```

##  Run the project

```bash
mvn spring-boot:run
```
