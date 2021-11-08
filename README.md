# iTunes Microservice

A microservice & Spring Boot 2 application to query the iTunes API to retrieve data
regarding an Artist and their albums.

## API Endpoints

* [GET] `/artists/{term}`
* [GET] `/albums/{artistId}`
* [GET] `/healthcheck`

For more information refer to the [Swagger](itunes_swagger.yaml)

## Build Project

To build the project and generate the model classes using Swagger `code-gen`, run:

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
