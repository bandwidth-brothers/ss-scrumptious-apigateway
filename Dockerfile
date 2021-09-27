FROM maven:latest
COPY target/ss-scrumptious-apigateway-0.0.1-SNAPSHOT.jar /home/scrumptious-apigateway.jar
ENTRYPOINT java -jar /home/restaurant-eureka.jar