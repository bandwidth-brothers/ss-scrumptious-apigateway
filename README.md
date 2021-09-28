# Scrumptious Orchestrator Service

This is a router/apigateway/orchestrator service that finds other services through Eureka and routes them through a single port `8080`.

Use `mvn spring-boot:run` to build and launch the application.

Here is a list of microservices and their corresponding access url's ([see LoadBalancedRoutesConfig](./src/main/java/com/ss/api_gateway/config/LoadBalancedRoutesConfig.java)):

- [Auth service](https://github.com/bandwidth-brothers/ss-scrumptious-auth): `http://localhost:8080/auth`

- [Customer service](https://github.com/bandwidth-brothers/ss-scrumptious-customers): `http://localhost:8080/customer`

- [Restaurant service](https://github.com/bandwidth-brothers/ss-scrumptious-restaurant): `http://localhost:8080/restaurant`

- [Order service](https://github.com/bandwidth-brothers/ss-scrumptious-orders): `http://localhost:8080/order`

## Additional Services

This service is responsible for running authentication checks on all traffic ([see JwtTokenFilter](./src/main/java/com/ss/api_gateway/config/JwtTokenFilter.java)).

The service also has its own [Cors Configuration](./src/main/java/com/ss/api_gateway/config/CorsConfiguration.java).
