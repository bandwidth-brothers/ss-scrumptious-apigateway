FROM maven:latest
COPY target/api_gateway-0.0.1-SNAPSHOT.jar /home/scrumptious-gateway.jar
ENTRYPOINT java -jar /home/scrumptious-gateway.jar
