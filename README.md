# Scrumptious Orchestrator Service

This is a router/apigateway/orchestrator service that finds other services through Eureka and routes them through a single port `8080`.

Here is a list of microservices and their corresponding access url's ([see LoadBalancedRoutesConfig](./src/main/java/com/ss/api_gateway/config/LoadBalancedRoutesConfig.java)):

- Auth service: `http://localhost:8080/auth`

- Customer service: `http://localhost:8080/customer`

- Customer service: `http://localhost:8080/customer`

- Restaurant service: `http://localhost:8080/restaurant`

- Order service: `http://localhost:8080/order`

## Additional Services

This service is also responsible for running authentication checks on all traffic ([see JwtTokenFilter](./src/main/java/com/ss/api_gateway/config/JwtTokenFilter.java)).

The service also has its own [Cors Configuration](./src/main/java/com/ss/api_gateway/config/CorsConfiguration.java)).
