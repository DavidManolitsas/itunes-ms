# iTunes Microservice

Spring Boot Microservice that retrieves data from Apple's
[iTunes API](https://developer.apple.com/library/archive/documentation/AudioVideo/Conceptual/iTuneSearchAPI/index.html) 
and collates it into concise information that can retrieved from the API endpoints.

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
