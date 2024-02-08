

# High Level Overview


### Folder Structure Explanation:

- **parent-service**: This is the root directory containing all modules and files related to the parent service.
  - **api-gateway**: This module  contains the implementation of an API gateway, which acts as an entry point for client requests to the microservices architecture.
  - **booking-service**: This module contains the implementation of the booking service, which handles booking-related functionalities.
  - **discovery-server**: This module  contains the implementation of a service discovery server, such as Eureka, which helps microservices find and communicate with each other dynamically.
  - **users-service**: This module contains the implementation of the users service, which handles user-related functionalities.
  - **api_gateway.log**: This log file  contains logs specific to the API gateway module, capturing information, warnings, and errors related to its operation.
  - **booking_service.log**: This log file contains logs specific to the booking service module, capturing information, warnings, and errors related to its operation.
  - **user_service.log**: This log file  contains logs specific to the users service module, capturing information, warnings, and errors related to its operation.
  - **pom.xml**: This is the Maven Project Object Model (POM) file, which defines the project configuration and dependencies for building the parent service.

### Folder Structure Hierarchy:

```
parent-service/
├── api-gateway/
├── booking-service/
├── discovery-server/
├── users-service/
├── api_gateway.log
├── booking_service.log
├── user_service.log
└── pom.xml
```





### Request:

- **Type**: POST
- **URL**: http://localhost:8080/api/booking/book
- **Body**: The request body contains JSON data with the following fields:
  - `userName`: The username of the user booking the cab.
  - `pickupLocation`: The location where the cab will pick up the passenger.
  - `dropoffLocation`: The destination where the cab will drop off the passenger.
  - `pickupTime`: The time at which the passenger wants to be picked up (in UTC time format).
  - `vehicleReg`: The registration number of the vehicle.
  - `cabType`: The type of cab being booked (e.g., Luxury).
  ### Request:

```json
{
    "userName": "jon_doe",
    "pickupLocation": "Madrid",
    "dropoffLocation": "Manchester",
    "pickupTime": "2024-01-27T09:00:00Z",
    "vehicleReg": "vehicle_registration_here",
    "cabType": "Luxury"
}
```


### Response (Success):

- **bookingInfo**: Contains information about the booked ride, including:
  - `id`: The unique identifier of the booking.
  - `userId`: The ID of the user who made the booking.
  - `pickupLocation`: The pickup location specified in the request.
  - `dropoffLocation`: The dropoff location specified in the request.
  - `pickupTime`: The pickup time specified in the request.
- **message**: A descriptive message indicating the success of the booking, including details such as the type of cab booked and the pickup and dropoff locations.
- **code**: A numeric code indicating the status of the response (1 typically denotes success).
  


```json
{
	"bookingInfo": {
		"id": 252,
		"userId": 1,
		"pickupLocation": "Madrid",
		"dropoffLocation": "Manchester",
		"pickupTime": "2024-01-27T09:00:00Z"
	},
	"message": "LuxuryCab Booked from Madrid to Manchester",
	"code": 1
}
```

### Response (Unsuccessful):

- **bookingInfo**: An empty object `{}` is returned.
- **message**: A descriptive error message explaining the reason for the failure. In this case, it indicates a connection error with a specific IP address and port.
- **code**: A numeric code indicating the status of the response (1 typically denotes an error condition).




```json
{
	"bookingInfo": {},
	"message": "finishConnect(..) failed: Connection refused: /192.168.0.113:41213",
	"code": 1
}
```




---



<p>&nbsp;</p>
<p>&nbsp;</p>
<p>&nbsp;</p>
<p>&nbsp;</p>
<p>&nbsp;</p>


# Api Gateway



### Code:
- `ApiGatewayApplication`: This class is the main entry point of the API Gateway application. It is annotated with `@SpringBootApplication` to mark it as a Spring Boot application and `@EnableDiscoveryClient` to enable service discovery with Eureka.
  ```

  package org.clairclarke;


  import org.springframework.boot.SpringApplication;
  import org.springframework.boot.autoconfigure.SpringBootApplication;
  import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

  @SpringBootApplication
  @EnableDiscoveryClient
  public class ApiGatewayApplication {
      public static void main(String[] args) {
          SpringApplication.run(ApiGatewayApplication.class, args);
      }
  }
  
  ```

### Configuration (application.yml):
- `eureka.client.serviceUrl.defaultZone`: This property specifies the URL of the Eureka server where service registration and discovery occur.
- `spring.application.name`: This property sets the application name to 'api-gateway'.
- `spring.cloud.gateway.routes`: This section defines the routes for the API Gateway.
  - `routes[0]`, `routes[1]`, `routes[2]`, `routes[3]`: These entries define individual routes for different services and resources.
    - `id`: Specifies a unique identifier for each route.
    - `uri`: Specifies the target URI where requests matching this route should be forwarded.
    - `predicates[0]`: Defines the predicate for matching incoming requests. In this case, it uses the `Path` predicate to match requests based on their paths.
    - `filters`: Optional. Specifies filters to be applied to requests before they are forwarded.
- `logging`: This section configures logging levels and file output.
  - `level.root`: Sets the root logging level to INFO.
  - `level.org.springframework.cloud.gateway.route.RouteDefinitionLocator`: Sets the logging level for the RouteDefinitionLocator to INFO.
  - `level.org.springframework.cloud.gateway`: Sets the logging level for the Spring Cloud Gateway package to TRACE.
  - `pattern.file`: Specifies the logging pattern for the log file.
  - `file.path`: Specifies the directory where log files are stored.
  - `file.name`: Specifies the name of the log file.
  ```
      eureka:
      client:
        serviceUrl:
          defaultZone: http://localhost:8781/eureka

    spring:
      application.name: api-gateway
      cloud:
        gateway:
          routes[0]:
            id: users-service
            uri: lb://users-service
            predicates[0]: Path=/api/users/*
    #     Booking routes
          routes[1]:
            id: booking-service
            uri: lb://booking-service
            predicates[0]: Path=/api/booking/*
    #     Discovery server routes
          routes[2]:
            id: discovery-server
            uri: http://localhost:8781
            predicates[0]: Path=/eureka/web
            filters: SetPath=/
    #     Discovery server static resources routes
          routes[3]:
            id: discovery-server-static
            uri: http://localhost:8781
            predicates[0]: Path=/eureka/**
    logging:
      level:
        root: INFO
        org.springframework.cloud.gateway.route.RouteDefinitionLocator: INFO
        org.springframework.cloud.gateway: TRACE


        '[com.clairclarke.cabproject]': INFO

      pattern:
        file: "%d [%level] %c{1.} [%t] %m%n"
      file:
        path: /logs/
        name: api_gateway.log



  ```

### Notes:
- The `routes` section in the YAML file defines routes for different services (`users-service`, `booking-service`, `discovery-server`) based on their paths.
- The API Gateway will forward requests to the appropriate services based on the defined routes and predicates.
- Logging configurations are set up to log gateway-related activities and requests to the specified log file.

Ensure that the Eureka server is running at `http://localhost:8781` and that the other services (`users-service`, `booking-service`, etc.) are registered with Eureka for service discovery to work correctly. Also, verify that the log directory specified (`/logs/`) exists and is writable by the application.




<p>&nbsp;</p>
<p>&nbsp;</p>
<p>&nbsp;</p>
<p>&nbsp;</p>
<p>&nbsp;</p>


# Discovery Server


Your `DiscoverServerApplication` class sets up a Eureka server, enabling service registration and discovery. The provided configuration sets up the Eureka server to run on port 8781 with certain properties.

Here's a breakdown of the code and configuration:

### Code (DiscoverServerApplication.java):

- `@EnableEurekaServer`: This annotation enables the application to act as a Eureka server, allowing other services to register with it for service discovery.
- `@SpringBootApplication`: This annotation marks the class as a Spring Boot application, enabling auto-configuration and component scanning.
- `main()`: This method is the entry point of the application. It bootstraps the Spring Boot application.
  
  ```
    package org.clairclarke;


    import org.springframework.boot.SpringApplication;
    import org.springframework.boot.autoconfigure.SpringBootApplication;
    import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

    @EnableEurekaServer
    @SpringBootApplication
    public class DiscoverServerApplication {
        public static void main(String[] args) {
            SpringApplication.run(DiscoverServerApplication.class, args);
        }
    }
  
  ```

### Configuration (application.yml):

- `eureka.instance.hostname`: Specifies the hostname for the Eureka server. Here, it's set to `localhost`.
- `eureka.client.register-with-eureka`: Specifies whether the server should register itself with Eureka. It's set to `false` because this instance is the Eureka server itself and doesn't need to register with another Eureka server.
- `eureka.client.fetch-registry`: Specifies whether the server should fetch the registry information from the Eureka server. Since this is the Eureka server, it doesn't need to fetch the registry, so it's set to `false`.
- `server.port`: Specifies the port on which the Eureka server should run. Here, it's set to `8781`.
- `spring.application.name`: Sets the name of the application to 'discovery-server'. This name will be used in the Eureka registry to identify the Eureka server instance.
  ```
  eureka:
  instance:
    hostname: localhost
  client:
    register-with-eureka: false
    fetch-registry: false

  server:
    port: 8781

  spring:
    application.name: discovery-server
  ```

Overall, this configuration sets up a Eureka server that listens on port 8781 and doesn't register itself with another Eureka server. Other services can register with this Eureka server for service discovery and registration. Make sure to run this Eureka server alongside your other services that need service discovery capabilities.








<p>&nbsp;</p>
<p>&nbsp;</p>
<p>&nbsp;</p>
<p>&nbsp;</p>
<p>&nbsp;</p>


# Booking service

Here's the file hierarchy for the Booking service, including all files and folders/packages:



```
booking-service/
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   ├── org.clairclarke.booking/
│   │   │   │   ├── config/
│   │   │   │   │   ├── WebClientConfig.java
│   │   │   │   ├── controller/
│   │   │   │   │   ├── BookingController.java
│   │   │   │   ├── dto/
│   │   │   │   │   ├── BookingRequest.java
│   │   │   │   │   ├── BookingResponse.java
│   │   │   │   │   ├── BookingResponseDto.java
│   │   │   │   │   ├── WebRequestUserDto.java
│   │   │   │   ├── entity/
│   │   │   │   │   ├── BookingEntity.java
│   │   │   │   ├── repository/
│   │   │   │   │   ├── BookingRepository.java
│   │   │   │   ├── service/
│   │   │   │   │   ├── BookingService.java
│   │   │   │   │   ├── BookingServiceImpl.java
│   │   ├── resources/
│  

```




- **config/**: Contains configuration files for the booking service.
  - `WebClientConfig.java`: Configuration class for WebClient setup.
  
- **controller/**: Contains controller classes for handling HTTP requests.
  - `BookingController.java`: Controller class for handling booking-related requests.
  
- **dto/**: Contains Data Transfer Object (DTO) classes used for data exchange.
  - `BookingRequest.java`: DTO class representing a booking request.
  - `BookingResponse.java`: DTO class representing a booking response.
  - `BookingResponseDto.java`: DTO class representing detailed booking response information.
  
- **entity/**: Contains entity classes representing database entities.
  - `BookingEntity.java`: Entity class representing a booking entity.
  
- **repository/**: Contains repository interfaces for database access.
  - `BookingRepository.java`: Interface extending JpaRepository for accessing booking data.
  
- **service/**: Contains service interfaces and implementations.
  - `BookingService.java`: Interface defining booking service methods.
  - `BookingServiceImpl.java`: Implementation class for booking service methods.

This hierarchy represents a typical structure for a Spring Boot application organized into packages based on functionality. Each package contains related files that serve specific purposes within the booking service.







### `WebClientConfig` (Webconfig.java)

This class contains the configuration for creating a `WebClient.Builder` bean, which is a part of Spring WebFlux used for making web requests. The main annotations and method are:

- `@Configuration`: Indicates that this class is a configuration class.
- `@Bean`: Marks a method as a bean-producing method that should be managed by the Spring container.
- `@LoadBalanced`: Indicates that the `WebClient.Builder` should be configured to work with load-balanced requests.

Method:
```java
@Bean
@LoadBalanced
public WebClient.Builder webClientBuilder() {
    return WebClient.builder();
}
```

Explanation:
- This method creates and configures a `WebClient.Builder` bean.
- `@LoadBalanced` enables client-side load balancing for requests made with this `WebClient.Builder`. It's useful when your application communicates with multiple instances of a service (e.g., via Eureka service discovery).

### `BookingController` (BookingController.java)

This class is a Spring MVC controller responsible for handling HTTP requests related to booking services. Key annotations and methods are:

```java
@RequestMapping("/api/booking")
public class BookingController {
    // ... (other methods and fields)

    @PostMapping("/book")
    @CircuitBreaker(name = "users", fallbackMethod = "bookingFallBackMethod")
    public ResponseEntity<BookingResponse> createBooking(@RequestBody BookingRequest booking) {
        // ... (method implementation)
    }

    public String bookingFallBackMethod(BookingRequest bookingRequest, RuntimeException runtimeException){
        // ... (fallback method implementation)
    }
    // ... (other methods)
}
```

Explanation:
- `@RequestMapping("/api/booking")`: Specifies that this controller handles requests with the "/api/booking" path.
- `@PostMapping("/book")`: Handles HTTP POST requests to "/api/booking/book".
- `@CircuitBreaker`: This annotation is typically associated with resilience patterns. In this case, it specifies that a circuit breaker named "users" should be applied to the `createBooking` method, and the `bookingFallBackMethod` should be called if the circuit is open.

### `BookingServiceImpl` (BookingserviceImpl.java)

This class implements the `BookingService` interface and contains the logic for creating bookings. The key part of the code is:

```java
WebRequestUserDto user = webClientBuilder.build().get()
        .uri("http://users-service/api/users/search_user_name/" + booking.getUserName())
        .retrieve()
        .bodyToMono(WebRequestUserDto.class)
        .block();
```

Explanation:
- `webClientBuilder.build().get()`: Builds a `WebClient` instance and prepares an HTTP GET request.
- `.uri("http://users-service/api/users/search_user_name/" + booking.getUserName())`: Specifies the URI for the GET request, including the username from the booking request.
- `.retrieve()`: Executes the request and returns a `BodySpec` for further processing.
- `.bodyToMono(WebRequestUserDto.class)`: Converts the response body to a reactive Mono containing an instance of `WebRequestUserDto`.
- `.block()`: Blocks until the result is available, effectively making this a synchronous call.

This code fetches user information by making a synchronous request to the "users-service" based on the provided username in the booking request. It retrieves a `WebRequestUserDto` object representing the user.