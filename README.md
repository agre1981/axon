# Getting Started

### Quick Start

1. Run Exon Server (http://localhost:8024/)
```
docker-compose -f src/main/docker/docker-compose-axonserver-se.yml up
```
2. Run Application (http://localhost:8090/)
```
gradlew bootRun
```
3. Endpoints (see http-request.http) 
```
GET http://localhost:8090/orders
 
POST http://localhost:8090/orders
Content-Type: application/json
{
  "name": "order1"
}

DELETE http://localhost:8090/orders/order1
```

### Reference Documentation
For further reference, please consider the following sections:

* [Spring Web](https://docs.spring.io/spring-boot/docs/2.5.4/reference/htmlsingle/#boot-features-developing-web-applications)
* [Spring Boot Actuator](https://docs.spring.io/spring-boot/docs/2.5.4/reference/htmlsingle/#production-ready)
* [Spring Boot DevTools](https://docs.spring.io/spring-boot/docs/2.5.4/reference/htmlsingle/#using-boot-devtools)

### Guides
The following guides illustrate how to use some features concretely:

* [Building a RESTful Web Service](https://spring.io/guides/gs/rest-service/)
* [Serving Web Content with Spring MVC](https://spring.io/guides/gs/serving-web-content/)
* [Building REST services with Spring](https://spring.io/guides/tutorials/bookmarks/)
* [Building a RESTful Web Service with Spring Boot Actuator](https://spring.io/guides/gs/actuator-service/)

### Additional Links
These additional references should also help you:

* [The Axon Framework open-source code repository on GitHub](https://github.com/AxonFramework)
* [The reference guide on how to use Axon](https://docs.axoniq.io/reference-guide/)
* [A full getting started tutorial for Axon in small simple steps (YouTube).](https://www.youtube.com/watch?v=tqn9p8Duy54&list=PL4O1nDpoa5KQkkApGXjKi3rzUW3II5pjm)
* [The reference guide section on how to use Axon Test module](https://docs.axoniq.io/reference-guide/axon-framework/testing)

### Running Axon Server Standard Edition in Docker

* The `docker/docker-compose-axonserver-se.yml` allows you to start Axon Server SE with the "`docker-compose -f docker-compose-axonserver-se.yml up`" command
* [The blog post on how to run Axon Server in Docker](https://axoniq.io/blog-overview/running-axon-server-in-docker)
